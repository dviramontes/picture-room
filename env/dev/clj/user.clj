(ns user
  (:require [mount.core :as mount]
            picture-room.core))

(defn start []
  (mount/start-without #'picture-room.core/repl-server))

(defn stop []
  (mount/stop-except #'picture-room.core/repl-server))

(defn restart []
  (stop)
  (start))


