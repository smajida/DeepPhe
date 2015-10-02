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

(retract-test-facts (create$ ?dcisEnc ?g))

;; ============================================================
;;
;; pM1-1	
;;       If populating a TNM target template, and there exists a
;;     an instance of metastatic malignant neoplasm, then create instance of pM1.
;;
;; ============================================================

(defrule pM1-1 "Create pM1 from metastatic malignant neoplasm"
     (declare (salience 1500))
     ?g <- (Goal (name ?name&:(eq ?name "test"))(isActive 1))
     (MetastaticMalignantNeoplasm)
     (Patient (id ?patientId))
     (not (GenericDistantMetastasisTnmFinding (summarizableId ?patientId)))
=>
     (bind ?tnmMgrade (add (new M1StageFinding)))
     (modify ?tnmMgrade (summarizableId ?patientId))
     (printout t "pM1-1 adding tnmMgrade from metastatic malignant neoplasm" crlf))

;; ============================================================
;;
;; pMx-1	
;;       If populating a TNM target template, and
;;     metastasis can not be confirmed no denied
;;
;; ============================================================

(defrule pMx-1 "Default value for metastasis"
     (declare (salience 750))
     ?g <- (Goal (name ?name&:(eq ?name "test"))(isActive 1))
     (Patient (id ?patientId))
     (not (GenericDistantMetastasisTnmFinding (summarizableId ?patientId)))
=>
     (bind ?tnmMgrade (add (new MxStageFinding)))
     (modify ?tnmMgrade (summarizableId ?patientId))
     (printout t "pM1-1 adding tnmMgrade based on insufficient information" crlf))

;; ============================================================
;;
;; pN1-1	
;;       If document has positive lymph node, then create pN1
;;
;; ============================================================

(defrule pN1-1 "Create pN1 from lymph node involvement"
     (declare (salience 1500))
     ?g <- (Goal (name ?name&:(eq ?name "extract-tnm"))(isActive 1))
     (LymphNodeInvolvement)
     (Patient (id ?patientId))
     (not (GenericRegionalLymphNodesTnmFinding (summarizableId ?patientId)))
=>
     (bind ?tnmNgrade (add (new N1StageFinding)))
     (modify ?tnmNgrade (summarizableId ?patientId))
     (printout t "pN1-1 adding tnmNgrade from involved lymph nodes" crlf))

;; ============================================================
;;
;; pN1mi-1	
;;        If document has nodal micrometastasis only, then create pN1mi
;;
;; ============================================================
(defrule pN1mi-1 "If document has nodal micrometastasis only, then create pN1mi"
     (declare (salience 1500))
     ?g <- (Goal (name ?name&:(eq ?name "extract-tnm"))(isActive 1))
     (LymphaticInvasion)
     (Patient (id ?patientId))
     (not (GenericRegionalLymphNodesTnmFinding (summarizableId ?patientId)))
=>
     (bind ?tnmNgrade (add (add (new N1miStageFinding))))
     (modify ?tnmNgrade (summarizableId ?patientId))
     (printout t "pN1mi-1 adding tnmNgrade from micrometastatic lymph nodes" crlf))

;; ============================================================
;;
;; pN0-1	
;;       If populating a TNM target template, and
;;     there is no mention of Lymph Node Involvement default to pN0
;;
;; ============================================================

(defrule pN0-1 "Default value for lymph node involvement"
     (declare (salience 750))
     ?g <- (Goal (name ?name&:(eq ?name "test"))(isActive 1))
     (Patient (id ?patientId))
     (not (GenericRegionalLymphNodesTnmFinding (summarizableId ?patientId)))
=>
     (bind ?tnmNgrade (add (new N0StageFinding)))
     (modify ?tnmNgrade (summarizableId ?patientId))
     (printout t "pN0-1 adding tnmNgrade based on no mentioned lymphatic involvment" crlf))

