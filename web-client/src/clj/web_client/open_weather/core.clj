(ns web-client.open-weather.core
  (:require [clojure.string :as str]))

(def open-weather-url "http://api.openweathermap.org/data/2.5")

(defn base-url [topic]
  (format "%s/%s" open-weather-url topic))

(defn create-query-string [queries]
  (map (fn [[k v]] (format "%s=%s" k v)) queries))

(defn build-url [url queries]
  (format "%s?%s" url (str/join "&" (create-query-string queries))))

(defn weather-at
  ([queries]
   (slurp (build-url (base-url "weather") queries)))
  ([city-name api-key]
   (weather-at {"appId" api-key
                "q" city-name}))
  ([lat lon api-key]
   (weather-at {"appId" api-key
                "lat" lat
                "lon" lon})))
