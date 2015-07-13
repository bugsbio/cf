(defproject cf "0.1.0-SNAPSHOT"
  :description "DSL for building Cloudformation JSON as a Clojure map"
  :url "http://github.com/bugsbio/cf"
  :license {:name "Eclipse Public License"
            :url "http://www.eclipse.org/legal/epl-v10.html"}
  :dependencies [[org.clojure/clojure "1.6.0"]
                 [selmer "0.8.0"]
                 [cheshire "5.4.0"]]
  :profiles {:dev {:dependencies [[expectations "2.1.1"]]
                   :test-paths ["test"]
                   :plugins [[lein-exec "0.3.4"]
                             [lein-autoexpect "1.4.2"]
                             [lein-expectations "0.0.8"]]}})
