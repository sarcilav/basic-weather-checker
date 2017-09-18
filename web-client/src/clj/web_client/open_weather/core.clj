(ns web-client.open-weather.core
  (:require [clojure.string :as str]))

(def open-weather-url "http://api.openweathermap.org/data/2.5")

(defn base-url [topic api-key]
  (format "%s/%s?appId=%s" open-weather-url topic api-key))

(defn build-url [url query]
  (str/join "&" (cons url query)))

(defn weather-at
  ([city-name api-key]
   (slurp (build-url (base-url "weather" api-key) [(str "q=" city-name)]))))
