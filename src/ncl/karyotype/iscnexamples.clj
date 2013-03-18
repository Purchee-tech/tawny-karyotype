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

(ns ncl.karyotype.iscnexamples
  (:use [tawny.owl])
  (:require [tawny [reasoner :as r]]
            [ncl.karyotype [karyotype :as k]]
            [ncl.karyotype [human :as h]]
            [ncl.karyotype [events :as e]]
            [ncl.karyotype [features :as f]]
            [ncl.karyotype [named :as n]]
            )
  )

(defontology iscnexamples
  :iri "http://ncl.ac.uk/karyotype/iscnexamples"
  :prefix "iex:"
  )

;; import all ncl.karyotype axioms
(owlimport k/karyotype)
(owlimport h/human)
(owlimport e/events)
(owlimport n/named)

(defclass ISCNExampleKaryotype
  :subclass k/Karyotype)

;; Define the namedKaryotypes

;; TODO Need to include disjoint axioms

;; example numerical allosomal abnormal karyotypes
(defclass k45_X
  :label "The 45,X karyotype"
  :subclass ISCNExampleKaryotype
  (owlsome n/derivedFrom n/k46_XN)
  (e/deletion 1 h/HumanAllosome))
(defclass k45_Y
  :label "The 45,Y karyotype"
  :subclass ISCNExampleKaryotype
  (owlsome n/derivedFrom n/k46_XY)
  (e/deletion 1 h/HumanChromosomeX))
(defclass k46_YY
  :label "The 46,YY karyotype"
  :subclass ISCNExampleKaryotype
  (owlsome n/derivedFrom n/k46_XY)
  (e/deletion 1 h/HumanChromosomeX)
  (e/addition 1 h/HumanChromosomeY))
(defclass k47_XXX
  :label "The 47,XXX karyotype"
  :subclass ISCNExampleKaryotype
  (owlsome n/derivedFrom n/k46_XX)
  (e/addition 1 h/HumanChromosomeX))
(defclass k47_XXY
  :label "The 47,XXY karyotype"
  :subclass ISCNExampleKaryotype
  (owlsome n/derivedFrom n/k46_XY)
  (e/addition 1 h/HumanChromosomeX))
(defclass k47_XYY
  :label "The 47,XYY karyotype"
  :subclass ISCNExampleKaryotype
  (owlsome n/derivedFrom n/k46_XY)
  (e/addition 1 h/HumanChromosomeY))
(defclass k47_YYY
  :label "The 47,YYY karyotype"
  :subclass ISCNExampleKaryotype
  (owlsome n/derivedFrom n/k46_XY)
  (e/addition 2 h/HumanChromosomeY)
  (e/deletion 1 h/HumanChromosomeX))
(defclass k48_XXXX
  :label "The 48,XXXX karyotype"
  :subclass ISCNExampleKaryotype
  (owlsome n/derivedFrom n/k46_XX)
  (e/addition 2 h/HumanChromosomeX))
(defclass k48_XXXY
  :label "The 48,XXXY karyotype"
  :subclass ISCNExampleKaryotype
  (owlsome n/derivedFrom n/k46_XY)
  (e/addition 2 h/HumanChromosomeX))
(defclass k48_XXYY
  :label "The 48,XXYY karyotype"
  :subclass ISCNExampleKaryotype
  (owlsome n/derivedFrom n/k46_XY)
  (e/addition 1 h/HumanChromosomeX)
  (e/addition 1 h/HumanChromosomeY))
(defclass k48_XYYY
  :label "The 48,XYYY karyotype"
  :subclass ISCNExampleKaryotype
  (owlsome n/derivedFrom n/k46_XY)
  (e/addition 2 h/HumanChromosomeY))
(defclass k48_YYYY
  :label "The 48,YYYY karyotype"
  :subclass ISCNExampleKaryotype
  (owlsome n/derivedFrom n/k46_XY)
  (e/addition 3 h/HumanChromosomeY)
  (e/deletion 1 h/HumanChromosomeX))
(defclass k49_XXXXX
  :label "The 49,XXXXX karyotype"
  :subclass ISCNExampleKaryotype
  (owlsome n/derivedFrom n/k46_XX)
  (e/addition 3 h/HumanChromosomeX))
(defclass k49_XXXXY
  :label "The 49,XXXXY karyotype"
  :subclass ISCNExampleKaryotype
  (owlsome n/derivedFrom n/k46_XY)
  (e/addition 3 h/HumanChromosomeX))
(defclass k49_XXXYY
  :label "The 49,XXXYY karyotype"
  :subclass ISCNExampleKaryotype
  (owlsome n/derivedFrom n/k46_XY)
  (e/addition 1 h/HumanChromosomeY)
  (e/addition 2 h/HumanChromosomeX))
