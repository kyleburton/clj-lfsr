script=${1:-examples/test.clj}
test -f clj-lfsr-standalone.jar || lein uberjar
java -cp clj-lfsr-standalone.jar clojure.main $script
