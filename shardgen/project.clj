(def ROOT-DIR (subs *file* 0 (- (count *file*) (count "project.clj"))))
(def VERSION (-> ROOT-DIR (str "/../VERSION") slurp))
(def CASCALOG-VERSION "2.1.0")

(defproject shardgen VERSION
  :description "created shards for testing"
  :dependencies [[org.clojure/clojure "1.5.1"]
                 [elephantdb/elephantdb-bdb ~VERSION]
                 [elephantdb/elephantdb-leveldb ~VERSION]
                 [elephantdb/elephantdb-cascalog ~VERSION]
                 [org.apache.hadoop/hadoop-core "1.2.1"]
                 [cascalog/cascalog-core ~CASCALOG-VERSION]
                ]
  :source-paths ["src/clj"]
  :aot :all
  :main shardgen.core)
