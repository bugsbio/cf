(ns cf.resource)

(defn refine
  [resource refinement]
  (cond (fn? refinement)  (refinement resource)
        (map? refinement) (merge resource refinement)
        :else             (throw (ex-info "Resource refinement must be either a map or fn."
                                          {:resource resource
                                           :refinement refinement}))))

(defmacro defresourcetype
  "Used internally by cf to define resource creation functions. There should be
  no need for you to call this yourself."
  [name identifier & [doc-url]]
  `(defn ~(vary-meta name assoc :doc-url doc-url)
     [manifest# resource-name# props# & refinements#]
     (assoc-in manifest# [:Resources resource-name#]
               (reduce refine {:Type ~identifier :Properties props#} refinements#))))

(defn with-properties
  [props]
  #(update-in % [:Properties] merge props))

(defn update-resource
  [manifest resource-name & refinements]
  (update-in manifest [:Resources resource-name] (apply comp refinements)))