(defclass k49_XXYYY
  :label "The 49,XXYYY karyotype"
  :subclass ISCNExampleKaryotype
  (owlsome n/derivedFrom n/k46_XY)
  (e/addition 2 h/HumanChromosomeY)
  (e/addition 1 h/HumanChromosomeX))
(defclass k49_XYYYY
  :label "The 49,XYYYY karyotype"
  :subclass ISCNExampleKaryotype
  (owlsome n/derivedFrom n/k46_XY)
  (e/addition 3 h/HumanChromosomeY))
(defclass k49_YYYYY
  :label "The 49,YYYYY karyotype"
  :subclass ISCNExampleKaryotype
  (owlsome n/derivedFrom n/k46_XY)
  (e/addition 4 h/HumanChromosomeY)
  (e/deletion 1 h/HumanChromosomeX))
(defclass k47_XX_+X
  :label "The 47,XX,+X karyotype"
  :subclass ISCNExampleKaryotype
  (owlsome n/derivedFrom n/k46_XX)
  (e/addition 1 h/HumanChromosomeX))
(defclass k45_X_-X
  :label "The 45,X,-X karyotype"
  :subclass ISCNExampleKaryotype
  (owlsome n/derivedFrom n/k46_XX)
  (e/deletion 1 h/HumanChromosomeX))
(defclass k45_X_-Y
  :label "The 45,X,-Y karyotype"
  :subclass ISCNExampleKaryotype
  (owlsome n/derivedFrom n/k46_XY)
  (e/deletion 1 h/HumanChromosomeY))
(defclass k45_Y_-X
  :label "The 45,Y,-X karyotype"
  :subclass ISCNExampleKaryotype
  (owlsome n/derivedFrom n/k46_XY)
  (e/deletion 1 h/HumanChromosomeX))
(defclass k48_XY_+X_+Y
  :label "The 48,XY,+X,+Y karyotype"
  :subclass ISCNExampleKaryotype
  (owlsome n/derivedFrom n/k46_XY)
  (e/addition 1 h/HumanChromosomeX)
  (e/addition 1 h/HumanChromosomeY))


;; example ploidy karyotypes
;; example haploid karyotype
(defclass k26_X_+4_+6_+21
  :label "The 26,X,+4,+6,+21 karyotype"
  :subclass ISCNExampleKaryotype
  (owlsome n/derivedFrom n/k23_X)
  (e/addition 1 h/HumanChromosome4)
  (e/addition 1 h/HumanChromosome6)
  (e/addition 1 h/HumanChromosome21))
;; example triploid karyotype
;; TODO n/derivedFrom
(defclass k71_XXX_+8_+10
  :label "The 71,XXX,+8,+10 karyotype"
  :subclass ISCNExampleKaryotype
  (e/addition 1 h/HumanChromosome8)
  (e/addition 1 h/HumanChromosome10))
;; example tetraploid karyotype
;; TODO n/derivedFrom
(defclass k89_XXYY_-1_-3_-5_+8_-21
  :label "The 89,XXYY,-1,-3,-5,+8,-21 karyotype"
  :subclass ISCNExampleKaryotype
  (e/addition 1 h/HumanChromosome8)
  (e/deletion 1 h/HumanChromosome1)
  (e/deletion 1 h/HumanChromosome3)
  (e/deletion 1 h/HumanChromosome5)
  (e/deletion 1 h/HumanChromosome21))



;; example numerical autosomal abnormal karyotypes
(defclass k45_XX_-22
  :label "The 45,XX,-22 karyotype"
  :subclass ISCNExampleKaryotype
  (owlsome n/derivedFrom n/k46_XX)
  (e/deletion 1 h/HumanChromosome22))
(defclass k46_XX_+8_-21
  :label "The 46,XX,+8,-21 karyotype"
  :subclass ISCNExampleKaryotype
  (owlsome n/derivedFrom n/k46_XX)
  (e/addition 1 h/HumanChromosome8)
  (e/deletion 1 h/HumanChromosome21))
(defclass k47_XX_+21
  :label "The 47,XX,+21 karyotype"
  :subclass ISCNExampleKaryotype
  (owlsome n/derivedFrom n/k46_XX)
  (e/addition 1 h/HumanChromosome21))
(defclass k48_XX_+13_+21
  :label "The 48,XX,+13,+21 karyotype"
  :subclass ISCNExampleKaryotype
  (owlsome n/derivedFrom n/k46_XX)
  (e/addition 1 h/HumanChromosome13)
  (e/addition 1 h/HumanChromosome21))



;; example conditional karyotypes
(defclass k48_XXYc_+X
  :label "The 48,XXYc,+X karyotype"
  :subclass ISCNExampleKaryotype
  (owlsome n/derivedFrom (owland (owlsome n/derivedFrom n/k46_XY) (e/addition 1 h/HumanChromosomeX))) ;;aka 47,XXY
  (e/addition 1 h/HumanChromosomeX))
