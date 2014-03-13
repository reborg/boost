(ns boost.core
  (:require [clojure.java.io :as io]))

;(set! *warn-on-reflection* true)

(defn optimal-size [coll n]
  "Given a collection coll and a desired number of containers n
  returns the optimal size of the container so items will
  be evenly distributed using, for example:
  (partition-all (optimal-size coll n) coll)"
  (let [size (quot (count coll) n)
        out (rem (count coll) size)]
    (if (zero? out) size (inc size))))

(defn resize [coll n]
  "Grow coll up to n items using nil fillers"
  (concat coll (take (- n (count coll)) (repeat nil))))

(defn resize-all [& colls]
  "Transform the list of collection colls so that
  all collections are of the same size using a nil-filler."
  (map #(resize % (apply max (map count colls))) colls))

(defn interleave-all [& colls]
  "Like interleave but include leftovers at the tail"
  (if (= 1 (count colls))
    (first colls)
    (remove nil? (apply interleave (apply resize-all colls)))))

(defn index-of [item coll]
  "Retrieve the index of the first occurence of item in coll"
  (let [res (count (take-while #(not= item %) coll))]
    (if (= res (count coll)) -1 res)))

(defn swap-head [coll item]
  "Returns new coll with item replacing first of coll"
  (cons item (rest coll)))

(defn seq-swap [coll i j]
  "Swaps item at i with item at j in the 0-based-index collection coll."
  (if (and (< i j) (<= j (count coll)))
    (let [[a b+c] (split-at i coll)
          [b c] (split-at (- j i) b+c)
          b-swap (swap-head b (first c))
          c-swap (swap-head c (first b))]
      (remove nil? (flatten (cons a (cons b-swap c-swap)))))
    coll))

(defn map-val [f m]
  "Applies f to all the values of the associative structure m:
   (map-val inc {:a 0 :b 0}) => {:a 1 :b 1}
  credits: http://stackoverflow.com/questions/1676891/mapping-a-function-on-the-values-of-a-map-in-clojure"
  (into {} (for [[k v] m] [k (f v)])))

;; File manipulation stuff

(defn abs-path [fname]
  "Given a resource accessible from the classpath,
  returns the absolute path of the resource"
  (.getPath (io/resource fname)))
