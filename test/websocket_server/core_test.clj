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

(defn request-handler-edn-add10
  "This function will take some EDN and increment a value, and send it back."
  [data]
  (println "Received Data: " (str data))
  (let [req (edn/read-string data)
        resp (str {:count (+ 10 (:count req))})]
    (println "Sending Resp: " resp)
    resp))

(defn start
  "Demonstrate how to use the websocket server library."
  []
  (let [port 7890]
    ;; (reset! ws-server (start-ws-server port request-handler-upcase))
    (reset! ws-server (start-ws-server port request-handler-edn-add10))))

(defn stop "Stop websocket server" [] (@ws-server))

(defn restart [] (stop) (start))

