(ns web-client.open-weather.core-test
  (:require [clojure.test :refer :all]
            [web-client.open-weather.core :refer :all]
            [mount.core :as mount]
            [web-client.config :refer [env]]))

(mount/start)

(deftest base-url-test
  (testing "building the base-url to open weather"
    (let [api-key (env :open-weather-key)
          topic "weather"
          expected (str "http://api.openweathermap.org/data/2.5/weather?appId=" api-key)]
      (is (= expected (base-url topic api-key))))))

(deftest build-url-test
  (testing "build-url that attach the query to end of a base url"
    (let [base "example.com"
          query ["hello" "sample"]
          expected (str base "&hello&sample")]
      (is (= expected (build-url base query))))))

(deftest weather-at-test
  (testing "query to get a city weather report"
    (let [api-key (env :open-weather-key)
          good-cities '(medellin berlin)]
      (is (= :some (weather-at (first good-cities) api-key ))))))
