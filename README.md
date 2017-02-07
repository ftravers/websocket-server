<div id="table-of-contents">
<h2>Table of Contents</h2>
<div id="text-table-of-contents">
<ul>
<li><a href="#sec-1">1. websocket-server</a></li>
<li><a href="#sec-2">2. Clojars</a></li>
<li><a href="#sec-3">3. Usage</a></li>
</ul>
</div>
</div>

# websocket-server<a id="sec-1" name="sec-1"></a>

This is the server side of the websocket connection.  This is meant to
be paired with [fentontravers/websocket-client](https://clojars.org/fentontravers/websocket-client).

# Clojars<a id="sec-2" name="sec-2"></a>

    [fentontravers/websocket-server "0.1.9"]

# Usage<a id="sec-3" name="sec-3"></a>

    (require '[websocket-server.core :refer [start-ws-server]])
    
    ;; After we start the server a function is returned
    ;; that we use for stopping the server.
    (defonce ws-server (atom nil))
    
    (defn request-handler
      "The function that will take incoming data off the websocket,
      process it and return a reponse.  In our case we'll simply UPPERCASE
      whatever is received."
      [data] (clojure.string/upper-case (str data)))
    
    (defn start
      "Demonstrate how to use the websocket server library."
      []
      (let [port 8899]
        (reset! ws-server (start-ws-server port request-handler))))
    
    (defn stop "Stop websocket server" [] (@server))