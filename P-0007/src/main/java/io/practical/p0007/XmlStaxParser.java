package io.practical.p0007;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import javax.xml.stream.XMLEventReader;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.events.XMLEvent;

public class XmlStaxParser {
	public XmlStaxParser(String filename) {

		XMLInputFactory xmlInputFactory = XMLInputFactory.newInstance();
		XMLEventReader xmlEventReader;

		ClassLoader loader = Thread.currentThread().getContextClassLoader();
		Properties props = new Properties();
		try (InputStream resourceStream = loader.getResourceAsStream(filename)) {
			props.load(resourceStream);
			xmlEventReader = xmlInputFactory.createXMLEventReader(resourceStream);
			while (xmlEventReader.hasNext()) {
				XMLEvent xmlEvent = xmlEventReader.nextEvent();
			}
		} catch (IOException e1) {
			e1.printStackTrace();
		} catch (XMLStreamException e) {
			e.printStackTrace();
		}

	}
}
