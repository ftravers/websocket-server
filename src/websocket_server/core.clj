(ns websocket-server.core
  (:require [org.httpkit.server :as http]
            [taoensso.timbre :as log]))

(defn websocket-server [cb req]
  (http/with-channel req channel
    (http/on-close
     channel
     (fn [status] (log/debug (str "Websocket channel closed with status: " status))))
    (http/on-receive
     channel
     (fn [data]
       (if (http/websocket? channel)
         (let [resp (cb data)]
           (log/debug "RECV: " data)
           (log/debug "RESP: " resp)
           (http/send! channel resp)))))))

(defn start-ws-server [port callback]
  (http/run-server (partial websocket-server callback)
                   {:port port}))

