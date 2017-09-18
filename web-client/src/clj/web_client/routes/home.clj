(ns web-client.routes.home
  (:require [web-client.layout :as layout]
            [compojure.core :refer [defroutes GET POST]]
            [ring.util.http-response :as response]
            [clojure.java.io :as io]
            [web-client.config :refer [env]]
            [web-client.open-weather.core :refer [weather-at]]))

(defn weather-page [weather]
  (layout/render "home.html" {:response weather}))

(defn about-page []
  (layout/render "about.html"))

(defroutes home-routes
  (GET "/" [] (weather-page nil))
  (POST "/weather" req (weather-page (weather-at (into {:appId (env :open-weather-key)}
                                                       (select-keys (:params req) [:q :lat :lon])))))
  (GET "/about" [] (about-page)))

