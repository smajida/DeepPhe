;; ==========================================================
;; Stage0-1	If document has pTis, pN0, pM0 then Stage 0 Breast Cancer	
;;
;; ==========================================================

(defrule Stage0-1-1 "If document has pTis, pN0, pM0 then Stage 0 Breast Cancer"
     (declare (salience 1500))
     ?g <- (Goal (name ?name&:(eq ?name "extract-stage"))(isActive 1))
     (Patient (id ?patientId))
     (TisStageFinding (summarizableId ?patientId))
     (N0StageFinding (summarizableId ?patientId))
     (M0StageFinding (summarizableId ?patientId))
     (not (CancerStage (summarizableId ?patientId)))
=>
     (bind ?cancerStage (add (new Stage0)))
     (modify ?cancerStage (summarizableId ?patientId))
     (printout t "Stage0-1 adding cancer stage Stage 0" crlf))

;; ==========================================================
;;Stage0-2	If document has primary dx ONLY DCIS OR (DCIS & Pagets), then Stage 0 Breast Cancer	
;; ==========================================================

(defrule Stage0-2-1 "If document has primary dx ONLY DCIS"
     (declare (salience 1400))
     ?g <- (Goal (name ?name&:(eq ?name "extract-stage"))(isActive 1))
     (Patient (id ?patientId))
     (IntraductalBreastNeoplasm  (preferredTerm ?dcisTerm)  (summarizableId ?patientId))
     (not (Diagnosis (preferredTerm ?otherTerm&:(neq ?otherTerm ?dcisTerm))))
     (not (CancerStage (summarizableId ?patientId)))
=>
     (bind ?cancerStage (add (new Stage0)))
     (modify ?cancerStage (summarizableId ?patientId))
     (printout t "Stage0-2-1 adding cancer stage Stage 0" crlf))

(defrule Stage0-2-2 "OR (DCIS & Pagets)..."
     (declare (salience 1350))
     ?g <- (Goal (name ?name&:(eq ?name "extract-stage"))(isActive 1))
     (Patient (id ?patientId))
     (IntraductalBreastNeoplasm (preferredTerm ?dcisTerm) (summarizableId ?patientId))
     (PagetDiseaseOfTheBreast (preferredTerm ?pagetTerm) (summarizableId ?patientId))
     (not (CancerStage (summarizableId ?patientId)))
     (not (Diagnosis (preferredTerm ?otherTerm&:(and
                                         (neq ?otherTerm ?dcisTerm)
                                         (neq ?otherTerm ?pagetTerm)))
                     (summarizableId ?patientId)))
=>
     (bind ?cancerStage (add (new Stage0)))
     (modify ?cancerStage (summarizableId ?patientId))
     (printout t "Stage0-2-2 adding cancer stage Stage 0" crlf))

;; ==========================================================
;;Stage0-3	If document has primary dx ONLY LCIS OR (LCIS & Pagets),  then Stage 0 Breast 
;; ==========================================================

(defrule Stage0-3-1 "If document has primary dx ONLY LCIS"
     (declare (salience 1200))
     ?g <- (Goal (name ?name&:(eq ?name "extract-stage"))(isActive 1))
     (Patient (id ?patientId))
     (LobularBreastCarcinomaInSitu  (preferredTerm ?lcisTerm)  (summarizableId ?patientId))
     (not (Diagnosis (preferredTerm ?otherTerm&:(neq ?otherTerm ?lcisTerm))))
     (not (CancerStage (summarizableId ?patientId)))
=>
     (bind ?cancerStage (add (new Stage0)))
     (modify ?cancerStage (summarizableId ?patientId))
     (printout t "Stage0-3-1 adding cancer stage Stage 0" crlf))

