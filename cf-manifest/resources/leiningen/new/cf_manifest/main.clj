(ns {{ name }}.main
  (:require
    [cf.core :as cf]
    [{{ main }}.stack])
  (:gen-class))

(defn parse-opts
  [opts]
  {:pretty (some #{"--pretty"} opts)})

(defn stack-args
  [opts]
  (remove #{"--pretty"} opts))

(defn -main
  [stack & opts]
  (-> (apply cf/generate-stack (keyword stack) (stack-args opts))
      (cf/to-cloudformation (parse-opts opts))
      (println))
  (System/exit 0))
