(ns websocket-server.core
  (:require [org.httpkit.server :as http]
            [taoensso.timbre :as timbre]))

(defn websocket-server [cb req]
  (http/with-channel req channel
    (http/on-close
     channel
     (fn [status] (timbre/debug (str "Websocket channel closed with status: " status))))
    (http/on-receive
     channel
     (fn [data]
       (if (http/websocket? channel)
         (cb channel data))))))

(defn start-ws-server [port callback]
  (http/run-server (partial websocket-server callback) {:port port}))

(defn send! [channel data]
  (http/send! channel data))
