(ns jsonrpc4c.server
  (:use compojure.core)
  (:use ring.util.response)
  (:use ring.middleware.json-params)
  (:require [compojure.handler :as handler]
            [compojure.route :as route]
            [clojure.data.json :as json]
            [clojure.walk :as walk]
            [jsonrpc4c.rpc :as rpc]))

(defn json-response [data & [status]]
  "Encapsulates data into a json response"
  {:status (or status 200)
   :headers {"Content-Type" "application/json"}
   :body (json/json-str data)})

(defn dispatch-rpc-request [id method params]
  "Dispatches json rpc requests to the rpc dispatcher"
  (json-response (rpc/dispatch id method params)))

(defroutes rpc-routes
           (POST "/" {json-params :json-params} 
                 (let [{id "id" method "method" params "params"} json-params] 
                   (dispatch-rpc-request id method (cond
                                                     (map? params) (walk/keywordize-keys params)
                                                     :else params)))))
(def app
  (-> rpc-routes
    handler/site
    wrap-json-params))

