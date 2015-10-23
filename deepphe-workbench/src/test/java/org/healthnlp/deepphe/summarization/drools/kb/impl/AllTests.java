package org.healthnlp.deepphe.summarization.drools.kb.impl;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

@RunWith(Suite.class)
@SuiteClasses({ TesterBreastCancerPlanner.class,
		TesterBreastCancerPrimaryTumor.class,
		TesterBreastCancerTumorSize.class,
		TesterBreastCancerTnm.class,
		TesterBreastCancerStage.class,
		TesterBreastCancerReceptorStatus.class
})
public class AllTests {

}