(defclass k46_Xc_+X
  :label "The 46,Xc,+X karyotype"
  :subclass ISCNExampleKaryotype
  (owlsome n/derivedFrom (owland (owlsome n/derivedFrom n/k46_XN) (e/deletion 1 h/HumanAutosome))) ;;aka 45,X
  (e/addition 1 h/HumanChromosomeX))
(defclass k46_XXYc_-X
  :label "The 46,XXYc,-X karyotype"
  :subclass ISCNExampleKaryotype
  (owlsome n/derivedFrom (owland (owlsome n/derivedFrom n/k46_XY) (e/addition 1 h/HumanChromosomeX))) ;;aka 47,XXY    
  (e/deletion 1 h/HumanChromosomeX))
(defclass k44_Xc_-X
  :label "The 44,Xc,-X karyotype"
  :subclass ISCNExampleKaryotype
  (owlsome n/derivedFrom (owland (owlsome n/derivedFrom n/k46_XN) (e/deletion 1 h/HumanAllosome))) ;;aka 45,X 
  (e/deletion 1 h/HumanChromosomeX))
(defclass k46_Xc_+21
  :label "The 46,Xc,+21 karyotype"
  :subclass ISCNExampleKaryotype
  (owlsome n/derivedFrom (owland (owlsome n/derivedFrom n/k46_XN) (e/deletion 1 h/HumanAllosome))) ;;aka 45,X
  (e/addition 1 h/HumanChromosome21))
(defclass k48_XY_+21c_+21
  :label "The 48,XY,+21c,+21 karyotype"
  :subclass ISCNExampleKaryotype
  (owlsome n/derivedFrom (owland (owlsome n/derivedFrom n/k46_XY) (e/addition 1 h/HumanChromosome21))) ;;aka 47,XY,+21
  (e/addition 1 h/HumanChromosome21))
(defclass k46_XY_+21c_-21
  :label "The 46,XY,+21c,-21 karyotype"
  :subclass ISCNExampleKaryotype
  (owlsome n/derivedFrom (owland (owlsome n/derivedFrom n/k46_XY) (e/addition 1 h/HumanChromosome21))) ;;aka 47,XY,+21 
  (e/deletion 1 h/HumanChromosome21))



;; example structural abnormal karyotypes

;; ADDITION
(defclass k46_XX_add!19!!p13.3!
  :label "The 46,XX,add(19)(p13.3) karyotype"
  :subclass ISCNExampleKaryotype
  (owlsome n/derivedFrom n/k46_XX)
  (e/addition 1 h/HumanChromosome19Bandp13.3))

(defclass k46_XY_add!12!!q13!
  :label "The 46,XY,add(12)(q13) karyotype"
  :subclass ISCNExampleKaryotype
  (owlsome n/derivedFrom n/k46_XY)
  (e/addition 1 h/HumanChromosome12Bandq13))

;; DELETION
(defclass k46_XX_del!5!!q13!
  :label "The 46,XX,del(5)(q13) karyotype"
  :subclass ISCNExampleKaryotype
  (owlsome n/derivedFrom n/k46_XX)
  (e/deletion 1 h/HumanChromosome5Bandq13))

(defclass k46_XX_del!5!!q13q33!
  :label "The 46,XX,del(5)(q13q33) karyotype"
  :subclass ISCNExampleKaryotype
  (owlsome n/derivedFrom n/k46_XX)
  (e/deletion 1 h/HumanChromosome5Bandq13 h/HumanChromosome5Bandq33))

(defclass k46_XX_del!5!!q13q13!
  :label "The 46,XX,del(5)(q13q13) karyotype"
  :subclass ISCNExampleKaryotype
  (owlsome n/derivedFrom n/k46_XX)
  (e/deletion 1 h/HumanChromosome5Bandq13 h/HumanChromosome5Bandq13))

(defclass k46_Y_del!X!!p21p21!
  :label "The 46,Y,del(X)(p21p21) karyotype"
  :subclass ISCNExampleKaryotype
  (owlsome n/derivedFrom n/k46_XY)
  (e/deletion 1 h/HumanChromosomeXBandp21 h/HumanChromosomeXBandp21))

;; DUPLICATIONS
(defclass k46_XX_dup!1!!q22q25!
  :label "The 46,XX,dup(1)(q22q25) karyotype"
  :subclass ISCNExampleKaryotype
  (owlsome n/derivedFrom n/k46_XX)
  (e/direct_duplication 1 h/HumanChromosome1Bandq22 h/HumanChromosome1Bandq25))

;; NOTE: Only the detailed system will clarify the location of the duplicated segment
;; AKA pterq25q25q22q25qter or pterq22q25q22q22qter
(defclass k46_XY_dup!1!!q25q22!
  :label "The 46,XY,dup(1)(q25q22) karyotype"
  :subclass ISCNExampleKaryotype
  (owlsome n/derivedFrom n/k46_XY)
  (e/inverse_duplication 1 h/HumanChromosome1Bandq25 h/HumanChromosome1Bandq22))

