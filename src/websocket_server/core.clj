(ns websocket-server.core
  (:require
   [org.httpkit.server :refer [on-close on-receive run-server send! websocket? with-channel]]
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
         (do
           (debug (str "Recieved data: " data))
           (let [resp (cb data)]
             (debug (str "Replying with: " resp))
             (send! channel resp))))))))

(defn start-ws-server [port callback]
  (run-server (partial websocket-server callback) {:port port}))


