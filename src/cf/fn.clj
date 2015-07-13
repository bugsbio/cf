(ns cf.fn)

(defn get-att
  [ref att]
  {"Fn::GetAtt" [ref, att]})

(defn join
  [delimiter tokens]
  {"Fn::Join" [delimiter tokens]})

(defn base-64
  [content]
  {"Fn::Base64" content})

(defn get-azs
  []
  {"Fn::GetAZs" ""})
