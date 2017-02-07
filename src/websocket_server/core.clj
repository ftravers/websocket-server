(ns websocket-server.core
  (:require [org.httpkit.server :refer [on-close on-receive run-server
                                        send! websocket? with-channel]]
            [taoensso.timbre :refer [spy trace debug]]
            [clojure.edn :refer [read-string]]))

(defn websocket-server [cb req]
  (with-channel req channel
    (on-close
     channel
     (fn [status] (debug (str "Websocket channel closed with status: " status))))
    (on-receive
     channel
     (fn [data]
       (if (websocket? channel)
         (do
           (trace (str "Recieved data: " data))
           (send! channel (cb data))))))))

(defn start-ws-server [port callback]
  (run-server (partial websocket-server callback) {:port port}))


