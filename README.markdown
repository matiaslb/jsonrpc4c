# jsonrpc4c

jsonrpc4c is a JSON-RPC library for the Clojure programming language.

## Usage

  (use 'ring.adapter.jetty)
  (require '[jsonrpc4c.rpc :as rpc])
  (require '[jsonrpc4c.server :as server])

  (rpc/defhandler "echo" identity)

  (run-jetty #'server/app {:port 9000})

Starts listening on port 9000 and provides the method "echo" that does nothing
but returning its arguments.


