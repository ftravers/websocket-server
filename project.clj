(defproject fentontravers/websocket-server "0.1.8"
  :description "WebSocket Server Library"
  :url "https://github.com/ftravers/websocket-server"
  :license {:name "Eclipse Public License"
            :url "http://www.eclipse.org/legal/epl-v10.html"}
  :dependencies [[org.clojure/clojure "1.9.0-alpha12"]
                 [http-kit "2.2.0"]
                 [com.taoensso/timbre "4.8.0"]]
  :target-path "target/%s"
  :plugins [[lein-pprint "1.1.2"]]
  :release-tasks [["vcs" "assert-committed"]
                  ["change" "version"
                   "leiningen.release/bump-version" "release"]
                  ["vcs" "commit"]
                  ["vcs" "tag"]
                   ["deploy" "clojars"]
 ["change" "version" "leiningen.release/bump-version"]
 ["vcs" "commit"]
 ["vcs" "push"]
                  ]

  :repositories [["clojars" {:url "https://clojars.org/repo" :creds :gpg}]
                 ;; ["snapshots" {:url "https://clojars.org/repo" :creds :gpg}]
                 ;; ["releases" {:url "https://clojars.org/repo" :creds :gpg}]
                 ;; ["alternate" {:url "https://clojars.org/repo" :creds :gpg} ]
                 ]

  
  :profiles {:uberjar {:aot :all}})