;; FISSION
(defclass k47_XY_-10_+fis!10!!p10!_+fis!10!!q10!
  :label "The 47,XY,-10,+fis(10)(p10),+fis(10)(q10) karyotype"
  :subclass ISCNExampleKaryotype
  (owlsome n/derivedFrom n/k46_XY)
  (flatten (e/fission 1 h/HumanChromosome10))) ;; TODO: include 'addition' event

;; INVERSION
(defclass k46_XX_inv!2!!p21q31!
  :label "The 46,XX,inv(2)(p21q31) karyotype"
  :subclass ISCNExampleKaryotype
  (owlsome n/derivedFrom n/k46_XX)
  (e/inversion 1 h/HumanChromosome2Bandp21 h/HumanChromosome2Bandq31))

(defclass k46_XX_inv!2!!p13p23!
  :label "The 46,XX,inv(2)(p13p23) karyotype"
  :subclass ISCNExampleKaryotype
  (owlsome n/derivedFrom n/k46_XX)
  (e/inversion 1 h/HumanChromosome2Bandp13 h/HumanChromosome2Bandp23))

(defclass k46_XX_inv!3!!q21q26.2!
  :label "The 46,XX,inv(3)(q21q26.2) karyotype"
  :subclass ISCNExampleKaryotype
  (owlsome n/derivedFrom n/k46_XX)
  (e/inversion 1 h/HumanChromosome3Bandq21 h/HumanChromosome3Bandq26.2))

(defclass k46_XY_inv!3!!p13q21!
  :label "The 46,XY,inv(3)(p13q21) karyotype"
  :subclass ISCNExampleKaryotype
  (owlsome n/derivedFrom n/k46_XY)
  (e/inversion 1 h/HumanChromosome3Bandp13 h/HumanChromosome3Bandq21))

;; INSERTIONS
(defclass k46_X_ins!5_X!!p14_q21q25!
  :label "The 46,X,ins(5;X)(p14;q21q25) karyotype"
  :subclass ISCNExampleKaryotype
  (owlsome n/derivedFrom n/k46_XX)
  (e/direct_insertion 1 h/HumanChromosome5Bandp14 h/HumanChromosomeXBandq21 h/HumanChromosomeXBandq25))

(defclass k46_XX_ins!2!!q13p13p23!
  :label "The 46,XX,ins(2)(q13p13p23) karyotype"
  :subclass ISCNExampleKaryotype
  (owlsome n/derivedFrom n/k46_XX)
  (e/inverse_insertion 1 h/HumanChromosome2Bandq13 h/HumanChromosome2Bandp13 h/HumanChromosome2Bandp23))

(defclass k46_XX_ins!2!!q13p23p13!
  :label "The 46,XX,ins(2)(q13p23p13) karyotype"
  :subclass ISCNExampleKaryotype
  (owlsome n/derivedFrom n/k46_XX)
  (e/direct_insertion 1 h/HumanChromosome2Bandq13 h/HumanChromosome2Bandp23 h/HumanChromosome2Bandp13))

(defclass k46_XX_ins!2!!p13q21q31!
  :label "The 46,XX,ins(2)(p13q21q31) karyotype"
  :subclass ISCNExampleKaryotype
  (owlsome n/derivedFrom n/k46_XX)
  (e/direct_insertion 1 h/HumanChromosome2Bandp13 h/HumanChromosome2Bandq21 h/HumanChromosome2Bandq31))

(defclass k46_XY_ins!2!!p13q31q21!
  :label "The 46,XY,ins(2)(p13q31q21) karyotype"
  :subclass ISCNExampleKaryotype
  (owlsome n/derivedFrom n/k46_XY)
  (e/inverse_insertion 1 h/HumanChromosome2Bandp13 h/HumanChromosome2Bandq31 h/HumanChromosome2Bandq21))

(defclass k46_XY_ins!5_2!!p14_q22q32!
  :label "The 46,XY,ins(5;2)(p14;q22q32) karyotype"
  :subclass ISCNExampleKaryotype
  (owlsome n/derivedFrom n/k46_XY)
  (e/direct_insertion 1 h/HumanChromosome5Bandp14 h/HumanChromosome2Bandq22 h/HumanChromosome2Bandq32))

(defclass k46_XY_ins!5_2!!p14_q32q22!
  :label "The 46,XY,ins(5;2)(p14;q32q22) karyotype"
  :subclass ISCNExampleKaryotype
  (owlsome n/derivedFrom n/k46_XY)
  (e/inverse_insertion 1 h/HumanChromosome5Bandp14 h/HumanChromosome2Bandq32 h/HumanChromosome2Bandq22))

