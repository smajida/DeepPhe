(defrule retract-identifiables  "retracts all identifiables"
     (declare (salience 100))
      (Goal (id ?gId)  (name "retract-identifiables"))
      ?identified <- (Identified (id ?otherId&:(neq ?gId ?otherId)))
=>
      (retract ?identified))

(defrule finished-retract-identifiables "done retracting so remove goal"
     (declare (salience 50))
      ?g <- (Goal (id ?gId)  (name "retract-identifiables"))
=>
      (retract ?g))

(deffunction retract-identifiables () "retracts all identifiables"
     (assert (Goal (name "retract-identifiables")))
     (run))