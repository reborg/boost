(ns boost.core-test
  (:use [midje.sweet]
        [boost.core])
  (:require [clojure.string :as s]))

(facts "optimal partitioning of coll into parts of the same size"
       (fact "no leftovers"
             (optimal-size (range 10) 2) => 5)
       (fact "single container"
             (optimal-size (range 10) 1) => 10)
       (fact "with leftovers"
             (optimal-size (range 100) 3) => 34))

(facts "expanding coll to a bigger size using a filler element"
       (fact "fill with nils"
             (resize [1 2 3] 5) => [1 2 3 nil nil])
       (fact "nothing to resize"
             (resize-all [1 2] [4 5]) => [[1 2] [4 5]])
       (fact "fill all with nils upto the biggest size"
             (resize-all [4 1 2] [1] [2]) => [[4 1 2] [1 nil nil] [2 nil nil]]))

(facts "interleave all, including collections of different sizes"
       (fact "just the same as interleave"
             (interleave-all [1 2] [3 4]) => [1 3 2 4])
       (fact "but returning the rest as well"
             (interleave-all [1 2] [3 4 5]) => [1 3 2 4 5])
       (fact "other direction included"
             (interleave-all [1 2 8] [3 4]) => [1 3 2 4 8])
       (fact "single collection"
             (interleave-all [1 2 8]) => [1 2 8])
       (fact "but even the middle"
             (interleave-all [1 2 3] [4 5] [6 7 8]) => [1 4 6 2 5 7 3 8]))

(facts "index of the first occurence of an item in coll"
       (fact "it is at the begnning" (index-of 0 (range 10)) => 0)
       (fact "it is a boolean" (index-of true [false false true false true]) => 2)
       (fact "it is at the end" (index-of 9 (range 10)) => 9)
       (fact "it's not there" (index-of 13 (range 10)) => -1))

(facts "swapping elements in a sequence"
       (fact "swap of empty coll is empty coll"
             (seq-swap [] 0 1) => [])
       (fact "simple swap"
             (seq-swap [1 2 3 4 5 6 7] 2 5) => [1 2 6 4 5 3 7])
       (fact "swap of continougs items"
             (seq-swap [1 2 3 4 5 6 7] 2 3) => [1 2 4 3 5 6 7])
       (fact "swap of binary coll"
             (seq-swap [1 2] 0 1) => [2 1])
       (fact "snteresting corner cases"
             (seq-swap [1 2] 1 1) => [1 2]
             (seq-swap [1 2] 0 0) => [1 2]
             (seq-swap [1 2] 2 3) => [1 2]
             (seq-swap [1 2] 0 3) => [1 2]
             (seq-swap [1 2] -2 -3) => [1 2]))

(facts "updating values in a map"
       (fact "keys are unaltered"
             (map-val inc {:a 0 :b 0}) => {:a 1 :b 1})
       (fact "passing an updating fn"
             (update-all {:a 0 :b 1 :c 2} [:b :c] inc) => {:a 0 :b 2 :c 3})
       (fact "passing an updating fn with additional args"
             (update-all {:a "hello" :b "ave" :c "hola"} [:b :c] str " renzo") => {:a "hello" :b "ave renzo" :c "hola renzo"}))

(facts "loading files"
       (fact "converts classpath name into abs path name"
             (.endsWith (abs-path "test.file") "test.file") => truthy))
