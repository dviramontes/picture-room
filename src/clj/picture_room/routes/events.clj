(ns picture-room.routes.events
  (:require
    [compojure.core :refer [defroutes GET]]
    [ring.util.http-response :as response]))

(defn home-page []
  (layout/render
    "home.html" {:docs (-> "docs/docs.md" io/resource slurp)}))

(defn about-page []
  (layout/render "about.html"))

(defroutes home-routes
           (GET "/" [] (home-page))
           (GET "/about" [] (about-page)))


