(ns cf.tag)

(defn tag
  [k v]
  {:Key k :Value v})

(defn with-tags
  [& kvs]
  #(assoc-in % [:Properties :Tags] (map (partial apply tag) (partition 2 kvs))))
