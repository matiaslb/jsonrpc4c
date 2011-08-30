(ns jsonrpc4c.rpc-test
  (:use jsonrpc4c.rpc
        clojure.test))

(deftest dispatch-test-1
  (do 
    (defhandler "echo-test" identity)
    (is (= {:jsonrpc "2.0", :result 42, :id 1} (dispatch 1 "echo-test" 42)))))

