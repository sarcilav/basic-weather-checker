(ns web-client.open-weather.core-test
  (:require [clojure.test :refer :all]
            [web-client.open-weather.core :refer :all]
            [mount.core :as mount]
            [web-client.config :refer [env]]))

(mount/start)

(deftest base-url-test
  (testing "building the base-url to open weather"
    (let [topic "weather"
          expected (str "http://api.openweathermap.org/data/2.5/weather")]
      (is (= expected (base-url topic))))))

(deftest create-query-string-test
  (testing "create-query-string generator is using the proper separators"
    (let [keys ["a" "b" "c" "d"]
          queries [1 2 3 4]
          expected ["a=1" "b=2" "c=3" "d=4"]]
      (is (= expected (create-query-string keys queries))))))

(deftest build-url-test
  (testing "build-url that attach the query to end of a base url"
    (let [base "example.com"
          query {"hello" "sample"
                 "a" "b"}
          expected (str base "?hello=sample&a=b")]
      (is (= expected (build-url base query))))))

(deftest weather-at-test
  (let [api-key (env :open-weather-key)]
    (testing "query to get a city weather report"
      (is (= :some (weather-at "berlin" api-key))))
    (testing "query to get lat/lon weather report"
      (is (= :some (weather-at 3.24 80.0 api-key))))))
