set -e 
set -x
lein deps
lein jar
lein pom
# scp pom.xml clj-lfsr.jar clojars@clojars.org:
