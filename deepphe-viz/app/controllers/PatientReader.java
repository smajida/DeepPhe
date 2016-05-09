package controllers;

import db.DatamodelUtility;
import db.Patient;
import play.mvc.Controller;
import play.mvc.Result;
import views.html.index;
import views.html.Patients;
import db.Neo4JOgmWrapper;
import db.Patient;

import java.util.List;
import java.util.ArrayList;
import org.neo4j.ogm.session.Session;
import java.lang.Iterable;
import java.util.Iterator;

/**
 * Created by harry on 5/8/16.
 */
public class PatientReader extends Controller {

    //something from OGM to read all patients
    public Result getPatients() {
        try {
            Session session = Neo4JOgmWrapper.getInstance().getNeo4JSession();
           // get patients. List<Patient> patient= caller.getPatients();
            Iterable<Patient> patients = session.loadAll(Patient.class,0);
            Iterator<Patient> ptiter = patients.iterator();
            List<Patient> pts = new ArrayList<Patient>();
            while (ptiter.hasNext()) {
                pts.add(ptiter.next());
            }
            System.out.println(pts.size());
            return ok(Patients.render(pts));
        } catch (Exception e) {
            e.printStackTrace();
            return ok(index.render(e.getMessage()));
        }

    }
}
