(ns picture-room.routes.events
  (:require
    [compojure.core :refer [defroutes GET]]
    [environ.core :refer [env]]
    ;; [fb-graph-clj.core :as fb]
    [ring.util.response :refer [response]]))

(def ac (env :access-token))

#_(fb/with-access-token
  ac
  (fb/pull [:pictureroomnyc :events]))

(defn fb-events []
  (let []
    (response {:test "foo"})))

(defroutes event-routes
           (GET "/events" [] (fb-events)))
