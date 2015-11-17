/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.healthnlp.deepphe.i2b2.orm.i2b2data;

import org.junit.After;
import org.junit.Before;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;

/**
 *
 * @author kjm84
 */
@RunWith(Suite.class)
@Suite.SuiteClasses({
    org.healthnlp.deepphe.i2b2.orm.i2b2data.ObservationFactTest.class,
    org.healthnlp.deepphe.i2b2.orm.i2b2data.ProviderDimensionTest.class,
    org.healthnlp.deepphe.i2b2.orm.i2b2data.VisitDimensionTest.class,
    org.healthnlp.deepphe.i2b2.orm.i2b2data.PatientDimensionTest.class,
    org.healthnlp.deepphe.i2b2.orm.i2b2data.ConceptDimensionTest.class})
public class OrmDataTestSuite {

    @Before
    public void setUp() throws Exception {
    }

    @After
    public void tearDown() throws Exception {
    }

}
