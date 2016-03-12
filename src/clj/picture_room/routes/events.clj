(ns picture-room.routes.events
  (:require
    [compojure.core :refer [defroutes GET]]
    [environ.core :refer [env]]
    [clj-facebook-graph.auth :refer [with-facebook-auth]]
    [clj-facebook-graph [client :as client]]
    [ring.util.response :refer [response]]))

(def ac {:access-token (or (env :access-token) (System/getenv "ACCESS_TOKEN"))})

(defn get-fb-events []
  (let [events-request (with-facebook-auth ac (client/get [:pictureroomnyc :events] {:extract :data}))
        event-pic-urls-request (with-facebook-auth
                                  ac
                                  (client/get [:pictureroomnyc :events]
                                              {:extract :data
                                               :query-params {:fields "events,picture,url"}}))
        flatten-pic-urls (mapv #(:id %) event-pic-urls-request)
        join-requests (map (fn [e]
                            (let [index (.indexOf flatten-pic-urls (:id e))
                                  pic-url (get-in (get event-pic-urls-request index)
                                                      [:picture :data :url])]
                              (assoc e :picture_url pic-url))) events-request)]

    (response {:data join-requests})))

(defroutes event-routes
           (GET "/events" [] (get-fb-events)))
