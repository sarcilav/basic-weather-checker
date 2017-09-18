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
  ([city-name api-key]
   (slurp (build-url (base-url "weather") {"appId" api-key "q" city-name}))))
