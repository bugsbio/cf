(ns cf.git
  (:require
    [clojure.java.shell :refer [sh]]
    [clojure.string     :as s]))

(defn sha
  []
  (s/trim-newline (:out (sh "git" "rev-parse" "--short" "HEAD"))))

