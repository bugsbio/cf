(ns leiningen.new.cf-manifest
  (:require [leiningen.new.templates :refer [renderer name-to-path ->files]]
            [leiningen.core.main     :as main]))

(def render (renderer "cf-manifest"))

(defn cf-manifest
  "Create a new cf manifest project for describing your infrastructure using
  using CloudFormation."
  [name]
  (let [data {:name name
              :sanitized (name-to-path name)}]
    (main/info "Generating fresh 'lein new' cf-manifest project.")
    (->files data
             ["project.clj"                (render "project.clj" data)]
             ["bin/cf-tail"                (render "bin/cf-tail" data)]
             ["bin/connect"                (render "bin/connect" data)]
             ["bin/dns-name"               (render "bin/dns-name" data)]
             ["src/{{sanitized}}/main.clj" (render "main.clj" data)])))
