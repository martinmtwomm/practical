package io.practical.p0007;

import java.io.File;
import java.io.IOException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.xml.sax.SAXException;

public class XmlDomParser {

	public XmlDomParser(String filename) {
		File resource = new File(getClass().getClassLoader().getResource(filename).getFile());
		DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
		DocumentBuilder dBuilder;

		Document doc;
		try {
			dBuilder = dbFactory.newDocumentBuilder();
			doc = dBuilder.parse(resource);
			doc.getDocumentElement().normalize();
		} catch (SAXException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ParserConfigurationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
}
