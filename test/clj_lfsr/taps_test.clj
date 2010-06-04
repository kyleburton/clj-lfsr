(ns clj-lfsr.taps-test
  (:require
   [clj-lfsr.taps :as lfsr-taps])
  (:use [clojure.test]))

(deftest should-get-bit-size
  (testing "LFSR Tap Database"
    (testing "a valid number of taps and bit size"
      (is (= [32 30 26 25] (:taps (lfsr-taps/lfsr-for-bit-size 4 32))))
      (is (= [31 28]       (:taps (lfsr-taps/lfsr-for-bit-size 2 31)))))
    (testing "an invalid number of taps or bit size"
      (is (not (lfsr-taps/lfsr-for-bit-size 0 0)))
      (is (not (lfsr-taps/lfsr-for-bit-size 4 4097))))))




