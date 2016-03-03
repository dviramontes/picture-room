(ns picture-room.env
  (:require [clojure.tools.logging :as log]))

(def defaults
  {:init
   (fn []
     (log/info "\n-=[picture-room started successfully]=-"))
   :middleware identity})
