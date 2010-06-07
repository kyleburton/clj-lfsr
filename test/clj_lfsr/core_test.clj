(ns clj-lfsr.core-test
  (:require [clj-lfsr.core :as core])
  (:use clojure.test))

;; > bash run-simple.sh -L info -s -S "4 [4 3]"
;; [INFO]  simple-lfsr.c(124) 4 [4 3] => 4 : [4 3]
;; [INFO]  simple-lfsr.c(109) [SUCCESS] re-reached start, period=15(15) start=1 tap_mask=12/0xC [1100]
;; [4]                    1 : 0001 START
;; [4]                   12 : 1100                    1
;; [4]                    6 : 0110                    2
;; [4]                    3 : 0011                    3
;; [4]                   13 : 1101                    4
;; [4]                   10 : 1010                    5
;; [4]                    5 : 0101                    6
;; [4]                   14 : 1110                    7
;; [4]                    7 : 0111                    8
;; [4]                   15 : 1111                    9
;; [4]                   11 : 1011                   10
;; [4]                    9 : 1001                   11
;; [4]                    8 : 1000                   12
;; [4]                    4 : 0100                   13
;; [4]                    2 : 0010                   14
;; [4]                    1 : 0001                   15
;; [4]                    1 : 0001 END
(def *lfsr-4-4_3* [1 12 6 3 13 10 5 14 7 15 11 9 8 4 2])

(deftest test-make-mask
  (is (=         0x0 (core/make-mask [])))
  (is (=         0x1 (core/make-mask [1])))
  (is (=         0x2 (core/make-mask [2])))
  (is (=         0x4 (core/make-mask [3])))
  (is (=         0x8 (core/make-mask [4])))
  (is (=        0x10 (core/make-mask [5])))
  (is (=  0x80000000 (core/make-mask [32]))))


(deftest test-lfsr-seq
  (is (= *lfsr-4-4_3* (map :state (core/lfsr-seq 1 [4 3])))))

;; (take 3 (core/lfsr-seq 321 [321 319 316 314]))
;; (deftest test-lfsr-seq!
;;   (is (thrown? IllegalStateException
;;                (take 100 (core/lfsr-seq! 1 [4 3])))))

;; (run-all-tests)

