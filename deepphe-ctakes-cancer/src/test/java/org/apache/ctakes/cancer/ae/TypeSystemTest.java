package org.apache.ctakes.cancer.ae;

import static org.junit.Assert.*;

import java.io.FileWriter;
import java.io.IOException;

import org.apache.uima.fit.factory.TypeSystemDescriptionFactory;
import org.apache.uima.resource.ResourceInitializationException;
import org.apache.uima.resource.metadata.TypeSystemDescription;
import org.junit.Ignore;
import org.junit.Test;
import org.xml.sax.SAXException;

public class TypeSystemTest {

    @Ignore
    @Test
    public void testTypeSystem() throws Exception {
		TypeSystemDescription typesystem = TypeSystemDescriptionFactory
				.createTypeSystemDescription();
		typesystem.toXML(System.out);
		//typesystem.toXML(new FileWriter("SuperDuperTypeSystem.xml"));
	}

}
