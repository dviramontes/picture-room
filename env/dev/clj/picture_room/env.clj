(ns picture-room.env
  (:require [selmer.parser :as parser]
            [clojure.tools.logging :as log]
            [picture-room.dev-middleware :refer [wrap-dev]]))

(def defaults
  {:init
   (fn []
     (parser/cache-off!)
     (log/info "\n-=[picture-room started successfully using the development profile]=-"))
   :middleware wrap-dev})
