package org.healthnlp.deepphe.i2b2.orm.i2b2meta;

import org.healthnlp.deepphe.i2b2.orm.I2b2DataSourceManager;
import org.hibernate.Session;

public class I2b2MetaDataSourceManager extends I2b2DataSourceManager {
	
	protected void addAnnotatedClasses() {
        configuration.addAnnotatedClass(DeeppheOntology.class);
        configuration.addAnnotatedClass(OntProcessStatus.class);
        configuration.addAnnotatedClass(TableAccess.class);
    }
	
	public Session getSession() {
		dbPropertiesUrl = getClass().getResource("i2b2metadata.properties");
		return super.getSession();
	}

}