(defclass k46_XX_ins!5_2!!q31_p13p23!
  :label "The 46,XX,ins(5;2)(q31;p13p23) karyotype"
  :subclass ISCNExampleKaryotype
  (owlsome n/derivedFrom n/k46_XX)
  (e/direct_insertion 1 h/HumanChromosome5Bandq31 h/HumanChromosome2Bandp13 h/HumanChromosome2Bandp23))

(defclass k46_XX_ins!5_2!!q31_p23p13!
  :label "The 46,XX,ins(5;2)(q31;p23p13) karyotype"
  :subclass ISCNExampleKaryotype
  (owlsome n/derivedFrom n/k46_XX)
  (e/inverse_insertion 1 h/HumanChromosome5Bandq31 h/HumanChromosome2Bandp23 h/HumanChromosome2Bandp13))

;; TRANSLOCATIONS
(defclass k46_XY_t!2_5!!q21_q31!
  :label "The 46,XY,t(2;5)(q21;q31) karyotype"
  :subclass ISCNExampleKaryotype
  (owlsome n/derivedFrom n/k46_XY)
  ;; (e/translocation 1 h/HumanChromosome2Bandq21 h/HumanChromosome5Bandq31)
  )
(defclass k46_XY_t!2_5!!p12_q31!
  :label "The 46,XY,t(2;5)(p12;q31) karyotype"
  :subclass ISCNExampleKaryotype
  (owlsome n/derivedFrom n/k46_XY)
  ;; (e/translocation 1 h/HumanChromosome2Bandp12 h/HumanChromosome5Bandq31)
  )
(defclass k46_X_t!X_13!!q27_q12!
  :label "The 46,X,t(X;13)(q27;q12) karyotype"
  :subclass ISCNExampleKaryotype
  (owlsome n/derivedFrom n/k46_XX)
  ;; (e/translocation 1 h/HumanChromosomeXBandq27 h/HumanChromosome13Bandq12)
  )

;; ;; ERROR in book! - there is no resolution band for Y q11.2
;; (defclass k46_t!X_Y!!q22_q11.2!
;;   :label "The 46,t(X;Y)(q22;q11.2) karyotype"
;;   :subclass ISCNExampleKaryotype
;;   (owlsome n/derivedFrom n/k46_XY)
;;   (e/translocation 1 h/HumanChromosomeXBandq22 h/HumanChromosomeYq11.2))

;; ;; ERROR in book! - there is no resolution band for Y q11.2
;; (defclass k46_t!X_18!!p11.2_q11.2!_t!Y_1!!q11.2_p31!
;;   :label "The 46,t(X;18)(p11.2;q11.2),t(Y;1)(q11.2;p31) karyotype"
;;   :subclass ISCNExampleKaryotype
;;   (owlsome n/derivedFrom n/k46_XY)
;;   (e/translocation 1 h/HumanChromosomeXBand11.2 h/HumanChromosome18Bandq11.2)
;;   (e/translocation 1 h/HumanChromosomeYBand11.2 h/HumanChromosome1Bandp31))

(defclass k46_XX_t!2_7_5!!p21_q22_q23!
  :label "The 46,XX,t(2;7;5)(p21;q22;q23) karyotype"
  :subclass ISCNExampleKaryotype
  (owlsome n/derivedFrom n/k46_XX)
  ;; (e/translocation 1 h/HumanChromosome2Bandp21 h/HumanChromosome7Bandq22 h/HumanChromosome5Bandq23)
  )
(defclass k46_X_t!X_22_1!!q24_q11.2_p33!
  :label "The 46,X,t(X;22;1)(q24;q11.2;p33) karyotype"
  :subclass ISCNExampleKaryotype
  (owlsome n/derivedFrom n/k46_XX)
  ;; (e/translocation 1 h/HumanChromosomeXBandq24 h/HumanChromosome22Bandq11.2 h/HumanChromosome1Bandp33)
  )
;; TODO How do I model that 2 distinct Chromosomes 7 are involved?
(defclass k46_XX_t!3_7_7*!!q21_q22_p13!
  :label "The 46,XX,t(3;7;7*)(q21;q22;p13) karyotype"
  :subclass ISCNExampleKaryotype
  (owlsome n/derivedFrom n/k46_XX)
  ;; (e/translocation 1 h/HumanChromosome3Bandq21 h/HumanChromosome7Bandq22 h/HumanChromosome7*Bandp13)
  )
(defclass k46_XX_t!3_9_22_21!!p13_q34_q11.2_q21!
  :label "The 46,XX,t(3;9;22;21)(p13;q34;q11.2;q21) karyotype"
  :subclass ISCNExampleKaryotype
  (owlsome n/derivedFrom n/k46_XX)
  ;; (e/translocation 1 h/HumanChromosome3Bandp13 h/HumanChromosome9q34 h/HumanChromosome22Bandq11.2 h/HumanChromosome21Bandq21)
  )
