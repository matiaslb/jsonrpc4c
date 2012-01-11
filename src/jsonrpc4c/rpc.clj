(ns jsonrpc4c.rpc
  (:use (clojure.tools [macro :only [name-with-attributes]]))
  (:require [clojure.data.json :as json]))

;;;;;;;;;;;
; Constants

(def error-msgs
  ^{:doc "JSON-RPC 2.0 standard error messages."}
  {:parse-error      "Invalid JSON was received by the server."
   :invalid-request  "The JSON sent is not a valid Request object."
   :method-not-found "The method does not exist / is not available."
   :invalid-params   "Invalid method parameter(s)."
   :internal-error   "Internal JSON-RPC error."
   })

(def error-codes
  ^{:doc "JSON-RPC 2.0 standard error codes."}
  {:parse-error      -32700
   :invalid-request  -32600
   :method-not-found -32601
   :invalid-params   -32602
   :internal-error   -32603
   })

;;;;;;;
; Utils

(defn make-response [result id]
  "Given a a result object and an id, returns a json-rpc 2.0 result response"
  {:jsonrpc "2.0"
   :result result
   :id id
   })

(defn make-error [code id &[msg data]]
  "Given an error code and id, returns an json-rpc 2.0 error response"
  {:jsonrpc "2.0"
   :error {:code (error-codes code)
           :message (or msg (error-msgs code))
           :data (or data "")}
   :id id
   })

(defmacro defnlm
  "Defines a function that can take a list or a map."
  [fn-name & fn-tail]
  (let [[fn-name [args & body]] (name-with-attributes fn-name fn-tail)
        syms (map #(-> % name symbol) args)
        de-map {:keys (vec syms)}]
    `(defn ~fn-name
       [map-or-vec#]
       (cond
         (map? map-or-vec#) (let [~de-map map-or-vec#]
                              ~@body)
         :else (let [~args map-or-vec#]
                 ~@body)
         ))))


;;;;;;;;;;
; Dispatch

(defmulti dispatch 
  "Dispatches requests by method name"
  (fn [id method params] method))
(defmethod dispatch :default [id method params]
  (apply make-error [:method-not-found id]))

(defn defhandler [name function]
  "Simplifies the binding of functions to an exposed method name"
  (defmethod dispatch name [id method params]
     (apply make-response [(function params) id])))