(defrule Stage0-3-2 "OR (LCIS & Pagets)..."
     (declare (salience 1150))
     ?g <- (Goal (name ?name&:(eq ?name "extract-stage"))(isActive 1))
     (Patient (id ?patientId))
     (LobularBreastCarcinomaInSitu (preferredTerm ?lcisTerm) (summarizableId ?patientId))
     (PagetDiseaseOfTheBreast (preferredTerm ?pagetTerm) (summarizableId ?patientId))
     (not (CancerStage (summarizableId ?patientId)))
     (not (Diagnosis (preferredTerm ?otherTerm&:(and
                                         (neq ?otherTerm ?lcisTerm)
                                         (neq ?otherTerm ?pagetTerm)))
                     (summarizableId ?patientId)))
=>
     (bind ?cancerStage (add (new Stage0)))
     (modify ?cancerStage (summarizableId ?patientId))
     (printout t "Stage0-3-2 adding cancer stage Stage 0" crlf))

;; ==========================================================
;;Stage0-4	If document has primary dx ONLY (DCIS & LCIS) OR (DCIS & LCIS & Paget's), then Stage 0 Breast Cancer	
;; ==========================================================

(defrule Stage0-4-1 "If document has primary dx ONLY LCIS"
     (declare (salience 1200))
     ?g <- (Goal (name ?name&:(eq ?name "extract-stage"))(isActive 1))
     (Patient (id ?patientId))
     (IntraductalBreastNeoplasm (preferredTerm ?dcisTerm) (summarizableId ?patientId))
     (LobularBreastCarcinomaInSitu  (preferredTerm ?lcisTerm)  (summarizableId ?patientId))
     (not (CancerStage (summarizableId ?patientId)))
     (not (PagetDiseaseOfTheBreast (summarizableId ?patientId)))
     (not (Diagnosis (preferredTerm ?otherTerm&:(and
                                         (neq ?otherTerm ?lcisTerm)
                                         (neq ?otherTerm ?dcisTerm)))
                     (summarizableId ?patientId)))
=>
     (bind ?cancerStage (add (new Stage0)))
     (modify ?cancerStage (summarizableId ?patientId))
     (printout t "Stage0-4-1 adding cancer stage Stage 0" crlf))


(defrule Stage0-4-2 "OR (LCIS & Pagets)..."
     (declare (salience 1150))
     ?g <- (Goal (name ?name&:(eq ?name "extract-stage"))(isActive 1))
     (Patient (id ?patientId))
     (IntraductalBreastNeoplasm (preferredTerm ?dcisTerm) (summarizableId ?patientId))
     (LobularBreastCarcinomaInSitu  (preferredTerm ?lcisTerm)  (summarizableId ?patientId))
     (PagetDiseaseOfTheBreast (preferredTerm ?pagetTerm) (summarizableId ?patientId))
     (not (CancerStage (summarizableId ?patientId)))
     (not (Diagnosis (preferredTerm ?otherTerm&:(and
                                         (neq ?otherTerm ?dcisTerm)
                                         (neq ?otherTerm ?lcisTerm)
                                         (neq ?otherTerm ?pagetTerm)))
                     (summarizableId ?patientId)))
=>
     (bind ?cancerStage (add (new Stage0)))
     (modify ?cancerStage (summarizableId ?patientId))
     (printout t "Stage0-4-2 adding cancer stage Stage 0" crlf))

;; ==========================================================
;;Stage 0-5   If document has primary dx ONLY Paget's Disease, then Stage 0 Breast Cancer
;; ==========================================================

(defrule Stage0-5-1 "If document has primary dx ONLY Paget's Disease, then Stage 0 Breast Cancer"
     (declare (salience 1000))
     ?g <- (Goal (name ?name&:(eq ?name "extract-stage"))(isActive 1))
     (Patient (id ?patientId))
     (PagetDiseaseOfTheBreast (preferredTerm ?pagetsTerm) (summarizableId ?patientId))
     (not (CancerStage (summarizableId ?patientId)))
     (not (Diagnosis (preferredTerm ?otherTerm&:(neq ?otherTerm ?pagetsTerm))
                     (summarizableId ?patientId)))
=>
     (bind ?cancerStage (add (new Stage0)))
     (modify ?cancerStage (summarizableId ?patientId))
     (printout t "Stage0-5-1 adding cancer stage Stage 0" crlf))

;; ==========================================================
;; StageIa-1	If document has pT1, pN0, pM0, then Stage IA Breast Cancer	
;;
;; Note: This could be a little confusing - any M0 is clinical. Path cannot tell	
;; ==========================================================

(defrule StageIa-1-1 "If document has pT1, pN0, pM0, then Stage IA Breast Cancer"
     (declare (salience 900))
     ?g <- (Goal (name ?name&:(eq ?name "extract-stage"))(isActive 1))
     (Patient (id ?patientId))
     (T1StageFinding (summarizableId ?patientId))
     (N0StageFinding (summarizableId ?patientId))
     (M0StageFinding (summarizableId ?patientId))
     (not (CancerStage (summarizableId ?patientId)))
=>
     (bind ?cancerStage (add (new StageIa)))
     (modify ?cancerStage (summarizableId ?patientId))
     (printout t "StageIa1-1-1 adding cancer stage StageIa" crlf))

;; ==========================================================
;;StageIb-1	If document has pT0, pN1mi, pM0, then Stage IB Breast Cancer	
;; ==========================================================

(defrule StageIb-1-1 "If document has pT0, pN1mi, pM0, then Stage IB Breast Cancer"
     (declare (salience 800))
     ?g <- (Goal (name ?name&:(eq ?name "extract-stage"))(isActive 1))
     (Patient (id ?patientId))
     (T0StageFinding (summarizableId ?patientId))
     (N1miStageFinding (summarizableId ?patientId))
     (M0StageFinding (summarizableId ?patientId))
     (not (CancerStage (summarizableId ?patientId)))
=>
     (bind ?cancerStage (add (new StageIb)))
     (modify ?cancerStage (summarizableId ?patientId))
     (printout t "StageIb-1-1 adding cancer stage StageIb" crlf))


;; ==========================================================
;;Stage1B-2	If document has pT1, pN1mi, pM0, then Stage IB Breast Cancer	
;; ==========================================================

(defrule StageIb-2-1 "If document has pT0, pN1mi, pM0, then Stage IB Breast Cancer"
     (declare (salience 700))
     ?g <- (Goal (name ?name&:(eq ?name "extract-stage"))(isActive 1))
     (Patient (id ?patientId))
     (T1StageFinding (summarizableId ?patientId))
     (N1miStageFinding (summarizableId ?patientId))
     (M0StageFinding (summarizableId ?patientId))
     (not (CancerStage (summarizableId ?patientId)))
=>
     (bind ?cancerStage (add (new StageIb)))
     (modify ?cancerStage (summarizableId ?patientId))
     (printout t "StageIb-2-1 adding cancer stage StageIb" crlf))

;; ==========================================================
;;Stage2A-1	If document has pT0, pN1, pM0, then Stage IIA Breast Cancer
;; ==========================================================

(defrule StageIIa-1-1 "If document has pT0, pN1, pM0, then Stage IIA Breast Cancer"
     (declare (salience 900))
     ?g <- (Goal (name ?name&:(eq ?name "extract-stage"))(isActive 1))
     (Patient (id ?patientId))
     (T0StageFinding (summarizableId ?patientId))
     (N1StageFinding (summarizableId ?patientId))
     (M0StageFinding (summarizableId ?patientId))
     (not (CancerStage (summarizableId ?patientId)))
=>
     (bind ?cancerStage (add (new StageIia)))
     (modify ?cancerStage (summarizableId ?patientId))
     (printout t "StageIIa-1-1 adding cancer stage StageIIa" crlf))

	
;; ==========================================================
;;Stage2A-2	If document has pT1, pN1, pM0, then Stage IIA Breast Cancer
;; ==========================================================

(defrule StageIIa-2-1 "If document has pT1, pN1, pM0, then Stage IIA Breast Cancer"
     (declare (salience 900))
     ?g <- (Goal (name ?name&:(eq ?name "extract-stage"))(isActive 1))
     (Patient (id ?patientId))
     (T1StageFinding (summarizableId ?patientId))
     (N1StageFinding (summarizableId ?patientId))
     (M0StageFinding (summarizableId ?patientId))
     (not (CancerStage (summarizableId ?patientId)))
=>
     (bind ?cancerStage (add (new StageIia)))
     (modify ?cancerStage (summarizableId ?patientId))
     (printout t "StageIIa-2-1 adding cancer stage StageIIa" crlf))

(defrule StageIIa-2-2 "Remove goal to find StageIIa cancer"
     (declare (salience 1000))
     ?g <- (Goal (name ?name&:(eq ?name "extract-stage"))(isActive 1))
     ?stageIIa <- (StageIia)
=>
     (printout t "Found stage IIa cancer." crlf)
     (retract ?stageIIa)
     (retract ?g)
     (printout t "StageIIa-2-2 removing staging goal" crlf))
	
;; ==========================================================
;;Stage2A-3	If document has pT2, pN0, pM0, then Stage IIA Breast Cancer
;; ==========================================================

(defrule StageIIa-3-1 "If document has pT2, pN0, pM0, then Stage IIA Breast Cancer"
     (declare (salience 900))
     ?g <- (Goal (name ?name&:(eq ?name "extract-stage"))(isActive 1))
     (Patient (id ?patientId))
     (T2StageFinding (summarizableId ?patientId))
     (N0StageFinding (summarizableId ?patientId))
     (M0StageFinding (summarizableId ?patientId))
     (not (CancerStage (summarizableId ?patientId)))
=>
     (bind ?cancerStage (add (new StageIia)))
     (modify ?cancerStage (summarizableId ?patientId))
     (printout t "StageIIa-3-1 adding cancer stage StageIIa" crlf))
	
;; ==========================================================
;;Stage2B-1	If document has pT2, pN1, pM0, then Stage IIB Breast Cancer
;; ==========================================================

(defrule StageIIb-1-1 "If document has pT2, pN1, pM0, then Stage IIB Breast Cancer"
     (declare (salience 900))
     ?g <- (Goal (name ?name&:(eq ?name "extract-stage"))(isActive 1))
     (Patient (id ?patientId))
     (T2StageFinding (summarizableId ?patientId))
     (N1StageFinding (summarizableId ?patientId))
     (M0StageFinding (summarizableId ?patientId))
     (not (CancerStage (summarizableId ?patientId)))
=>
     (bind ?cancerStage (add (new StageIib)))
     (modify ?cancerStage (summarizableId ?patientId))
     (printout t "StageIIb-1-1 adding cancer stage StageIIb" crlf))
	
;; ==========================================================
;;Stage2B-2	If document has pT3, pN0, pM0, then Stage IIB Breast Cancer	
;; ==========================================================

(defrule StageIIb-2-1 "If document has pT3, pN0, pM0, then Stage IIB Breast Cancer"
     (declare (salience 900))
     ?g <- (Goal (name ?name&:(eq ?name "extract-stage"))(isActive 1))
     (Patient (id ?patientId))
     (T3StageFinding (summarizableId ?patientId))
     (N0StageFinding (summarizableId ?patientId))
     (M0StageFinding (summarizableId ?patientId))
     (not (CancerStage (summarizableId ?patientId)))
=>
     (bind ?cancerStage (add (new StageIib)))
     (modify ?cancerStage (summarizableId ?patientId))
     (printout t "StageIIb-2-1 adding cancer stage StageIIb" crlf))

;; ==========================================================
;;Stage3A-1	If document has pT0, pN2, pM0, then Stage IIIA Breast Cancer	
;; ==========================================================

(defrule StageIIIa-1-1 "If document has pT0, pN2, pM0, then Stage IIIA Breast Cancer"
     (declare (salience 1000))
     ?g <- (Goal (name ?name&:(eq ?name "extract-stage"))(isActive 1))
     (Patient (id ?patientId))
     (T0StageFinding (summarizableId ?patientId))
     (N2StageFinding (summarizableId ?patientId))
     (M0StageFinding (summarizableId ?patientId))
     (not (CancerStage (summarizableId ?patientId)))
=>
     (bind ?cancerStage (add (new StageIiia)))
     (modify ?cancerStage (summarizableId ?patientId))
     (printout t "StageIIIa-1-1 adding cancer stage StageIIIa" crlf))

;; ==========================================================
;;Stage3A-2	If document has pT1, pN2, pM0, then Stage IIIA Breast Cancer	
;; ==========================================================

(defrule StageIIIa-2-1 "If document has pT1, pN2, pM0, then Stage IIIA Breast Cancer"
     (declare (salience 1000))
     ?g <- (Goal (name ?name&:(eq ?name "extract-stage"))(isActive 1))
     (Patient (id ?patientId))
     (T1StageFinding (summarizableId ?patientId))
     (N2StageFinding (summarizableId ?patientId))
     (M0StageFinding (summarizableId ?patientId))
     (not (CancerStage (summarizableId ?patientId)))
=>
     (bind ?cancerStage (add (new StageIiia)))
     (modify ?cancerStage (summarizableId ?patientId))
     (printout t "StageIIIa-2-1 adding cancer stage StageIIIa" crlf))

;; ==========================================================
;;Stage3A-3	If document has pT2, pN2, pM0, then Stage IIIA Breast Cancer	
;; ==========================================================

(defrule StageIIIa-3-1 "If document has pT2, pN2, pM0, then Stage IIIA Breast Cancer"
     (declare (salience 1000))
     ?g <- (Goal (name ?name&:(eq ?name "extract-stage"))(isActive 1))
     (Patient (id ?patientId))
     (T2StageFinding (summarizableId ?patientId))
     (N2StageFinding (summarizableId ?patientId))
     (M0StageFinding (summarizableId ?patientId))
     (not (CancerStage (summarizableId ?patientId)))
=>
     (bind ?cancerStage (add (new StageIiia)))
     (modify ?cancerStage (summarizableId ?patientId))
     (printout t "StageIIIa-3-1 adding cancer stage StageIIIa" crlf))

;; ==========================================================
;;Stage3A-4	If document has pT3, pN1, pM0, then Stage IIIA Breast Cancer
;; ==========================================================

(defrule StageIIIa-4-1 "If document has pT3, pN1, pM0, then Stage IIIA Breast Cancer"
     (declare (salience 1000))
     ?g <- (Goal (name ?name&:(eq ?name "extract-stage"))(isActive 1))
     (Patient (id ?patientId))
     (T3StageFinding (summarizableId ?patientId))
     (N1StageFinding (summarizableId ?patientId))
     (M0StageFinding (summarizableId ?patientId))
     (not (CancerStage (summarizableId ?patientId)))
=>
     (bind ?cancerStage (add (new StageIiia)))
     (modify ?cancerStage (summarizableId ?patientId))
     (printout t "StageIIIa-4-1 adding cancer stage StageIIIa" crlf))
	
;; ==========================================================
;;Stage3A-5	If document has pT3, pN2, pM0, then Stage IIIA Breast Cancer
;; ==========================================================

(defrule StageIIIa-5-1 "If document has pT3, pN1, pM0, then Stage IIIA Breast Cancer"
     (declare (salience 1000))
     ?g <- (Goal (name ?name&:(eq ?name "extract-stage"))(isActive 1))
     (Patient (id ?patientId))
     (T3StageFinding (summarizableId ?patientId))
     (N2StageFinding (summarizableId ?patientId))
     (M0StageFinding (summarizableId ?patientId))
     (not (CancerStage (summarizableId ?patientId)))
=>
     (bind ?cancerStage (add (new StageIiia)))
     (modify ?cancerStage (summarizableId ?patientId))
     (printout t "StageIIIa-5-1 adding cancer stage StageIIIa" crlf))
		
;; ==========================================================
;;Stage3B-1	If document has pT4, pN0, pM0, then Stage IIIB Breast Cancer	
;; ==========================================================

(defrule StageIIIb-1-1 "If document has pT4, pN0, pM0, then Stage IIIB Breast Cancer"
     (declare (salience 1000))
     ?g <- (Goal (name ?name&:(eq ?name "extract-stage"))(isActive 1))
     (Patient (id ?patientId))
     (T4StageFinding (summarizableId ?patientId))
     (N0StageFinding (summarizableId ?patientId))
     (M0StageFinding (summarizableId ?patientId))
     (not (CancerStage (summarizableId ?patientId)))
=>
     (bind ?cancerStage (add (new StageIiib)))
     (modify ?cancerStage (summarizableId ?patientId))
     (printout t "StageIIIb-1-1 adding cancer stage StageIIIb" crlf))

;; ==========================================================
;;Stage3B-2	If document has pT4, pN1, pM0, then Stage IIIB Breast Cancer
;; ==========================================================

(defrule StageIIIb-2-1 "If document has pT4, pN1, pM0, then Stage IIIB Breast Cancer"
     (declare (salience 1000))
     ?g <- (Goal (name ?name&:(eq ?name "extract-stage"))(isActive 1))
     (Patient (id ?patientId))
     (T4StageFinding (summarizableId ?patientId))
     (N1StageFinding (summarizableId ?patientId))
     (M0StageFinding (summarizableId ?patientId))
     (not (CancerStage (summarizableId ?patientId)))
=>
     (bind ?cancerStage (add (new StageIiib)))
     (modify ?cancerStage (summarizableId ?patientId))
     (printout t "StageIIIb-2-1 adding cancer stage StageIIIb" crlf))
	
;; ==========================================================
;;Stage3B-3	If document has pT4, pN2, pM0, then Stage IIIB Breast Cancer
;; ==========================================================

(defrule StageIIIb-3-1 "If document has pT4, pN2, pM0, then Stage IIIB Breast Cancer"
     (declare (salience 1000))
     ?g <- (Goal (name ?name&:(eq ?name "extract-stage"))(isActive 1))
     (Patient (id ?patientId))
     (T4StageFinding (summarizableId ?patientId))
     (N2StageFinding (summarizableId ?patientId))
     (M0StageFinding (summarizableId ?patientId))
     (not (CancerStage (summarizableId ?patientId)))
=>
     (bind ?cancerStage (add (new StageIiib)))
     (modify ?cancerStage (summarizableId ?patientId))
     (printout t "StageIIIb-3-1 adding cancer stage StageIIIb" crlf))

;; ==========================================================	
;;Stage3C-1	If document has any pT value, pN3, pM0, then Stage IIIC Breast Cancer
;; ==========================================================

(defrule StageIIIc-1-1 "If document has any pT value, pN3, pM0, then Stage IIIC Breast Cancer"
     (declare (salience 1000))
     ?g <- (Goal (name ?name&:(eq ?name "extract-stage"))(isActive 1))
     (Patient (id ?patientId))
     (GenericPrimaryTumorTnmFinding (summarizableId ?patientId))
     (N3StageFinding (summarizableId ?patientId))
     (M0StageFinding (summarizableId ?patientId))
     (not (CancerStage (summarizableId ?patientId)))
=>
     (bind ?cancerStage (add (new StageIiic)))
     (modify ?cancerStage (summarizableId ?patientId))
     (printout t "StageIIIc-1-1 adding cancer stage StageIIIc" crlf))
	
;; ==========================================================
;;Stage4-1	If document has any pT value, any pN value, pM1, then Stage IV Breast Cancer
;; ==========================================================

(defrule StageIV-1-1 "If document has any pT value, any pN value, pM1, then Stage IV Breast Cancer"
     (declare (salience 1000))
     ?g <- (Goal (name ?name&:(eq ?name "extract-stage"))(isActive 1))
     (Patient (id ?patientId))
     (GenericPrimaryTumorTnmFinding (summarizableId ?patientId))
     (GenericRegionalLymphNodesTnmFinding (summarizableId ?patientId))
     (M1StageFinding (summarizableId ?patientId))
     (not (CancerStage (summarizableId ?patientId)))
=>
     (bind ?cancerStage (add (new StageIv)))
     (modify ?cancerStage (summarizableId ?patientId))
     (printout t "StageIV-1-1 adding cancer stage StageIV" crlf))
