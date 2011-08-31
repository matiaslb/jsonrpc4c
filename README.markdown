# jsonrpc4c

jsonrpc4c is a JSON-RPC library for Clojure.

## Usage

    (use 'ring.adapter.jetty)
    (require '[jsonrpc4c.rpc :as rpc])
    (require '[jsonrpc4c.server :as server])
    
    (rpc/defhandler "echo" identity)
    
    (run-jetty #'server/app {:port 3000})

Starts listening on port 3000 and provides the method "echo" that does nothing
but return its arguments.


