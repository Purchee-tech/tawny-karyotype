;; The contents of this file are subject to the LGPL License, Version 3.0.

;; Copyright (C) 2013, Newcastle University

;; This program is free software: you can redistribute it and/or modify
;; it under the terms of the GNU General Public License as published by
;; the Free Software Foundation, either version 3 of the License, or
;; (at your option) any later version.

;; This program is distributed in the hope that it will be useful,
;; but WITHOUT ANY WARRANTY; without even the implied warranty of
;; MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
;; GNU General Public License for more details.

;; You should have received a copy of the GNU General Public License
;; along with this program.  If not, see http://www.gnu.org/licenses/.

(ns ncl.karyotype.named_test
  (:use [clojure.test])
  (:require
   [ncl.karyotype.named :as n]
   [tawny.owl :as o]
   [tawny.reasoner :as r]))

(defn ontology-reasoner-fixture [tests]
  (r/reasoner-factory :hermit)
  (o/ontology-to-namespace n/named)
  (binding [r/*reasoner-progress-monitor*
            r/reasoner-progress-monitor-silent]
    (tests)))

(use-fixtures :once ontology-reasoner-fixture)

;; to run: M-x 'lein' 'test'

(deftest Basic
  (is (r/consistent?))
  (is (r/coherent?)))

;; TODO Conditional karyotypes
(deftest MaleKaryotype
  (is (r/isuperclass? n/k45_Y n/MaleKaryotype))
  (is (r/isuperclass? n/k46_XY n/MaleKaryotype))

  (is (not (r/isuperclass? n/k45_X n/FemaleKaryotype)))
  (is (not (r/isuperclass? n/k46_XX n/MaleKaryotype)))
  )

;; TODO Conditional karyotypes
(deftest FemaleKaryotype
  (is (r/isuperclass? n/k46_XX n/FemaleKaryotype))
  (is (r/isuperclass? n/k47_XXX n/FemaleKaryotype))

  ;; FAILS due to the current definition of Female
  ;;(is (r/isuperclass? n/k45_X n/FemaleKaryotype))

  (is (not (r/isuperclass? n/k45_X n/FemaleKaryotype)))
  )

(deftest NumericalAbnormalKaryotypeGain
  (is (r/isuperclass? n/k47_XXX n/NumericalAbnormalKaryotypeChromosomalGain))

  (is (not (r/isuperclass? n/k45_X n/NumericalAbnormalKaryotypeChromosomalGain)))
  (is (not (r/isuperclass? n/k46_XX n/NumericalAbnormalKaryotypeChromosomalGain)))
  )

(deftest NumericalAbnormalKaryotypeLoss
  (is (r/isuperclass? n/k45_X n/NumericalAbnormalKaryotypeChromosomalLoss))

  (is (not (r/isuperclass? n/k47_XXX n/NumericalAbnormalKaryotypeChromosomalLoss)))
  (is (not (r/isuperclass? n/k46_XX n/NumericalAbnormalKaryotypeChromosomalLoss)))
  )

(deftest NumericalAbnormalKaryotype
  (is (r/isuperclass? n/k47_XXX n/NumericalAbnormalKaryotype))
  (is (r/isuperclass? n/k45_X n/NumericalAbnormalKaryotype))

  (is (not (r/isuperclass? n/k46_XX n/NumericalAbnormalKaryotype)))
  )

(deftest StructuralAbnormalKaryotypeInsertion
  (is (r/isuperclass? n/k46_XX_ins!2!!q13p13p23! n/StructuralAbnormalKaryotypeInsertion))

  (is (not (r/isuperclass? n/k46_XX_inv!2!!p13p23! n/StructuralAbnormalKaryotypeInsertion)))
  (is (not (r/isuperclass? n/k46_XX n/StructuralAbnormalKaryotypeInsertion)))
  )

(deftest StructuralAbnormalKaryotypeInversion
  (is (r/isuperclass? n/k46_XX_inv!2!!p13p23! n/StructuralAbnormalKaryotypeInversion))

  (is (not (r/isuperclass? n/k46_XX_ins!2!!q13p13p23! n/StructuralAbnormalKaryotypeInversion)))  
  (is (not (r/isuperclass? n/k46_XX n/StructuralAbnormalKaryotypeInversion)))
  )

(deftest StructuralAbnormalKaryotype
  (is (r/isuperclass? n/k46_XX_ins!2!!q13p13p23! n/StructuralAbnormalKaryotype))
  (is (r/isuperclass? n/k46_XX_inv!2!!p13p23! n/StructuralAbnormalKaryotype))
  
  (is (not (r/isuperclass? n/k46_XX n/StructuralAbnormalKaryotype)))
  )

;; Probe classes

;; Test when tawny supports it!
;; (is (not
;;      (with-probe-class
;;        [joined (owlclass :subclass n/k46_XY n/k45_Y)]
;;        (r/consistent? joined))))


