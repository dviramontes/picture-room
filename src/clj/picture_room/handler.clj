(ns picture-room.handler
  (:require [compojure.core :refer [routes wrap-routes]]
            [picture-room.layout :refer [error-page]]
            [picture-room.routes.home :refer [home-routes]]
            [picture-room.routes.events :refer [event-routes]]
            [compojure.route :as route]
            [picture-room.middleware :as middleware]))

(def app-routes
  (routes
    (wrap-routes #'event-routes middleware/wrap-csrf)
    (route/not-found
      (:body
        (error-page {:status 404
                     :title  "page not found"})))))

(def app (middleware/wrap-base #'app-routes))
