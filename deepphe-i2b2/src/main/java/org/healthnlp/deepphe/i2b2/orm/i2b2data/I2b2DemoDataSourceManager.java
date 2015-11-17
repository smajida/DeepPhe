package org.healthnlp.deepphe.i2b2.orm.i2b2data;

import org.healthnlp.deepphe.i2b2.orm.I2b2DataSourceManager;
import org.hibernate.Session;

public class I2b2DemoDataSourceManager extends I2b2DataSourceManager {
	
	protected void addAnnotatedClasses() {
		configuration.addAnnotatedClass(ObservationFact.class);
		configuration.addAnnotatedClass(ProviderDimension.class);
		configuration.addAnnotatedClass(VisitDimension.class);
		configuration.addAnnotatedClass(PatientDimension.class);
		configuration.addAnnotatedClass(ConceptDimension.class);

	}
	
	public Session getSession() {
		dbPropertiesUrl = getClass().getResource("i2b2demodata.properties");
		return super.getSession();
	}

}
