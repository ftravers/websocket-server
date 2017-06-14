(ns websocket-server.core
  (:require [org.httpkit.server :as http]
            [taoensso.timbre :as timbre]
            [clojure.core.async :as async]))

(defn websocket-server [callback req]
  (let [close-msg  (fn [status]
                     (timbre/debug
                      (str "Websocket channel closed with status: " status)))

        on-close   (http/on-close channel close-msg)

        on-data    (fn [data]
                     (when (http/websocket? channel)
                       (callback channel data)))

        on-receive (http/on-receive channel on-data)]

    (http/with-channel req channel on-close on-receive)))

(defn start-ws-server [port callback]
  (http/run-server (partial websocket-server callback) {:port port}))

(defn send! [channel data]
  (http/send! channel data))
