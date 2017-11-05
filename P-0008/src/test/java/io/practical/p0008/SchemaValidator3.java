package io.practical.p0008;

import java.io.File;

import javax.xml.transform.Source;
import javax.xml.transform.stream.StreamSource;

import com.helger.schematron.ISchematronResource;
import com.helger.schematron.pure.SchematronResourcePure;

public class SchemaValidator3 {
	public SchemaValidator3(String filename, String schemaFilename) {
		File xmlFile = new File(getClass().getClassLoader().getResource(filename).getFile());
		File schemaFile = new File(getClass().getClassLoader().getResource(schemaFilename).getFile());
		Source xmlSource = new StreamSource(xmlFile);

		final ISchematronResource aResPure = SchematronResourcePure.fromFile(schemaFile);
		if (!aResPure.isValidSchematron())
			throw new IllegalArgumentException("Invalid Schematron!");
		try {
			boolean isValid = aResPure.getSchematronValidity(xmlSource).isValid();
			System.out.println("Schematron - isValid ?: " + isValid);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
}
