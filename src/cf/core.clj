(ns cf.core
  (:refer-clojure :exclude [ref])
  (:require
    [cf.autoscaling      :as autoscaling]
    [cf.cfn-init         :as cfn-init]
    [cf.cloud-watch      :as cloud-watch]
    [cf.dimension        :as dim]
    [cf.ec2              :as ec2]
    [cf.elasticache      :as elasticache]
    [cf.elb              :as elb]
    [cf.fn               :as fn]
    [cf.git              :as git]
    [cf.iam              :as iam]
    [cf.manifest         :as manifest]
    [cf.rds              :as rds]
    [cf.ref              :as ref]
    [cf.resource         :as resource]
    [cf.route-53         :as route-53]
    [cf.sg               :as sg]
    [cf.sns              :as sns]
    [cf.sqs              :as sqs]
    [cf.stack            :as stack]
    [cf.tag              :as tag]
    [clojure.string      :as s]))

(def dim               dim/dim)
(def base-64           fn/base-64)
(def get-att           fn/get-att)
(def join              fn/join)
(def get-azs           fn/get-azs)
(def ref               ref/ref)
(def update-resource   resource/update-resource)
(def with-properties   resource/with-properties)
(def register-stack    stack/register-stack)
(def generate-stack    stack/generate-stack)
(def tag               tag/tag)
(def with-tags         tag/with-tags)

(def description       manifest/description)
(def manifest          manifest/manifest)
(def version           manifest/version)
(def to-cloudformation manifest/to-cloudformation)

(defmacro defstack
  "Define a Cloudformation stack. Body should be a series of forms through
  which a manifest will be threaded (thread-first) to construct a Clojure map
  representing the desired Cloudformation JSON."
  [name args & forms]
  `(register-stack ~(keyword name) (fn ~args (-> (manifest) ~@forms))))

(defmacro defresources
  "Define a function that, given a manifest, will define the set of resources
  configured in its body."
  [name args & resources]
  `(defn ~name [manifest# ~@args] (-> manifest# ~@resources)))

(defmacro cf-docs
  "Call this with a cf resource function as its argument to be taken to the
  AWS docs for that resource."
  [resource]
  `(if-let [doc-url# (:doc-url (meta (var ~resource)))]
     (browse/browse-url doc-url#)
     (println "No doc-url attached to this resource.")))
