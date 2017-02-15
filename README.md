# WebSocket Server

This is the server side of the websocket connection.  This is meant to
be paired with [fentontravers/websocket-client](https://github.com/ftravers/websocket-client).

# Clojars

![](https://clojars.org/fentontravers/websocket-server/latest-version.svg)
  
# Usage

```clojure
(require '[websocket-server.core :refer [start-ws-server]])

;; After we start the server a function is returned
;; that we use for stopping the server.
(defonce ws-server (atom nil))

(defn request-handler-upcase-string
  "The function that will take incoming data off the websocket,
  process it and return a reponse.  In our case we'll simply UPPERCASE
  whatever is received."
  [data] (clojure.string/upper-case (str data)))

(defn start
  "Demonstrate how to use the websocket server library."
  []
  (let [port 8899]
    (reset! ws-server (start-ws-server port request-handler-upcase-string))))

(defn stop "Stop websocket server" [] (@ws-server))
```
  
Here is another example that expects EDN in the form of a map that
looks like `{:count 1}`, or just a map with a key `:count` and some
integer value.  Then it increments that value by 10 and returns it
back.

```clojure
(defn request-handler-add10 
  [data]
  (->> data
       edn/read-string
       :count
       (+ 10)
       (hash-map :count)
       str))
```
