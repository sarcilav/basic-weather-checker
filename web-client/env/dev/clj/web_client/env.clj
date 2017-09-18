(ns web-client.env
  (:require [selmer.parser :as parser]
            [clojure.tools.logging :as log]
            [web-client.dev-middleware :refer [wrap-dev]]))

(def defaults
  {:init
   (fn []
     (parser/cache-off!)
     (log/info "\n-=[web-client started successfully using the development profile]=-"))
   :stop
   (fn []
     (log/info "\n-=[web-client has shut down successfully]=-"))
   :middleware wrap-dev})
