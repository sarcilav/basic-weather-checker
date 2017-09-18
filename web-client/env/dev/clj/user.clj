(ns user
  (:require [mount.core :as mount]
            web-client.core))

(defn start []
  (mount/start-without #'web-client.core/repl-server))

(defn stop []
  (mount/stop-except #'web-client.core/repl-server))

(defn restart []
  (stop)
  (start))


