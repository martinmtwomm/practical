package io.practical.p0007;

import java.io.File;
import java.io.IOException;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

public class XmlParserSax {

	public XmlParserSax(String filename) {
		SAXParserFactory saxParserFactory = SAXParserFactory.newInstance();

		SAXParser saxParser;
		try {
			saxParser = saxParserFactory.newSAXParser();
			ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
			File file = new File(classLoader.getResource(filename).getFile());

			DefaultHandler handler = new DefaultHandler();
			saxParser.parse(file, handler);
		} catch (ParserConfigurationException e) {
			e.printStackTrace();
		} catch (SAXException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

}
