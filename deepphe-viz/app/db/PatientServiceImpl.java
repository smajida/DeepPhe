package db;

import db.GenericService;
import db.Patient;
import db.PatientService;

/**
 * Created by harry on 5/8/16.
 */
//@Service("patientService")
public class PatientServiceImpl extends GenericService<Patient> implements PatientService {
    @Override
    public Class<Patient> getEntityType() {
        return Patient.class;
    }

}
