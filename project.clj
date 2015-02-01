(defproject com.github.kyleburton/clj-lfsr "1.3.2"
  :description        "LFSR Library"
  :url                "http://github.com/kyleburton/clj-lfsr"
  :lein-release         {:deploy-via :clojars}
  :license              {:name "Eclipse Public License - v 1.0"
                         :url "http://www.eclipse.org/legal/epl-v10.html"
                         :distribution :repo
                         :comments "Same as Clojure"}
  :repositories         {"sonatype-oss-public" "https://oss.sonatype.org/content/groups/public/"}
  :local-repo-classpath true
  :profiles             {:dev {:dependencies [[org.clojure/tools.nrepl                "0.2.3"]
                                              [org.clojure/clojure                    "1.6.0"]]}
                         ;; NB: the use of ex-info prevents 1.3 from being supported
                         :1.2 {:dependencies [[org.clojure/clojure "1.2.0"]]}
                         :1.3 {:dependencies [[org.clojure/clojure "1.3.0"]]}
                         :1.4 {:dependencies [[org.clojure/clojure "1.4.0"]]}
                         :1.5 {:dependencies [[org.clojure/clojure "1.5.1"]]}
                         :1.6 {:dependencies [[org.clojure/clojure "1.6.0"]]}}
  :aliases              {"all" ["with-profile" "1.2:1.3:1.4:1.5:1.6"]}
  :global-vars          {*warn-on-reflection* true}
  :dependencies         [])
