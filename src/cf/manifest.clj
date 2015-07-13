(ns cf.manifest
  (:require
    [cheshire.core :as json]))

(defn manifest
  "Creates an empty map, or takes a map as argument, to use as the base for
  a threading construct that build up your manifest. You could just as well use
  an empty map in its place; it is provided for readability's sake."
  [& [content]]
  content)

(defn version
  "Set the AWS Template version."
  [manifest version]
  (assoc manifest :AWSTemplateFormatVersion "2010-09-09"))

(defn description
  "Set a stack description."
  [manifest & description]
  (assoc manifest :Description (apply str description)))

(defn to-cloudformation
  "Generate Cloudformation JSON given its Clojure map form.
  Options are passed along to cheshire.core/generate-string."
  [manifest opts]
  (json/generate-string manifest opts))
