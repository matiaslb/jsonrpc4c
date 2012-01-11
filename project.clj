(defproject jsonrpc4c "0.0.1-SNAPSHOT"
            :description "a JSON-RPC library for Clojure"
            :dependencies [[org.clojure/clojure "1.3.0"]
                           [org.clojure/tools.macro "0.1.1"]
                           [org.clojure/data.json "0.1.1"]
                           [ring/ring-core "1.0.1"]
                           [ring/ring-jetty-adapter "1.0.1"]
                           [ring-json-params "0.1.3"]
                           [compojure "1.0.1"]]
            :dev-dependencies [[lein-ring "0.5.4"]]
            :ring {:handler jsonrpc4c.server/app})
