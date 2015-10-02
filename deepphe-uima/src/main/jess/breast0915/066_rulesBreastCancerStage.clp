;; ==========================================================
;;  Clears out the autoloader for testing
;; ==========================================================
(undefrule planning-breast-cancer)
(undefrule planning-goal-achieved)
(undefrule planning-activate-new-goal)
(undefrule planning-retract-last-goal)

(deffunction retract-test-facts "retracts a list of facts" (?args)
    (foreach ?x ?args (printout t ?x crlf)))

(bind ?g (add (new Goal)))
(modify ?g (name "test") (isActive 1))
(bind ?dcis (add (new IntraductalBreastNeoplasm)))
(bind ?lcis (add (new LobularBreastCarcinomaInSitu)))
(bind ?micro (add (new MicroinvasiveBreastCarcinoma)))
(bind ?linv (add (new LymphaticInvasion)))

(bind ?genericT (add (new GenericPrimaryTumorTnmFinding)))
(bind ?tis (add (new TisStageFinding)))
(bind ?t0 (add (new T0StageFinding)))
(bind ?t1 (add (new T1StageFinding)))
(bind ?t2 (add (new T2StageFinding)))

(bind ?genericN (add (new GenericRegionalLymphNodesTnmFinding)))
(bind ?n0 (add (new N0StageFinding)))
(bind ?n1 (add (new N1StageFinding)))
(bind ?n1mi (add (new N1miStageFinding)))

(bind ?genericM (add (new GenericDistantMetastasisTnmFinding)))
(bind ?m0 (add (new M0StageFinding)))
(bind ?m1 (add (new M1StageFinding)))
(bind ?mx (add (new MxStageFinding)))

;; ==========================================================
;; Stage0-1	If document has pTis, pN0, pM0 then Stage 0 Breast Cancer	
;;
;; ==========================================================

(defrule Stage0-1-1 "If document has pTis, pN0, pM0 then Stage 0 Breast Cancer"
     (declare (salience 1500))
     ?g <- (Goal (name ?name&:(eq ?name "test"))(isActive 1))
     (Patient (id ?patientId))
     (TisStageFinding (summarizableId ?patientId))
     (N0StageFinding (summarizableId ?patientId))
     (M0StageFinding (summarizableId ?patientId))
     (not (CancerStage (summarizableId ?patientId)))
=>
     (bind ?cancerStage (add (new Stage0)))
     (modify ?cancerStage (summarizableId ?patientId))
     (printout t "Stage0-1 adding cancer stage Stage 0" crlf))

(defrule Stage0-1-2 ""
     (declare (salience 1000))
     ?g <- (Goal (name ?name&:(eq ?name "test"))(isActive 1))
     ?stage0 <- (Stage0)
=>
     (printout t "Found stage 0 cancer." crlf)

     (retract ?g)
     (printout t "Stage0-1-2 removing staging goal" crlf))

(deffunction UnitTest-Stage0-1 () "Tests Stage0-1 rule"
    (bind ?g (add (new Goal)))
    (bind ?p (add (new Patient)))
    (bind ?patientId (fact-slot-value ?p id))
    (modify ?g (name "test") (isActive 1))
    (bind ?t (add (new TisStageFinding)))
    (bind ?n (add (new N0StageFinding)))
    (bind ?m (add (new M0StageFinding)))
    (modify ?t (summarizableId ?patientId))
    (modify ?n (summarizableId ?patientId))
    (modify ?m (summarizableId ?patientId))
    (run)
    (retract ?patientId)
    (retract ?p)
    (retract ?t)
    (retract ?n)
    (retract ?m)
    (retract ?g))

(UnitTest-Stage0-1)

(undefrule Stage0-1-1)
(undefrule Stage0-1-2)

;; ==========================================================
;;Stage0-2	If document has primary dx ONLY DCIS OR (DCIS & Pagets), then Stage 0 Breast Cancer	
;; ==========================================================