;; TODO How do I model that 2 distinct Chromosomes 7 are involved?
(defclass k46_XX_t!3_9_9*_22!!p13_q22_q34_q11.2!
  :label "The 46,XX,t(3;9;9*;22)(p13;q22;q34;q11.2) karyotype"
  :subclass ISCNExampleKaryotype
  (owlsome n/derivedFrom n/k46_XX)
  ;; (e/translocation 1 h/HumanChromosome3Bandp13 h/HumanChromosome9Bandq22 h/HumanChromosome9*Bandq34 h/HumanChromosome22Bandq11.2)
  )
(defclass k46_XY_t!5_6!!q13q23_q15q23!
  :label "The 46,XY,t(5;6)(q13q23;q15q23) karyotype"
  :subclass ISCNExampleKaryotype
  (owlsome n/derivedFrom n/k46_XY)
  ;; (e/translocation 1 h/HumanChromosome5Bandq13 h/HumanChromosome5Bandq23 h/HumanChromosome6Bandq15 h/HumanChromosome6Bandq23)
  )

(defclass k46_XX_t!5_14_9!!q13q23_q24q21_p12p23!
  :label "The 46,XX,t(5;14;9)(q13q23;q24q21;p12p23) karyotype"
  :subclass ISCNExampleKaryotype
  (owlsome n/derivedFrom n/k46_XX)
  ;; (e/translocation 1 3 h/HumanChromosome5Bandq13 h/HumanChromosome5Bandq23 h/HumanChromosome14Bandq24 h/HumanChromosome14Bandq21 h/HumanChromosome9Bandp12 h/HumanChromosome9Bandp23)
  )

(defclass k46_XY_t!1_3!!p10_q10!
  :label "The 46,XY,t(1;3)(p10;q10) karyotype"
  :subclass ISCNExampleKaryotype
  (owlsome n/derivedFrom n/k46_XY)
  ;;(e/translocation 1 h/HumanChromosome1Cen h/HumanChromosome3Cen)
  )

;; TODO hasEvent definitions for the following karyotypes
(defclass k46_XY_t!12_16!!q13_p11.1!
  :label "The 46,XY,t(12;16)(q13;p11.1) karyotype"
  :subclass ISCNExampleKaryotype
  (owlsome n/derivedFrom n/k46_XY)
  ;; (e/translocation 1 h/HumanChromosome12Bandq13 h/HumanChromosome16Bandp11.1)
  )
(defclass k46_X_t!X_18!!p11.1_q11.1!
  :label "The 46,X,t(X;18)(p11.1;q11.1) karyotype"
  :subclass ISCNExampleKaryotype
  (owlsome n/derivedFrom n/k46_XN)
  ;; (e/translocation 1 h/HumanChromosomeXBandp11.1 h/HumanChromosome18Bandq11.1)
  )
(defclass k46_XX_t!9_22_17!!q34_q11.2_q22!
  :label "The 46,XX,t(9;22;17)(q34;q11.2;q22) karyotype"
  :subclass ISCNExampleKaryotype
  (owlsome n/derivedFrom n/k46_XX)
  ;; (e/translocation 1 h/HumanChromosome9Bandq34 h/HumanChromosome22Bandq11.2 h/HumanChromosome17Bandq22)
  )
(defclass k46_XY_t!X_15_18!!p11.1_p11.1_q11.1!
  :label "The 46,XY,t(X;15;18)(p11.1;p11.1;q11.1) karyotype"
  :subclass ISCNExampleKaryotype
  (owlsome n/derivedFrom n/k46_XY)
  ;; (e/translocation 1 h/HumanChromosomeXBandp11.1 h/HumanChromosome15Bandp11.1 h/HumanChromosome18Bandq11.1)
  )
(defclass k46_XX_t!3_9_22_21!!p13_q34_q11.2_q21!
  :label "The 46,XX,t(3;9;22;21)(p13;q34;q11.2;q21) karyotype"
  :subclass ISCNExampleKaryotype
  (owlsome n/derivedFrom n/k46_XX)
  ;; (e/translocation 1 h/HumanChromosome3Bandp13 h/HumanChromosome9Bandq34 h/HumanChromosome22Bandq11.2 h/HumanChromosome21Bandq21)
  )
(defclass k46_XY_t!5_6!!q13q23_q15q23!
  :label "The 46,XY,t(5;6)(q13q23;q15q23) karyotype"
  :subclass ISCNExampleKaryotype
  (owlsome n/derivedFrom n/k46_XY)
  ;; (e/translocation 1 h/HumanChromosome5Bandq13 h/HumanChromosome5Bandq23 h/HumanChromosome6Bandq15 h/HumanChromosome6Bandq23)
  )
(defclass k46_t!X_18!!p11.1_q11.2!_t!Y_1!!q11.2_p13!
  :label "The 46,t(X;18)(p11.1;q11.2),t(Y;1)(q11.2;p13) karyotype"
  :subclass ISCNExampleKaryotype
  (owlsome n/derivedFrom n/k46_XY)
  ;; (e/translocation 1 h/HumanChromosomeXBandp11.1 h/HumanChromosome18Bandq11.2)
  ;; (e/translocation 1 h/HumanChromosomeYBand11.2 h/HumanChromosome1Bandp13)
  )
