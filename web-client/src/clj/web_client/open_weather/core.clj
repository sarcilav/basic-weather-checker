(ns web-client.open-weather.core
  (:require [clojure.string :as str]
            [clojure.data.json :as json])
  (:import (java.net URL)))

(def open-weather-url "http://api.openweathermap.org/data/2.5")

(defn base-url [topic]
  (format "%s/%s" open-weather-url topic))

(defn create-query-string [queries]
  (map (fn [[k v]] (str k "=" v)) queries))

(defn build-url [url queries]
  (format "%s?%s" url (str/join "&" (create-query-string queries))))

(defn weather-at
  ([queries]
   #dbg
   (let [url-string (build-url (base-url "weather") queries)
         url (URL. url-string)
         conn (. url openConnection)]
     (with-open [stream (if (= (. conn getResponseCode) 200)
                          (. conn getInputStream)
                          (. conn getErrorStream))]
       (json/read-json (slurp stream)))))

  ([city-name api-key]
   (weather-at {"appId" api-key
                "q" city-name}))

  ([lat lon api-key]
   (weather-at {"appId" api-key
                "lat" lat
                "lon" lon})))
