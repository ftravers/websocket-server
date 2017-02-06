(ns websocket-server.core
  (:require [org.httpkit.server :refer [on-close on-receive run-server
                                        send! websocket? with-channel]]
            [taoensso.timbre :refer [spy trace debug]]
            [clojure.edn :refer [read-string]]))

(defn callback [data]
  (prn-str (spy :debug (read-string data))))

(defonce server (atom nil))

(defn websocket-handler [req]
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
           (send! channel (callback data))))))))

(defn start
  "Start websocket server"
  ([] (start 7890))
  ([port]
   (debug (str "Starting websocket on port: [" port "]"))
   (reset! server (run-server websocket-handler {:port port}))))

(defn stop "Stop websocket server" [] (@server))
(defn restart [] (stop) (start))
