(defproject jsonrpc4c "0.0.1-SNAPSHOT"
            :description "a JSON-RPC library for the Clojure programming language"
            :dependencies [[org.clojure/clojure "1.2.1"]
                           [org.clojure/clojure-contrib "1.2.0"]
                           [ring/ring-core "0.3.11"]
                           [ring/ring-jetty-adapter "0.3.11"]
                           [ring-json-params "0.1.3"]
                           [compojure "0.6.5"]]
            :dev-dependencies [[lein-ring "0.4.5"]]
            :ring {:handler jsonrpc4c.server/app})