(defrule Stage0-2-1 "If document has primary dx ONLY DCIS"
     (declare (salience 1400))
     ?g <- (Goal (name ?name&:(eq ?name "test"))(isActive 1))
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
     ?g <- (Goal (name ?name&:(eq ?name "test"))(isActive 1))
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

(defrule Stage0-2-3 ""
     (declare (salience 1300))
     ?g <- (Goal (name ?name&:(eq ?name "test"))(isActive 1))
     ?stage0 <- (Stage0)
=>
     (printout t "Stage0-2-3 Assert TRUE => Found Stage0 cancer." crlf)
     (retract ?stage0)
     (retract ?g)
     (printout t "Stage0-2-3 removing staging goal" crlf))

(deffunction UnitTest-Stage0-2 () "Tests Stage0-2-* rule suite"
    (bind ?g (add (new Goal)))
    (bind ?p (add (new Patient)))
    (bind ?patientId (fact-slot-value ?p id))
    (modify ?g (name "test") (isActive 1))
    (bind ?dcis (add (new IntraductalBreastNeoplasm)))
    (modify ?dcis (summarizableId ?patientId))
    (run)
    (retract ?patientId)
    (retract ?p)
    (retract ?dcis)
    (retract ?g))

(UnitTest-Stage0-2)

(undefrule Stage0-2-1)
(undefrule Stage0-2-2)
(undefrule Stage0-2-3)

;; ==========================================================
;;Stage0-3	If document has primary dx ONLY LCIS OR (LCIS & Pagets),  then Stage 0 Breast 
;; ==========================================================

(defrule Stage0-3-1 "If document has primary dx ONLY LCIS"
     (declare (salience 1200))
     ?g <- (Goal (name ?name&:(eq ?name "test"))(isActive 1))
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
     ?g <- (Goal (name ?name&:(eq ?name "test"))(isActive 1))
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

(defrule Stage0-3-3 ""
     (declare (salience 1300))
     ?g <- (Goal (name ?name&:(eq ?name "test"))(isActive 1))
     ?stage0 <- (Stage0)
=>
     (printout t "Stage0-3-3 Assert TRUE => Found Stage0 cancer." crlf)
     (retract ?stage0)
     (retract ?g)
     (printout t "Stage0-3-3 removing staging goal" crlf))

(deffunction UnitTest-Stage0-3 () "Tests Stage0-3-* rule suite"
    (bind ?g (add (new Goal)))
    (bind ?p (add (new Patient)))
    (bind ?patientId (fact-slot-value ?p id))
    (modify ?g (name "test") (isActive 1))
    (bind ?lcis (add (new LobularBreastCarcinomaInSitu)))
    (modify ?lcis (summarizableId ?patientId))
    (run)
    (retract ?patientId)
    (retract ?p)
    (retract ?lcis)
    (retract ?g))

(UnitTest-Stage0-3)

(undefrule Stage0-3-1)
(undefrule Stage0-3-2)
(undefrule Stage0-3-3)

;; ==========================================================
;;Stage0-4	If document has primary dx ONLY (DCIS & LCIS) OR (DCIS & LCIS & Paget's), then Stage 0 Breast Cancer	
;; ==========================================================

(defrule Stage0-4-1 "If document has primary dx ONLY LCIS"
     (declare (salience 1200))
     ?g <- (Goal (name ?name&:(eq ?name "test"))(isActive 1))
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
     ?g <- (Goal (name ?name&:(eq ?name "test"))(isActive 1))
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

(defrule Stage0-4-3 ""
     (declare (salience 1100))
     ?g <- (Goal (name ?name&:(eq ?name "test"))(isActive 1))
     ?stage0 <- (Stage0)
=>
     (printout t "Stage0-4-3 Assert TRUE => Found Stage0 cancer." crlf)
     (retract ?stage0)
     (retract ?g)
     (printout t "Stage0-4-3 removing staging goal" crlf))

(deffunction UnitTest-Stage0-4 () "Tests Stage0-4-* rule suite"
    (bind ?g (add (new Goal)))
    (bind ?p (add (new Patient)))
    (bind ?patientId (fact-slot-value ?p id))
    (modify ?g (name "test") (isActive 1))
    (bind ?dcis (add (new IntraductalBreastNeoplasm)))
    (bind ?lcis (add (new LobularBreastCarcinomaInSitu)))
    (bind ?pagets (add (new PagetDiseaseOfTheBreast)))
    (modify ?dcis (summarizableId ?patientId) (preferredTerm "IntraductalBreastNeoplasm"))
    (modify ?lcis (summarizableId ?patientId) (preferredTerm "LobularBreastCarcinomaInSitu"))
    (modify ?pagets (summarizableId ?patientId) (preferredTerm "PagetDiseaseOfTheBreast"))
    (run)
    (retract ?patientId)
    (retract ?p)
    (retract ?pagets)
    (retract ?lcis)
    (retract ?dcis)
    (retract ?g))

(UnitTest-Stage0-4)

(undefrule Stage0-4-1)
(undefrule Stage0-4-2)
(undefrule Stage0-4-3)

;; ==========================================================
;;Stage 0-5   If document has primary dx ONLY Paget's Disease, then Stage 0 Breast Cancer
;; ==========================================================

(defrule Stage0-5-1 "If document has primary dx ONLY Paget's Disease, then Stage 0 Breast Cancer"
     (declare (salience 1000))
     ?g <- (Goal (name ?name&:(eq ?name "test"))(isActive 1))
     (Patient (id ?patientId))
     (PagetDiseaseOfTheBreast (preferredTerm ?pagetsTerm) (summarizableId ?patientId))
     (not (CancerStage (summarizableId ?patientId)))
     (not (Diagnosis (preferredTerm ?otherTerm&:(neq ?otherTerm ?pagetsTerm))
                     (summarizableId ?patientId)))
=>
     (bind ?cancerStage (add (new Stage0)))
     (modify ?cancerStage (summarizableId ?patientId))
     (printout t "Stage0-5-1 adding cancer stage Stage 0" crlf))

(defrule Stage0-5-2 ""
     (declare (salience 950))
     ?g <- (Goal (name ?name&:(eq ?name "test"))(isActive 1))
     ?stage0 <- (Stage0)
=>
     (printout t "Stage0-5-2 Assert TRUE => Found Stage0 cancer." crlf)
     (retract ?stage0)
     (retract ?g)
     (printout t "Stage0-5-2 removing staging goal" crlf))

(deffunction UnitTest-Stage0-5 () "Tests Stage0-5-* rule suite"
    (bind ?g (add (new Goal)))
    (bind ?p (add (new Patient)))
    (bind ?patientId (fact-slot-value ?p id))
    (modify ?g (name "test") (isActive 1))
    (bind ?pagets (add (new PagetDiseaseOfTheBreast)))
    (modify ?pagets (summarizableId ?patientId) (preferredTerm "PagetDiseaseOfTheBreast"))
    (run)
    (retract ?patientId)
    (retract ?p)
    (retract ?pagets)
    (retract ?g))

(UnitTest-Stage0-5)

(undefrule Stage0-5-1)
(undefrule Stage0-5-2)

;; ==========================================================
;; StageIa-1	If document has pT1, pN0, pM0, then Stage IA Breast Cancer	
;;
;; Note: This could be a little confusing - any M0 is clinical. Path cannot tell	
;; ==========================================================

(defrule StageIa-1-1 "If document has pT1, pN0, pM0, then Stage IA Breast Cancer"
     (declare (salience 900))
     ?g <- (Goal (name ?name&:(eq ?name "test"))(isActive 1))
     (Patient (id ?patientId))
     (T1StageFinding (summarizableId ?patientId))
     (N0StageFinding (summarizableId ?patientId))
     (M0StageFinding (summarizableId ?patientId))
     (not (CancerStage (summarizableId ?patientId)))
=>
     (bind ?cancerStage (add (new StageIa)))
     (modify ?cancerStage (summarizableId ?patientId))
     (printout t "StageIa1-1-1 adding cancer stage StageIa" crlf))

(defrule StageIa-1-2 "Remove goal to find StageIa cancer"
     (declare (salience 1000))
     ?g <- (Goal (name ?name&:(eq ?name "test"))(isActive 1))
     ?stage1A <- (StageIa)
=>
     (printout t "Found stage 1A cancer." crlf)
     (retract ?g)
     (printout t "StageIa-1-2 removing staging goal" crlf))

(deffunction UnitTest-StageIa-1 () "Tests StageIa-1 rule"
    (bind ?g (add (new Goal)))
    (bind ?p (add (new Patient)))
    (bind ?patientId (fact-slot-value ?p id))
    (modify ?g (name "test") (isActive 1))
    (bind ?t (add (new T1StageFinding)))
    (bind ?n (add (new N0StageFinding)))
    (bind ?m (add (new M0StageFinding)))
    (modify ?t (summarizableId ?patientId))
    (modify ?n (summarizableId ?patientId))
    (modify ?m (summarizableId ?patientId))
    (run)
    (retract ?patientId)
    (retract ?p)
    (retract ?t)
    (retract ?n)
    (retract ?m)
    (retract ?g))

(UnitTest-StageIa-1)

(undefrule StageIa-1-1)
(undefrule StageIa-1-2)

;; ==========================================================
;;StageIb-1	If document has pT0, pN1mi, pM0, then Stage IB Breast Cancer	
;; ==========================================================

(defrule StageIb-1-1 "If document has pT0, pN1mi, pM0, then Stage IB Breast Cancer"
     (declare (salience 800))
     ?g <- (Goal (name ?name&:(eq ?name "test"))(isActive 1))
     (Patient (id ?patientId))
     (T0StageFinding (summarizableId ?patientId))
     (N1miStageFinding (summarizableId ?patientId))
     (M0StageFinding (summarizableId ?patientId))
     (not (CancerStage (summarizableId ?patientId)))
=>
     (bind ?cancerStage (add (new StageIb)))
     (modify ?cancerStage (summarizableId ?patientId))
     (printout t "StageIb-1-1 adding cancer stage StageIb" crlf))

(defrule StageIb-1-2 "Remove goal to find StageIb cancer"
     (declare (salience 750))
     ?g <- (Goal (name ?name&:(eq ?name "test"))(isActive 1))
     ?stageIb <- (StageIb)
=>
     (retract ?stageIb)
     (printout t "Found stageIb cancer." crlf)
     (retract ?g)
     (printout t "StageIb-1-2 removing staging goal" crlf))

(deffunction UnitTest-StageIb-1 () "Tests StageIb-1 rule"
    (bind ?g (add (new Goal)))
    (bind ?p (add (new Patient)))
    (bind ?patientId (fact-slot-value ?p id))
    (modify ?g (name "test") (isActive 1))
    (bind ?t (add (new T0StageFinding)))
    (bind ?n (add (new N1miStageFinding)))
    (bind ?m (add (new M0StageFinding)))
    (modify ?t (summarizableId ?patientId))
    (modify ?n (summarizableId ?patientId))
    (modify ?m (summarizableId ?patientId))
    (run)
    (retract ?patientId)
    (retract ?p)
    (retract ?t)
    (retract ?n)
    (retract ?m)
    (retract ?g))

(UnitTest-StageIb-1)

(undefrule StageIb-1-1)
(undefrule StageIb-1-2)

;; ==========================================================
;;Stage1B-2	If document has pT1, pN1mi, pM0, then Stage IB Breast Cancer	
;; ==========================================================

(defrule StageIb-2-1 "If document has pT0, pN1mi, pM0, then Stage IB Breast Cancer"
     (declare (salience 700))
     ?g <- (Goal (name ?name&:(eq ?name "test"))(isActive 1))
     (Patient (id ?patientId))
     (T1StageFinding (summarizableId ?patientId))
     (N1miStageFinding (summarizableId ?patientId))
     (M0StageFinding (summarizableId ?patientId))
     (not (CancerStage (summarizableId ?patientId)))
=>
     (bind ?cancerStage (add (new StageIb)))
     (modify ?cancerStage (summarizableId ?patientId))
     (printout t "StageIb-2-1 adding cancer stage StageIb" crlf))

(defrule StageIb-2-2 "Remove goal to find StageIb cancer"
     (declare (salience 650))
     ?g <- (Goal (name ?name&:(eq ?name "test"))(isActive 1))
     ?stageIb <- (StageIb)
=>
     (retract ?stageIb)
     (printout t "Found stageIb cancer." crlf)
     (retract ?g)
     (printout t "StageIb-2-2 removing staging goal" crlf))

(deffunction UnitTest-StageIb-2 () "Tests StageIb-2 rule"
    (bind ?g (add (new Goal)))
    (bind ?p (add (new Patient)))
    (bind ?patientId (fact-slot-value ?p id))
    (modify ?g (name "test") (isActive 1))
    (bind ?t (add (new T1StageFinding)))
    (bind ?n (add (new N1miStageFinding)))
    (bind ?m (add (new M0StageFinding)))
    (modify ?t (summarizableId ?patientId))
    (modify ?n (summarizableId ?patientId))
    (modify ?m (summarizableId ?patientId))
    (run)
    (retract ?patientId)
    (retract ?p)
    (retract ?t)
    (retract ?n)
    (retract ?m)
    (retract ?g))

(UnitTest-StageIb-2)

(undefrule StageIb-2-1)
(undefrule StageIb-2-2)

;; ==========================================================
;;Stage2A-1	If document has pT0, pN1, pM0, then Stage IIA Breast Cancer
;; ==========================================================

(defrule StageIIa-1-1 "If document has pT0, pN1, pM0, then Stage IIA Breast Cancer"
     (declare (salience 900))
     ?g <- (Goal (name ?name&:(eq ?name "test"))(isActive 1))
     (Patient (id ?patientId))
     (T0StageFinding (summarizableId ?patientId))
     (N1StageFinding (summarizableId ?patientId))
     (M0StageFinding (summarizableId ?patientId))
     (not (CancerStage (summarizableId ?patientId)))
=>
     (bind ?cancerStage (add (new StageIia)))
     (modify ?cancerStage (summarizableId ?patientId))
     (printout t "StageIIa-1-1 adding cancer stage StageIIa" crlf))

(defrule StageIIa-1-2 "Remove goal to find StageIIa cancer"
     (declare (salience 1000))
     ?g <- (Goal (name ?name&:(eq ?name "test"))(isActive 1))
     ?stageIIa <- (StageIia)
=>
     (printout t "Found stage IIa cancer." crlf)
     (retract ?stageIIa)
     (retract ?g)
     (printout t "StageIIa-1-2 removing staging goal" crlf))

(deffunction UnitTest-StageIIa-1 () "Tests StageIIa-1 rule"
    (bind ?g (add (new Goal)))
    (bind ?p (add (new Patient)))
    (bind ?patientId (fact-slot-value ?p id))
    (modify ?g (name "test") (isActive 1))
    (bind ?t (add (new T1StageFinding)))
    (bind ?n (add (new N0StageFinding)))
    (bind ?m (add (new M0StageFinding)))
    (modify ?t (summarizableId ?patientId))
    (modify ?n (summarizableId ?patientId))
    (modify ?m (summarizableId ?patientId))
    (run)
    (retract ?patientId)
    (retract ?p)
    (retract ?t)
    (retract ?n)
    (retract ?m)
    (retract ?g))

(UnitTest-StageIIa-1)

(undefrule StageIIa-1-1)
(undefrule StageIIa-1-2)

	
;; ==========================================================
;;Stage2A-2	If document has pT1, pN1, pM0, then Stage IIA Breast Cancer
;; ==========================================================

(defrule StageIIa-2-1 "If document has pT1, pN1, pM0, then Stage IIA Breast Cancer"
     (declare (salience 900))
     ?g <- (Goal (name ?name&:(eq ?name "test"))(isActive 1))
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
     ?g <- (Goal (name ?name&:(eq ?name "test"))(isActive 1))
     ?stageIIa <- (StageIia)
=>
     (printout t "Found stage IIa cancer." crlf)
     (retract ?stageIIa)
     (retract ?g)
     (printout t "StageIIa-2-2 removing staging goal" crlf))

(deffunction UnitTest-StageIIa-2 () "Tests StageIIa-2 rule"
    (bind ?g (add (new Goal)))
    (bind ?p (add (new Patient)))
    (bind ?patientId (fact-slot-value ?p id))
    (modify ?g (name "test") (isActive 1))
    (bind ?t (add (new T1StageFinding)))
    (bind ?n (add (new N1StageFinding)))
    (bind ?m (add (new M0StageFinding)))
    (modify ?t (summarizableId ?patientId))
    (modify ?n (summarizableId ?patientId))
    (modify ?m (summarizableId ?patientId))
    (run)
    (retract ?patientId)
    (retract ?p)
    (retract ?t)
    (retract ?n)
    (retract ?m)
    (retract ?g))

(UnitTest-StageIIa-2)

(undefrule StageIIa-2-1)
(undefrule StageIIa-2-2)
	
;; ==========================================================
;;Stage2A-3	If document has pT2, pN0, pM0, then Stage IIA Breast Cancer
;; ==========================================================

(defrule StageIIa-3-1 "If document has pT2, pN0, pM0, then Stage IIA Breast Cancer"
     (declare (salience 900))
     ?g <- (Goal (name ?name&:(eq ?name "test"))(isActive 1))
     (Patient (id ?patientId))
     (T2StageFinding (summarizableId ?patientId))
     (N0StageFinding (summarizableId ?patientId))
     (M0StageFinding (summarizableId ?patientId))
     (not (CancerStage (summarizableId ?patientId)))
=>
     (bind ?cancerStage (add (new StageIia)))
     (modify ?cancerStage (summarizableId ?patientId))
     (printout t "StageIIa-3-1 adding cancer stage StageIIa" crlf))

(defrule StageIIa-3-2 "Remove goal to find StageIIa cancer"
     (declare (salience 1000))
     ?g <- (Goal (name ?name&:(eq ?name "test"))(isActive 1))
     ?stageIIa <- (StageIia)
=>
     (printout t "Found stage IIa cancer." crlf)
     (retract ?stageIIa)
     (retract ?g)
     (printout t "StageIIa-3-2 removing staging goal" crlf))

(deffunction UnitTest-StageIIa-3 () "Tests StageIIa-3 rule"
    (bind ?g (add (new Goal)))
    (bind ?p (add (new Patient)))
    (bind ?patientId (fact-slot-value ?p id))
    (modify ?g (name "test") (isActive 1))
    (bind ?t (add (new T2StageFinding)))
    (bind ?n (add (new N0StageFinding)))
    (bind ?m (add (new M0StageFinding)))
    (modify ?t (summarizableId ?patientId))
    (modify ?n (summarizableId ?patientId))
    (modify ?m (summarizableId ?patientId))
    (run)
    (retract ?patientId)
    (retract ?p)
    (retract ?t)
    (retract ?n)
    (retract ?m)
    (retract ?g))

(UnitTest-StageIIa-3)

(undefrule StageIIa-3-1)
(undefrule StageIIa-3-2)
	
;; ==========================================================
;;Stage2B-1	If document has pT2, pN1, pM0, then Stage IIB Breast Cancer
;; ==========================================================

(defrule StageIIb-1-1 "If document has pT2, pN1, pM0, then Stage IIB Breast Cancer"
     (declare (salience 900))
     ?g <- (Goal (name ?name&:(eq ?name "test"))(isActive 1))
     (Patient (id ?patientId))
     (T2StageFinding (summarizableId ?patientId))
     (N1StageFinding (summarizableId ?patientId))
     (M0StageFinding (summarizableId ?patientId))
     (not (CancerStage (summarizableId ?patientId)))
=>
     (bind ?cancerStage (add (new StageIib)))
     (modify ?cancerStage (summarizableId ?patientId))
     (printout t "StageIIb-1-1 adding cancer stage StageIIb" crlf))

(defrule StageIIb-1-2 "Remove goal to find StageIIb cancer"
     (declare (salience 1000))
     ?g <- (Goal (name ?name&:(eq ?name "test"))(isActive 1))
     ?stageIIb <- (StageIib)
=>
     (printout t "Found stage IIb cancer." crlf)
     (retract ?stageIIb)
     (retract ?g)
     (printout t "StageIIb-1-2 removing staging goal" crlf))

(deffunction UnitTest-StageIIb-1 () "Tests StageIIb-1 rule"
    (bind ?g (add (new Goal)))
    (bind ?p (add (new Patient)))
    (bind ?patientId (fact-slot-value ?p id))
    (modify ?g (name "test") (isActive 1))
    (bind ?t (add (new T2StageFinding)))
    (bind ?n (add (new N1StageFinding)))
    (bind ?m (add (new M0StageFinding)))
    (modify ?t (summarizableId ?patientId))
    (modify ?n (summarizableId ?patientId))
    (modify ?m (summarizableId ?patientId))
    (run)
    (retract ?patientId)
    (retract ?p)
    (retract ?t)
    (retract ?n)
    (retract ?m)
    (retract ?g))

(UnitTest-StageIIb-1)

(undefrule StageIIb-1-1)
(undefrule StageIIb-1-2)
	
;; ==========================================================
;;Stage2B-2	If document has pT3, pN0, pM0, then Stage IIB Breast Cancer	
;; ==========================================================

(defrule StageIIb-2-1 "If document has pT3, pN0, pM0, then Stage IIB Breast Cancer"
     (declare (salience 900))
     ?g <- (Goal (name ?name&:(eq ?name "test"))(isActive 1))
     (Patient (id ?patientId))
     (T3StageFinding (summarizableId ?patientId))
     (N0StageFinding (summarizableId ?patientId))
     (M0StageFinding (summarizableId ?patientId))
     (not (CancerStage (summarizableId ?patientId)))
=>
     (bind ?cancerStage (add (new StageIib)))
     (modify ?cancerStage (summarizableId ?patientId))
     (printout t "StageIIb-2-1 adding cancer stage StageIIb" crlf))

(defrule StageIIb-2-2 "Remove goal to find StageIIb cancer"
     (declare (salience 1000))
     ?g <- (Goal (name ?name&:(eq ?name "test"))(isActive 1))
     ?stageIIb <- (StageIib)
=>
     (printout t "Found stage IIb cancer." crlf)
     (retract ?stageIIb)
     (retract ?g)
     (printout t "StageIIb-2-2 removing staging goal" crlf))

(deffunction UnitTest-StageIIb-2 () "Tests StageIIb-2 rule"
    (bind ?g (add (new Goal)))
    (bind ?p (add (new Patient)))
    (bind ?patientId (fact-slot-value ?p id))
    (modify ?g (name "test") (isActive 1))
    (bind ?t (add (new T3StageFinding)))
    (bind ?n (add (new N0StageFinding)))
    (bind ?m (add (new M0StageFinding)))
    (modify ?t (summarizableId ?patientId))
    (modify ?n (summarizableId ?patientId))
    (modify ?m (summarizableId ?patientId))
    (run)
    (retract ?patientId)
    (retract ?p)
    (retract ?t)
    (retract ?n)
    (retract ?m)
    (retract ?g))

(UnitTest-StageIIb-2)

(undefrule StageIIb-2-1)
(undefrule StageIIb-2-2)


;; ==========================================================
;;Stage3A-1	If document has pT0, pN2, pM0, then Stage IIIA Breast Cancer	
;; ==========================================================

(defrule StageIIIa-1-1 "If document has pT0, pN2, pM0, then Stage IIIA Breast Cancer"
     (declare (salience 1000))
     ?g <- (Goal (name ?name&:(eq ?name "test"))(isActive 1))
     (Patient (id ?patientId))
     (T0StageFinding (summarizableId ?patientId))
     (N2StageFinding (summarizableId ?patientId))
     (M0StageFinding (summarizableId ?patientId))
     (not (CancerStage (summarizableId ?patientId)))
=>
     (bind ?cancerStage (add (new StageIiia)))
     (modify ?cancerStage (summarizableId ?patientId))
     (printout t "StageIIIa-1-1 adding cancer stage StageIIIa" crlf))

(defrule StageIIIa-1-2 "Remove goal to find StageIIIa cancer"
     (declare (salience 900))
     ?g <- (Goal (name ?name&:(eq ?name "test"))(isActive 1))
     ?stageIiia <- (StageIiia)
=>
     (printout t "Found stage IIIa cancer." crlf)
     (retract ?stageIiia)
     (retract ?g)
     (printout t "StageIIIa-1-2 removing staging goal" crlf))

(deffunction UnitTest-StageIIIa-1 () "Tests StageIIIa-1 rule"
    (bind ?g (add (new Goal)))
    (bind ?p (add (new Patient)))
    (bind ?patientId (fact-slot-value ?p id))
    (modify ?g (name "test") (isActive 1))
    (bind ?t (add (new T0StageFinding)))
    (bind ?n (add (new N2StageFinding)))
    (bind ?m (add (new M0StageFinding)))
    (modify ?t (summarizableId ?patientId))
    (modify ?n (summarizableId ?patientId))
    (modify ?m (summarizableId ?patientId))
    (run)
    (retract ?patientId)
    (retract ?p)
    (retract ?t)
    (retract ?n)
    (retract ?m)
    (retract ?g))

(UnitTest-StageIIIa-1)

(undefrule StageIIIa-1-1)
(undefrule StageIIIa-1-2)

;; ==========================================================
;;Stage3A-2	If document has pT1, pN2, pM0, then Stage IIIA Breast Cancer	
;; ==========================================================

(defrule StageIIIa-2-1 "If document has pT1, pN2, pM0, then Stage IIIA Breast Cancer"
     (declare (salience 1000))
     ?g <- (Goal (name ?name&:(eq ?name "test"))(isActive 1))
     (Patient (id ?patientId))
     (T1StageFinding (summarizableId ?patientId))
     (N2StageFinding (summarizableId ?patientId))
     (M0StageFinding (summarizableId ?patientId))
     (not (CancerStage (summarizableId ?patientId)))
=>
     (bind ?cancerStage (add (new StageIiia)))
     (modify ?cancerStage (summarizableId ?patientId))
     (printout t "StageIIIa-2-1 adding cancer stage StageIIIa" crlf))

(defrule StageIIIa-2-2 "Remove goal to find StageIIIa cancer"
     (declare (salience 900))
     ?g <- (Goal (name ?name&:(eq ?name "test"))(isActive 1))
     ?stageIiia <- (StageIiia)
=>
     (printout t "Found stage IIIa cancer." crlf)
     (retract ?stageIiia)
     (retract ?g)
     (printout t "StageIIIa-2-2 removing staging goal" crlf))

(deffunction UnitTest-StageIIIa-2 () "Tests StageIIIa-2 rule"
    (bind ?g (add (new Goal)))
    (bind ?p (add (new Patient)))
    (bind ?patientId (fact-slot-value ?p id))
    (modify ?g (name "test") (isActive 1))
    (bind ?t (add (new T1StageFinding)))
    (bind ?n (add (new N2StageFinding)))
    (bind ?m (add (new M0StageFinding)))
    (modify ?t (summarizableId ?patientId))
    (modify ?n (summarizableId ?patientId))
    (modify ?m (summarizableId ?patientId))
    (run)
    (retract ?patientId)
    (retract ?p)
    (retract ?t)
    (retract ?n)
    (retract ?m)
    (retract ?g))

(UnitTest-StageIIIa-2)

(undefrule StageIIIa-2-1)
(undefrule StageIIIa-2-2)

;; ==========================================================
;;Stage3A-3	If document has pT2, pN2, pM0, then Stage IIIA Breast Cancer	
;; ==========================================================

(defrule StageIIIa-3-1 "If document has pT2, pN2, pM0, then Stage IIIA Breast Cancer"
     (declare (salience 1000))
     ?g <- (Goal (name ?name&:(eq ?name "test"))(isActive 1))
     (Patient (id ?patientId))
     (T2StageFinding (summarizableId ?patientId))
     (N2StageFinding (summarizableId ?patientId))
     (M0StageFinding (summarizableId ?patientId))
     (not (CancerStage (summarizableId ?patientId)))
=>
     (bind ?cancerStage (add (new StageIiia)))
     (modify ?cancerStage (summarizableId ?patientId))
     (printout t "StageIIIa-3-1 adding cancer stage StageIIIa" crlf))

(defrule StageIIIa-3-2 "Remove goal to find StageIIIa cancer"
     (declare (salience 900))
     ?g <- (Goal (name ?name&:(eq ?name "test"))(isActive 1))
     ?stageIiia <- (StageIiia)
=>
     (printout t "Found stage IIIa cancer." crlf)
     (retract ?stageIiia)
     (retract ?g)
     (printout t "StageIIIa-3-2 removing staging goal" crlf))

(deffunction UnitTest-StageIIIa-3 () "Tests StageIIIa-3 rule"
    (bind ?g (add (new Goal)))
    (bind ?p (add (new Patient)))
    (bind ?patientId (fact-slot-value ?p id))
    (modify ?g (name "test") (isActive 1))
    (bind ?t (add (new T2StageFinding)))
    (bind ?n (add (new N2StageFinding)))
    (bind ?m (add (new M0StageFinding)))
    (modify ?t (summarizableId ?patientId))
    (modify ?n (summarizableId ?patientId))
    (modify ?m (summarizableId ?patientId))
    (run)
    (retract ?patientId)
    (retract ?p)
    (retract ?t)
    (retract ?n)
    (retract ?m)
    (retract ?g))

(UnitTest-StageIIIa-3)

(undefrule StageIIIa-3-1)
(undefrule StageIIIa-3-2)

;; ==========================================================
;;Stage3A-4	If document has pT3, pN1, pM0, then Stage IIIA Breast Cancer
;; ==========================================================

(defrule StageIIIa-4-1 "If document has pT3, pN1, pM0, then Stage IIIA Breast Cancer"
     (declare (salience 1000))
     ?g <- (Goal (name ?name&:(eq ?name "test"))(isActive 1))
     (Patient (id ?patientId))
     (T3StageFinding (summarizableId ?patientId))
     (N1StageFinding (summarizableId ?patientId))
     (M0StageFinding (summarizableId ?patientId))
     (not (CancerStage (summarizableId ?patientId)))
=>
     (bind ?cancerStage (add (new StageIiia)))
     (modify ?cancerStage (summarizableId ?patientId))
     (printout t "StageIIIa-4-1 adding cancer stage StageIIIa" crlf))

(defrule StageIIIa-4-2 "Remove goal to find StageIIIa cancer"
     (declare (salience 900))
     ?g <- (Goal (name ?name&:(eq ?name "test"))(isActive 1))
     ?stageIiia <- (StageIiia)
=>
     (printout t "Found stage IIIa cancer." crlf)
     (retract ?stageIiia)
     (retract ?g)
     (printout t "StageIIIa-4-2 removing staging goal" crlf))

(deffunction UnitTest-StageIIIa-4 () "Tests StageIIIa-4 rule"
    (bind ?g (add (new Goal)))
    (bind ?p (add (new Patient)))
    (bind ?patientId (fact-slot-value ?p id))
    (modify ?g (name "test") (isActive 1))
    (bind ?t (add (new T3StageFinding)))
    (bind ?n (add (new N1StageFinding)))
    (bind ?m (add (new M0StageFinding)))
    (modify ?t (summarizableId ?patientId))
    (modify ?n (summarizableId ?patientId))
    (modify ?m (summarizableId ?patientId))
    (run)
    (retract ?patientId)
    (retract ?p)
    (retract ?t)
    (retract ?n)
    (retract ?m)
    (retract ?g))

(UnitTest-StageIIIa-4)

(undefrule StageIIIa-4-1)
(undefrule StageIIIa-4-2)
	
;; ==========================================================
;;Stage3A-5	If document has pT3, pN2, pM0, then Stage IIIA Breast Cancer
;; ==========================================================

(defrule StageIIIa-5-1 "If document has pT3, pN1, pM0, then Stage IIIA Breast Cancer"
     (declare (salience 1000))
     ?g <- (Goal (name ?name&:(eq ?name "test"))(isActive 1))
     (Patient (id ?patientId))
     (T3StageFinding (summarizableId ?patientId))
     (N2StageFinding (summarizableId ?patientId))
     (M0StageFinding (summarizableId ?patientId))
     (not (CancerStage (summarizableId ?patientId)))
=>
     (bind ?cancerStage (add (new StageIiia)))
     (modify ?cancerStage (summarizableId ?patientId))
     (printout t "StageIIIa-5-1 adding cancer stage StageIIIa" crlf))

(defrule StageIIIa-5-2 "Remove goal to find StageIIIa cancer"
     (declare (salience 900))
     ?g <- (Goal (name ?name&:(eq ?name "test"))(isActive 1))
     ?stageIiia <- (StageIiia)
=>
     (printout t "Found stage IIIa cancer." crlf)
     (retract ?stageIiia)
     (retract ?g)
     (printout t "StageIIIa-5-2 removing staging goal" crlf))

(deffunction UnitTest-StageIIIa-5 () "Tests StageIIIa-5 rule"
    (bind ?g (add (new Goal)))
    (bind ?p (add (new Patient)))
    (bind ?patientId (fact-slot-value ?p id))
    (modify ?g (name "test") (isActive 1))
    (bind ?t (add (new T3StageFinding)))
    (bind ?n (add (new N1StageFinding)))
    (bind ?m (add (new M0StageFinding)))
    (modify ?t (summarizableId ?patientId))
    (modify ?n (summarizableId ?patientId))
    (modify ?m (summarizableId ?patientId))
    (run)
    (retract ?patientId)
    (retract ?p)
    (retract ?t)
    (retract ?n)
    (retract ?m)
    (retract ?g))

(UnitTest-StageIIIa-5)

(undefrule StageIIIa-5-1)
(undefrule StageIIIa-5-2)
		
;; ==========================================================
;;Stage3B-1	If document has pT4, pN0, pM0, then Stage IIIB Breast Cancer	
;; ==========================================================

(defrule StageIIIb-1-1 "If document has pT4, pN0, pM0, then Stage IIIB Breast Cancer"
     (declare (salience 1000))
     ?g <- (Goal (name ?name&:(eq ?name "test"))(isActive 1))
     (Patient (id ?patientId))
     (T4StageFinding (summarizableId ?patientId))
     (N0StageFinding (summarizableId ?patientId))
     (M0StageFinding (summarizableId ?patientId))
     (not (CancerStage (summarizableId ?patientId)))
=>
     (bind ?cancerStage (add (new StageIiib)))
     (modify ?cancerStage (summarizableId ?patientId))
     (printout t "StageIIIb-1-1 adding cancer stage StageIIIb" crlf))

(defrule StageIIIb-1-2 "Remove goal to find StageIIIb cancer"
     (declare (salience 900))
     ?g <- (Goal (name ?name&:(eq ?name "test"))(isActive 1))
     ?stageIiib <- (StageIiib)
=>
     (printout t "Found stage IIIb cancer." crlf)
     (retract ?stageIiib)
     (retract ?g)
     (printout t "StageIIIb-1-2 removing staging goal" crlf))

(deffunction UnitTest-StageIIIb-1 () "Tests StageIIIb-1 rule"
    (bind ?g (add (new Goal)))
    (bind ?p (add (new Patient)))
    (bind ?patientId (fact-slot-value ?p id))
    (modify ?g (name "test") (isActive 1))
    (bind ?t (add (new T4StageFinding)))
    (bind ?n (add (new N0StageFinding)))
    (bind ?m (add (new M0StageFinding)))
    (modify ?t (summarizableId ?patientId))
    (modify ?n (summarizableId ?patientId))
    (modify ?m (summarizableId ?patientId))
    (run)
    (retract ?patientId)
    (retract ?p)
    (retract ?t)
    (retract ?n)
    (retract ?m)
    (retract ?g))

(UnitTest-StageIIIb-1)

(undefrule StageIIIb-1-1)
(undefrule StageIIIb-1-2)

;; ==========================================================
;;Stage3B-2	If document has pT4, pN1, pM0, then Stage IIIB Breast Cancer
;; ==========================================================

(defrule StageIIIb-2-1 "If document has pT4, pN1, pM0, then Stage IIIB Breast Cancer"
     (declare (salience 1000))
     ?g <- (Goal (name ?name&:(eq ?name "test"))(isActive 1))
     (Patient (id ?patientId))
     (T4StageFinding (summarizableId ?patientId))
     (N1StageFinding (summarizableId ?patientId))
     (M0StageFinding (summarizableId ?patientId))
     (not (CancerStage (summarizableId ?patientId)))
=>
     (bind ?cancerStage (add (new StageIiib)))
     (modify ?cancerStage (summarizableId ?patientId))
     (printout t "StageIIIb-2-1 adding cancer stage StageIIIb" crlf))

(defrule StageIIIb-2-2 "Remove goal to find StageIIIb cancer"
     (declare (salience 900))
     ?g <- (Goal (name ?name&:(eq ?name "test"))(isActive 1))
     ?stageIiib <- (StageIiib)
=>
     (printout t "Found stage IIIb cancer." crlf)
     (retract ?stageIiib)
     (retract ?g)
     (printout t "StageIIIb-2-2 removing staging goal" crlf))

(deffunction UnitTest-StageIIIb-2 () "Tests StageIIIb-2 rule"
    (bind ?g (add (new Goal)))
    (bind ?p (add (new Patient)))
    (bind ?patientId (fact-slot-value ?p id))
    (modify ?g (name "test") (isActive 1))
    (bind ?t (add (new T4StageFinding)))
    (bind ?n (add (new N1StageFinding)))
    (bind ?m (add (new M0StageFinding)))
    (modify ?t (summarizableId ?patientId))
    (modify ?n (summarizableId ?patientId))
    (modify ?m (summarizableId ?patientId))
    (run)
    (retract ?patientId)
    (retract ?p)
    (retract ?t)
    (retract ?n)
    (retract ?m)
    (retract ?g))

(UnitTest-StageIIIb-2)

(undefrule StageIIIb-2-1)
(undefrule StageIIIb-2-2)

	
;; ==========================================================
;;Stage3B-3	If document has pT4, pN2, pM0, then Stage IIIB Breast Cancer
;; ==========================================================

(defrule StageIIIb-3-1 "If document has pT4, pN2, pM0, then Stage IIIB Breast Cancer"
     (declare (salience 1000))
     ?g <- (Goal (name ?name&:(eq ?name "test"))(isActive 1))
     (Patient (id ?patientId))
     (T4StageFinding (summarizableId ?patientId))
     (N2StageFinding (summarizableId ?patientId))
     (M0StageFinding (summarizableId ?patientId))
     (not (CancerStage (summarizableId ?patientId)))
=>
     (bind ?cancerStage (add (new StageIiib)))
     (modify ?cancerStage (summarizableId ?patientId))
     (printout t "StageIIIb-3-1 adding cancer stage StageIIIb" crlf))

(defrule StageIIIb-3-2 "Remove goal to find StageIIIb cancer"
     (declare (salience 900))
     ?g <- (Goal (name ?name&:(eq ?name "test"))(isActive 1))
     ?stageIiib <- (StageIiib)
=>
     (printout t "Found stage IIIb cancer." crlf)
     (retract ?stageIiib)
     (retract ?g)
     (printout t "StageIIIb-3-2 removing staging goal" crlf))

(deffunction UnitTest-StageIIIb-3 () "Tests StageIIIb-3 rule"
    (bind ?g (add (new Goal)))
    (bind ?p (add (new Patient)))
    (bind ?patientId (fact-slot-value ?p id))
    (modify ?g (name "test") (isActive 1))
    (bind ?t (add (new T4StageFinding)))
    (bind ?n (add (new N2StageFinding)))
    (bind ?m (add (new M0StageFinding)))
    (modify ?t (summarizableId ?patientId))
    (modify ?n (summarizableId ?patientId))
    (modify ?m (summarizableId ?patientId))
    (run)
    (retract ?patientId)
    (retract ?p)
    (retract ?t)
    (retract ?n)
    (retract ?m)
    (retract ?g))

(UnitTest-StageIIIb-3)

(undefrule StageIIIb-3-1)
(undefrule StageIIIb-3-2)

;; ==========================================================	
;;Stage3C-1	If document has any pT value, pN3, pM0, then Stage IIIC Breast Cancer
;; ==========================================================

(defrule StageIIIc-1-1 "If document has any pT value, pN3, pM0, then Stage IIIC Breast Cancer"
     (declare (salience 1000))
     ?g <- (Goal (name ?name&:(eq ?name "test"))(isActive 1))
     (Patient (id ?patientId))
     (GenericPrimaryTumorTnmFinding (summarizableId ?patientId))
     (N3StageFinding (summarizableId ?patientId))
     (M0StageFinding (summarizableId ?patientId))
     (not (CancerStage (summarizableId ?patientId)))
=>
     (bind ?cancerStage (add (new StageIiic)))
     (modify ?cancerStage (summarizableId ?patientId))
     (printout t "StageIIIc-1-1 adding cancer stage StageIIIc" crlf))

(defrule StageIIIc-1-2 "Remove goal to find StageIIIc cancer"
     (declare (salience 900))
     ?g <- (Goal (name ?name&:(eq ?name "test"))(isActive 1))
     ?stageIiic <- (StageIiic)
=>
     (printout t "Found stage IIIc cancer." crlf)
     (retract ?stageIiic)
     (retract ?g)
     (printout t "StageIIIc-1-2 removing staging goal" crlf))

(deffunction UnitTest-StageIIIc-1 () "Tests StageIIIc-1 rule"
    (bind ?g (add (new Goal)))
    (bind ?p (add (new Patient)))
    (bind ?patientId (fact-slot-value ?p id))
    (modify ?g (name "test") (isActive 1))
    (bind ?t (add (new T2StageFinding)))
    (bind ?n (add (new N3StageFinding)))
    (bind ?m (add (new M0StageFinding)))
    (modify ?t (summarizableId ?patientId))
    (modify ?n (summarizableId ?patientId))
    (modify ?m (summarizableId ?patientId))
    (run)
    (retract ?patientId)
    (retract ?p)
    (retract ?t)
    (retract ?n)
    (retract ?m)
    (retract ?g))

(UnitTest-StageIIIc-1)

(undefrule StageIIIc-1-1)
(undefrule StageIIIc-1-2)
	
;; ==========================================================
;;Stage4-1	If document has any pT value, any pN value, pM1, then Stage IV Breast Cancer
;; ==========================================================

(defrule StageIV-1-1 "If document has any pT value, any pN value, pM1, then Stage IV Breast Cancer"
     (declare (salience 1000))
     ?g <- (Goal (name ?name&:(eq ?name "test"))(isActive 1))
     (Patient (id ?patientId))
     (GenericPrimaryTumorTnmFinding (summarizableId ?patientId))
     (GenericRegionalLymphNodesTnmFinding (summarizableId ?patientId))
     (M1StageFinding (summarizableId ?patientId))
     (not (CancerStage (summarizableId ?patientId)))
=>
     (bind ?cancerStage (add (new StageIv)))
     (modify ?cancerStage (summarizableId ?patientId))
     (printout t "StageIV-1-1 adding cancer stage StageIV" crlf))

(defrule StageIV-1-2 "Remove goal to find StageIV cancer"
     (declare (salience 900))
     ?g <- (Goal (name ?name&:(eq ?name "test"))(isActive 1))
     ?stageIv <- (StageIv)
=>
     (printout t "Found stage IV cancer." crlf)
     (retract ?stageIv)
     (retract ?g)
     (printout t "StageIV-1-2 removing staging goal" crlf))

(deffunction UnitTest-StageIV-1 () "Tests StageIV-1 rule"
    (bind ?g (add (new Goal)))
    (bind ?p (add (new Patient)))
    (bind ?patientId (fact-slot-value ?p id))
    (modify ?g (name "test") (isActive 1))
    (bind ?t (add (new T2StageFinding)))
    (bind ?n (add (new N3StageFinding)))
    (bind ?m (add (new M1StageFinding)))
    (modify ?t (summarizableId ?patientId))
    (modify ?n (summarizableId ?patientId))
    (modify ?m (summarizableId ?patientId))
    (run)
    (retract ?patientId)
    (retract ?p)
    (retract ?t)
    (retract ?n)
    (retract ?m)
    (retract ?g))

(UnitTest-StageIV-1)

(undefrule StageIV-1-1)
(undefrule StageIV-1-2)
