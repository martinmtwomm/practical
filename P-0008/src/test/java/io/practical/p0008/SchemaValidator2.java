package io.practical.p0008;

import java.io.File;
import java.io.IOException;

import javax.xml.XMLConstants;
import javax.xml.transform.Source;
import javax.xml.transform.stream.StreamSource;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import javax.xml.validation.Validator;

import org.xml.sax.SAXException;

public class SchemaValidator2 {
	public SchemaValidator2(String filename, String schemaFilename) {
		File xmlFile = new File(getClass().getClassLoader().getResource(filename).getFile());
		File schemaFile = new File(getClass().getClassLoader().getResource(schemaFilename).getFile());
		Source xmlSource = new StreamSource(xmlFile);

        System.setProperty(SchemaFactory.class.getName() + ":" + XMLConstants.RELAXNG_NS_URI, "com.thaiopensource.relaxng.jaxp.CompactSyntaxSchemaFactory");

		SchemaFactory schemaFactory = SchemaFactory.newInstance(XMLConstants.RELAXNG_NS_URI);

		try {
			Schema schema = schemaFactory.newSchema(schemaFile);
			Validator validator = schema.newValidator();
			validator.validate(xmlSource);
			System.out.println(xmlSource.getSystemId() + " is valid");
		} catch (SAXException e) {
			System.out.println(xmlSource.getSystemId() + " is NOT valid reason:" + e);
		} catch (IOException e) {
		}
	}
}
