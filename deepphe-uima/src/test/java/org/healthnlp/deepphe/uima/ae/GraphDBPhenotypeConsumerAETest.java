package org.healthnlp.deepphe.uima.ae;

import static org.apache.ctakes.dependency.parser.util.DependencyUtility.logger;
import static org.junit.Assert.fail;

import java.io.File;
import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Path;

import org.apache.commons.io.FileUtils;
import org.apache.uima.resource.ResourceInitializationException;
import org.healthnlp.deepphe.fhir.summary.MedicalRecord;
import org.healthnlp.deepphe.graph.GraphObjectFactory;
import org.healthnlp.deepphe.graph.summary.CancerSummary;
import org.healthnlp.deepphe.uima.fhir.FHIRObjectMocker;
import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;
import org.neo4j.ogm.session.SessionFactory;


public class GraphDBPhenotypeConsumerAETest {


    private static final String TEST_DB = "testdb";


    @BeforeClass
    public static void cleanupDB() throws IOException {
        Path p = FileSystems.getDefault().getPath(TEST_DB);
        FileUtils.deleteDirectory(p.toFile());
    }

    @Test
    public void initializationTest() {
        try {
            String dbPath = TEST_DB + File.separator;
            GraphDBPhenotypeConsumerAE ae = new GraphDBPhenotypeConsumerAE();
            SessionFactory sf = ae.initializeGraphDatabase(dbPath);
            sf.openSession();
            ae.destroy();

        } catch (ResourceInitializationException e) {
            e.printStackTrace();
            fail(e.getMessage());
        }
    }

    @Test
    public void processTest() {
        String dbPath = TEST_DB + File.separator;

        GraphDBPhenotypeConsumerAE ae = new GraphDBPhenotypeConsumerAE();
        SessionFactory sf;
        try {
            sf = ae.initializeGraphDatabase(dbPath);

            FHIRObjectMocker mocker = new FHIRObjectMocker();
            MedicalRecord mr = mocker.getMedicalRecord();
            ae.processPatient(sf.openSession(), mr);


        } catch (ResourceInitializationException e) {
            e.printStackTrace();
        }


    }

}
