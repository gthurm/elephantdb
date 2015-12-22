(ns shardgen.core
   (:use [cascalog.api]
         [elephantdb.cascalog.keyval :only [keyval-tap]])
   (:import [elephantdb.persistence LevelDB]
            [elephantdb.partition HashModScheme])
   (:gen-class))

(defn long-to-a [x]
  (-> (java.nio.ByteBuffer/allocate 8)
      (.putLong x)
      (.array)))

(defn to-bytes [xs]
  (map (fn [[x y]] [(long-to-a x) (long-to-a y)]) xs))

(defn -main [domain-path]
  (?- (keyval-tap domain-path :spec { :num-shards 2 
                                      :coordinator (LevelDB.) 
                                      :shard-scheme (HashModScheme.) })
      (to-bytes (map (fn [x] [x (* x x)]) (range 100000)))))