(defclass k46_Y_t!X_8!!p22.3_q24.1!
  :label "The 46,Y,t(X;8)(p22.3;q24.1) karyotype"
  :subclass ISCNExampleKaryotype
  (owlsome n/derivedFrom n/k46_XY)
  ;; (e/translocation 1 h/HumanChromosomeXBandp22.3 h/HumanChromosome8Bandq24.1)
  )

;; QUADRUPLICATIONS
;; NOTE: It is not possible to indicate the orientations of the segments with the short system!
(defclass k46_XX_qdp!1!!q23q32!
  :label "The 46,XX,qdp(1)(q23q32) karyotype"
  :subclass ISCNExampleKaryotype
  (owlsome n/derivedFrom n/k46_XX)
  (e/quadruplication 1 h/HumanChromosome1Bandq23.1 h/HumanChromosome1Bandq32.3))

;; TRIPLICATIONS
;; NOTE: It is not possible to indicate the orientations of the segments with the short system!
(defclass k46_XX_trp!1!!q21q32!
  :label "The 46,XX,trp(1)(q21q32) karyotype"
  :subclass ISCNExampleKaryotype
  (owlsome n/derivedFrom n/k46_XX)
  (exactly 1 e/hasEvent (owland e/DirectTriplication (owlsome e/hasBreakPoint h/HumanChromosome1Bandq23.1 h/HumanChromosome1Bandq32.3))))

(defclass k46_XX_invtrp!1!!q32q21!
  :label "The 46,XX,inv trp(1)(q32q21) karyotype"
  :subclass ISCNExampleKaryotype
  (owlsome n/derivedFrom n/k46_XX)
  (exactly 1 e/hasEvent (owland e/InverseTriplication (owlsome e/hasBreakPoint h/HumanChromosome1Bandq23.1 h/HumanChromosome1Bandq32.3))))

;; MIXTURE
(defclass k47_X_t!X_13!!q27_q12!_inv!10!!p13q22!_+21
  :label "The 47,X,t(X;13)(q27;q12),inv(10)(p13q22),+21 karyotype"
  :subclass ISCNExampleKaryotype
  (owlsome n/derivedFrom n/k46_XX)
  ;; (e/translocation 1 h/HumanChromosomeXBandq27 h/HumanChromosome13Bandq12)
  (e/inversion 1 h/HumanChromosome10Bandp13 h/HumanChromosome10Bandq22)
  (e/addition 1 h/HumanChromosome21))

(defclass k48_X_t!Y_12!!q11.2_p12!_del!6!!q11!_+8_t!9_22!!q34_q11.2!_+17_-21_+22
  :label "The 48,X,t(Y;12)(q11.2;p12),del(6)(q11),+8,t(9;22)(q34;q11.2),+17,-21,+22 karyotype"
  :subclass ISCNExampleKaryotype
  (owlsome n/derivedFrom n/k46_XY)
  ;; (e/translocation 1 h/HumanChromosomeYBandq11.2 h/HumanChromosome12Bandp12)
  (e/deletion 1 h/HumanChromosome6Bandq11)
  (e/addition 1 h/HumanChromosome8)
  ;; (e/translocation 1 h/HumanChromosome9Bandq34 h/HumanChromosome22Bandq11.2)
  (e/addition 1 h/HumanChromosome17)
  (e/deletion 1 h/HumanChromosome21)
  (e/addition 1 h/HumanChromosome22)
)

(defclass k49_X_inv!X!!p21q26!_+3_inv!3!!q21q26.2!_+7_+10_-20_del!20!!q11.2!_+21
  :label "The 49,X,inv(X)(p21q26),+3,inv(3)(q21q26.2),+7,+10,-20,del(20)(q11.2),+21  karyotype"
  :subclass ISCNExampleKaryotype
  (owlsome n/derivedFrom n/k46_XX)
  (e/inversion 1 h/HumanChromosomeXBandp21 h/HumanChromosomeXBandq26)
  (e/addition 1 h/HumanChromosome3)
  (e/inversion 1 h/HumanChromosome3Bandq21 h/HumanChromosome3Bandq26.2)
  (e/addition 1 h/HumanChromosome7)
  (e/addition 1 h/HumanChromosome10)
  (e/deletion 1 h/HumanChromosome20)
  (e/deletion 1 h/HumanChromosome20Bandq11.2)
  (e/addition 1 h/HumanChromosome21))

