(ns cf.cfn-init
  (:require
    [cf.fn          :as fn]
    [cf.ref         :as ref]
    [clojure.string :as s]
    [selmer.parser  :as selmer]))

(defn cfn-hup-conf
  []
  ["[main]\n"
   "stack=" (ref/ref "AWS::StackName") "\n"
   "interval=1\n"
   "verbose=true\n"
   "region=" (ref/ref "AWS::Region") "\n"])

(defn cfn-auto-reloader-conf
  [resource-name]
  ["[cfn-auto-reloader-hook]\n"
   "triggers=post.update\n"
   "path=Resources." resource-name ".Metadata.AWS::CloudFormation::Init\n"
   "action=cfn-init -v "
   "                --stack " (ref/ref "AWS::StackName")
   "                --resource " resource-name
   "                --region " (ref/ref "AWS::Region") "\n"
   "runas=root\n"])

(defn- user-data
  [resource-name]
  ["#!/bin/bash -xe\n"

   "# Run cfn-init commands\n"
   "cfn-init -v "
   "         --stack " (ref/ref "AWS::StackName")
   "         --resource " resource-name
   "         --region " (ref/ref "AWS::Region") "\n"

   "# Signal the status from cfn-init\n"
   "cfn-signal -e $? "
   "         --stack " (ref/ref "AWS::StackName")
   "         --resource " resource-name
   "         --region " (ref/ref "AWS::Region") "\n"])

(defn- cfn-init-config
  [resource-name]
  {"/etc/cfn/cfn-hup.conf"                   {:content (fn/join "" (cfn-hup-conf)) :mode "000400" :owner "root" :group "root"}
   "/etc/cfn/hooks.d/cfn-auto-reloader.conf" {:content (fn/join "" (cfn-auto-reloader-conf resource-name))}})

(defn with-metadata
  [resource-name]
  #(-> %
       (update-in [:Metadata "AWS::CloudFormation::Init" :config :files] merge (cfn-init-config resource-name))
       (assoc-in [:Metadata "AWS::CloudFormation::Init" :config :services :sysvinit :cfn-hup]
                 {:enabled "true" :ensureRunning "true"
                  :files ["/etc/cfn/cfn-hup.conf" "/etc/cfn/hooks.d/cfn-auto-reloader.conf"]})))

(defn with-user-data
  [resource-name]
  #(->> (user-data resource-name)
        (fn/join "")
        (fn/base-64)
        (assoc-in % [:Properties :UserData])))

(defn with-files
  [& files]
  #(reduce (fn [instance [dest src mode owner group params]]
             (assoc-in instance [:Metadata "AWS::CloudFormation::Init" :config :files dest]
                       {:content (selmer/render-file src params)
                        :mode mode
                        :owner owner
                        :group group})) % files))

(defn- command-key
  [n command-name]
  (str (when (< n 10) "0")
       n "_" (s/replace (name command-name) #"-" "_")))

(defn with-commands
  [& commands]
  #(let [start-index (inc (count (get-in % [:Metadata "AWS::CloudFormation::Init" :config :commands])))]
     (reduce (fn [instance [index [name command]]]
               (assoc-in instance
                         [:Metadata "AWS::CloudFormation::Init" :config :commands (command-key (+ start-index index) name)]
                         {:command command}))
             % (map-indexed vector commands))))
