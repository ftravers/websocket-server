(ns websocket-server.core
  (:require
   [org.httpkit.server :as hk :refer [on-close on-receive run-server websocket? with-channel]]
   [taoensso.timbre :refer [spy trace debug]]
   [clojure.edn :refer [read-string]]
   [clojure.core.async :as async :refer :all]))

(defn websocket-server [cb req]
  (with-channel req channel
    (on-close
     channel
     (fn [status] (debug (str "Websocket channel closed with status: " status))))
    (on-receive
     channel
     (fn [data]
       (if (websocket? channel)
         (cb channel data))))))

(defn start-ws-server [port callback]
  (run-server (partial websocket-server callback) {:port port}))

(defn send! [channel data] (hk/send! channel data))
