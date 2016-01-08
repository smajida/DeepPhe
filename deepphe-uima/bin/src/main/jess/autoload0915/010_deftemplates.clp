(deftemplate Identified 
     (declare (from-class Identified)
                  (include-variables TRUE)))
(deftemplate Goal extends Identified 
     (declare (from-class Goal)
                  (include-variables TRUE)))
(deftemplate Summarizable extends Identified 
     (declare (from-class Summarizable)
                  (include-variables TRUE)))
(deftemplate Summary extends Identified 
     (declare (from-class Summary)
                  (include-variables TRUE)))
(deftemplate Patient extends Summarizable 
     (declare (from-class Patient)
                  (include-variables TRUE)))
(deftemplate Encounter extends Summarizable 
     (declare (from-class Encounter)
                  (include-variables TRUE)))
(deftemplate Diagnosis extends Summary 
     (declare (from-class Diagnosis)
                  (include-variables TRUE)))
(deftemplate Finding extends Summary 
     (declare (from-class Finding)
                  (include-variables TRUE)))
(deftemplate Er extends Summary 
     (declare (from-class Er)
                  (include-variables TRUE)))
(deftemplate Pr extends Summary 
     (declare (from-class Pr)
                  (include-variables TRUE)))
(deftemplate Her2 extends Summary 
     (declare (from-class Her2)
                  (include-variables TRUE)))
(deftemplate ReceptorStatusCalculator extends Summary 
     (declare (from-class ReceptorStatusCalculator)
                  (include-variables TRUE)))
(deftemplate TnmTgrade extends Summary 
     (declare (from-class TnmTgrade)
                  (include-variables TRUE)))
(deftemplate TnmNgrade extends Summary 
     (declare (from-class TnmNgrade)
                  (include-variables TRUE)))
(deftemplate TnmMgrade extends Summary 
     (declare (from-class TnmMgrade)
                  (include-variables TRUE)))
(deftemplate TumorSizeCalculator extends Summary 
     (declare (from-class TumorSizeCalculator)
                  (include-variables TRUE)))