(defclass k50_XX_+1_del!1!!p13!_+dup!1!!q21q32!_+inv!1!!p31q41!_+8_r!10!!p12q25!_-21
  :label "The 50,XX,+1,del(1)(p13),+dup(1)(q21q32),+inv(1)(p31q41),+8,r(10)(p12q25),-21 karyotype"
  :subclass ISCNExampleKaryotype
  (owlsome n/derivedFrom n/k46_XX)
  (e/addition 1 h/HumanChromosome1)
  (e/deletion 1 h/HumanChromosome1Bandp13)
  ;; (e/addition 1 (e/duplication 1 h/HumanChromosome1Bandq21 h/HumanChromosome1Bandq32))
  ;; (e/addition 1 (e/inverion 1 h/HumanChromosome1Bandp31 h/HumanChromosome1Bandq41))
  (e/addition 1 h/HumanChromosome8)
  ;; TODO (exactly 1 f/hasFeature (owland f/RingChromosome h/HumanChromosome10 (e/hasBreakPoint h/HumanChromosome10p12 h/HumanChromosome10q25))) 
  (e/deletion 1 h/HumanChromosome21))


;; NOT HUMAN?
;; 69,XXX,del(7)(p11.2)
;; 69,XXY,del(7)(q22),inv(7)(p13q22),t(7;14)(p15;q11.1)
;; 70,XXX,+del(7)(p11.2)
;; 92,XXYY,del(7)(p11.2),t(7;14)(p15;q11.1)
;; 92,XXYY,del(7)(p11.2),del(7)(q22),del(7)(q34)

;; DICENTRIC
;; 45,XX,dic(13;15)(q22;q24)

;; DERIVATIVES
;; CORRECT?
;; (defclass k46_Y_der!X!t!X_8!!p22.3_q24.1!
;;   :label "The 46,Y,der(X)t(X;8)(p22.3;q24.1) karyotype"
;;   :subclass ISCNExampleKaryotype
;;   (owlsome n/derivedFrom n/k46_XY)
;;   (exactly 1 f/hasFeature (owland f/DerivativeChromosome h/HumanChromosomeX (e/translocation 1 h/HumanChromosomeXpTer h/HumanChromosomeXp22 h/HumanChromosome8qTer h/HumanHumanChromosome8q24.1))))

;; ;; CORRECT?
;; (defclass k46_XX_der!1!t!1_3!!p22_q13.1!
;;   :label "The 46,XX,der(1)t(1;3)(p22;q13.1) karyotype"
;;   :subclass ISCNExampleKaryotype
;;   (owlsome n/derivedFrom n/k46_XX)
;;   (exactly 1 f/hasFeature (owland f/DerivativeChromosome h/HumanChromosome1 (e/translocation 1 h/HumanChromosome1pTer h/HumanChromosome1p22 h/HumanChromosome3qTer h/HumanChromosome3q13.1))))

;; ;; CORRECT?
;; (defclass k45_XY_der!1!t!1_3!!p22_q13.1!_-3
;;   :label "The 45,XY,der(1)t(1;3)(p22;q13.1),-3 karyotype"
;;   :subclass ISCNExampleKaryotype
;;   (owlsome n/derivedFrom n/k46_XX)
;;   (exactly 1 f/hasFeature (owland f/DerivativeChromosome h/HumanChromosome1 (e/translocation 1 h/HumanChromosome1pTer h/HumanChromosome1p22 h/HumanChromosome3qTer h/HumanChromosome3q13.1)))
;;   (e/deletion 1 h/HumanChromosome3))

;; 46,XX,der(1)t(1;3)(p32;q21)t(1;11)(q25;q13)
;; 46,XY,der(1)t(1;3)(p32;q21)t(3;7)(q28;q11.2)
;; 46,XX,der(1)t(1;11)(p32;q13)t(1;3)(q25;q21)
;; 46,XY,der(1)t(1;3)(p22;q13.1)
;; 45,XY,-10,der(10)t(10;17)(q22;p12)              

;; 46,XY,der(1)t(1;3)(p32;q21)dup(1)(q25q42)
;; 46,XY,der(9)del(9)(p12)t(9;13)(q34;q11)
;; 46,XY,der(9)del(9)(p12)del(9)(q31)
;; 46,XY,der(9)inv(9)(p13p23)del(9)(q22q33)
;; 46,XX,der(7)add(7)(p22)add(7)(q22)
;; 46,XX,der(5)add(5)(p15.1)del(5)(q13)
;; 46,XX,der(5)add(5)(p15.3)add(5)(q23)

;; 46,XX,rec(6)dup(6p)inv(6)(p22.2q25.2)mat


;; ;; MACROS

;; ;; PARSE KARYOTYPE STRING
;; input: string
;; output: karyotype
;; (defmacro parseKaryotypeString[s]
;;   (defclass <TODO>
;;     :label (str "The" s "karyotype")
;;     :subclass ISCNExampleKaryotype
;;     (owlsome n/derivedFrom <TODO>)
;;     <TODO>))

;; ;; PARSE KARYOTYPE
;; input: karyotype
;; output: string
;; (defmacro parseKaryotype[k] ())
