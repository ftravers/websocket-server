(defproject fentontravers/websocket-server "0.4.1"
  :description "WebSocket Server Library"
  :url "https://github.com/ftravers/websocket-server"
  :license {:name "Eclipse Public License"
            :url "http://www.eclipse.org/legal/epl-v10.html"}
  :dependencies [[org.clojure/clojure "1.9.0-alpha12"]
                 [http-kit "2.2.0"]
                 [com.taoensso/timbre "4.8.0"]
                 [org.clojure/core.async "0.2.395"]]
  :target-path "target/%s"

  :repositories [["clojars" {:url "https://clojars.org/repo" :creds :gpg}]
                 ["snapshots" {:url "https://clojars.org/repo" :creds :gpg}]
                 ["releases" {:url "https://clojars.org/repo" :creds :gpg}]
                 ["alternate" {:url "https://clojars.org/repo" :creds :gpg}]]
  
  :profiles {:uberjar {:aot :all}})
