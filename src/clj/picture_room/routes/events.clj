(ns picture-room.routes.events
  (:require
    [compojure.core :refer [defroutes GET]]
    [environ.core :refer [env]]
    [fb-graph-clj.core :as fb]
    ;; [ring.util.http-response :as res]
    [ring.util.response :refer [response]]))

(def ac (or (env :access-token) (System/getenv "ACCESS_TOKEN")))

(defn fb-events []
  (let [events (fb/with-access-token ac (fb/pull [:pictureroomnyc :events]))]
    (response (:body events))))

(defroutes event-routes
           (GET "/events" [] (fb-events)))