;; ============================================================
;;
;; Class-to-code-transfer
;;    Populates Summary classes with NA codes with the class name.
;;
;; ============================================================
(defrule Class-to-code-transfer "Populates Summary objects that have  NA codes with the class name"
     (declare (salience 1500))
     ?g <- (Goal (name ?name&:(eq ?name "test"))(isActive 1))
     ?n <- (Summary
                (code ?code&:(eq ?code "NA"))
                (OBJECT ?javaObj))
=>
     (bind ?cls (call ?javaObj getClass))
     (bind ?clsName (call ?cls getSimpleName))
     (modify ?n (code ?clsName))
     (printout t "Class-to-code-transfer adding code transfer " ?clsName crlf))


;; ============================================================
;;
;; pT-1
;;       If document has dx (microinvasive & LCIS) or (microinvasive & DCIS), then create pT1
;;

;; ============================================================"
(defrule pT1-1-DCIS "Creates pT1 from isolated microinvansion and DCIS"
     (declare (salience 1500))
     ?g <- (Goal (name ?name&:(eq ?name "test"))(isActive 1))
     (IntraductalBreastNeoplasm)
     (MicroinvasiveBreastCarcinoma)
     (Patient (id ?patientId))
     (not (GenericPrimaryTumorTnmFinding (summarizableId ?patientId)))
=>
     (bind ?tnmTgrade (add (new T1StageFinding)))
     (modify ?tnmTgrade (summarizableId ?patientId))
     (printout t "pT1-1-DCIS adding tnmTgrade 1" crlf))

(defrule pTis-1-LCIS "Creates pT0 from isolated LCIS"
     (declare (salience 1500))
     ?g <- (Goal (name ?name&:(eq ?name "test"))(isActive 1))
     (LobularBreastCarcinomaInSitu)
     (MicroinvasiveBreastCarcinoma)
     (Patient (id ?patientId))
     (not (GenericPrimaryTumorTnmFinding (summarizableId ?patientId)))
=>
     (bind ?tnmTgrade (add (new T1StageFinding)))
     (modify ?tnmTgrade (summarizableId ?patientId))
     (printout t "pTis-1-LCIS adding tnmTgrade 1" crlf))

;; ============================================================
;;
;; pTis-1
;;       If document has dx of (DCIS and/or LCIS) ONLY, then create pTis
;;       To implement this we'll use cuis for DCIS, LCIS
;;
;; ============================================================"

(defrule pTis-1-DCIS "Creates pT0 from isolated DCIS"
     (declare (salience 1000))
     ?g <- (Goal (name ?name&:(eq ?name "test"))(isActive 1))
     (IntraductalBreastNeoplasm)
     (Patient (id ?patientId))
     (not (GenericPrimaryTumorTnmFinding (summarizableId ?patientId)))
     (not (MalignantBreastNeoplasm))
=>
     (bind ?tnmTgrade (add (new TisStageFinding)))
     (modify ?tnmTgrade (summarizableId ?patientId))
     (printout t "pTis-1-DCIS adding tnmTgrade tis" crlf))

(defrule pTis-1-LCIS "Creates pT0 from isolated LCIS"
     (declare (salience 1000))
     ?g <- (Goal (name ?name&:(eq ?name "test"))(isActive 1))
     (LobularBreastCarcinomaInSitu)
     (Patient (id ?patientId))
     (not (GenericPrimaryTumorTnmFinding (summarizableId ?patientId)))
     (not (MalignantBreastNeoplasm))
=>
     (bind ?tnmTgrade (add (new TisStageFinding)))
     (modify ?tnmTgrade (summarizableId ?patientId))
     (printout t "pTis-1-LCIS adding tnmTgrade tis" crlf))


;; ============================================================
;;
;; Done-testing
;;       Done testing
;;
;; ============================================================

(defrule Done-testing "Removes testing goal "
     (declare (salience 500))
     ?g <- (Goal (name ?name&:(eq ?name "test"))(isActive 1))
=>
     (retract ?g)
     (printout t "Testing is complete." crlf))
