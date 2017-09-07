(defproject com.github.kyleburton/clj-lfsr "1.3.4"
  :description        "LFSR Library"
  :url                "http://github.com/kyleburton/clj-lfsr"
  :license              {:name "Eclipse Public License - v 1.0"
                         :url "http://www.eclipse.org/legal/epl-v10.html"
                         :distribution :repo
                         :comments "Same as Clojure"}
  :deploy-repositories [
                 ["releases"  {:url "https://clojars.org/repo" :creds :gpg}]
                 ["snapshots" {:url "https://clojars.org/repo" :creds :gpg}]]
  :local-repo-classpath true
  :global-vars          {*warn-on-reflection* true}
  :dependencies         [[org.clojure/clojure                    "1.8.0"]])
