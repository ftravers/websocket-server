(ns websocket-server.core-test
  (:require [websocket-server.core :refer [start-ws-server]]
            [clojure.edn :as edn]))

;; After we start the server a function is returned
;; that we use for stopping the server.
(defonce ws-server (atom nil))

(defn request-handler-upcase
  "The function that will take incoming data off the websocket,
  process it and return a reponse.  In our case we'll simply UPPERCASE
  whatever is received."
  [data]
  (println "Received Data: " (str data))
  (let [resp (clojure.string/upper-case (str data))]
    (println "Sending Resp: " resp)
    resp))

(defn req-hndlr-add10 [data]
  (->> data
       edn/read-string
       :count
       (+ 10)
       (hash-map :count)
       str))

(defn start
  "Demonstrate how to use the websocket server library."
  []
  (let [port 7890]
    ;; (reset! ws-server (start-ws-server port request-handler-upcase))
    (reset! ws-server (start-ws-server port request-handler-edn-add10))))

(defn stop "Stop websocket server" [] (@ws-server))

(defn restart [] (stop) (start))

