(defproject {{ name }} "0.1.0-SNAPSHOT"
  :description "CloudFormation infrastructure config"
  :url "http://github.com/FIXME"
  :main {{ name }}.main
  :dependencies [[org.clojure/clojure "1.6.0"]
                 [cf "0.1.0-SNAPSHOT"]]
  :profiles {:dev {:plugins [[lein-exec "0.3.4"]]}})
