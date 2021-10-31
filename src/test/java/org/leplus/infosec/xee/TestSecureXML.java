package org.leplus.infosec.xee;

import java.io.ByteArrayInputStream;
import java.io.IOException;

import javax.xml.XMLConstants;
import javax.xml.transform.stream.StreamSource;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import javax.xml.validation.Validator;

import org.junit.jupiter.api.Test;
import org.xml.sax.SAXException;

public class TestSecureXML {
	
	public static final String VALID_XML_SCHEMA
			= "<?xml version=\"1.0\" encoding=\"UTF-8\"?>"
			+ "<xs:schema xmlns:xs=\"http://www.w3.org/2001/XMLSchema\">"
			+ "  <xs:element name=\"note\"/>"
			+ "</xs:schema>";

	public static final String VALID_XML_DOC_WITH_EXTERNAL_GENERAL_ENTITY
			= "<?xml version=\"1.0\" encoding=\"UTF-8\"?>"
			+ "<!DOCTYPE note ["
			+ "  <!ELEMENT note ANY >"
			+ "  <!ENTITY space SYSTEM \"note.dtd\">"
			+ "]>"
			+ "<note>&space;</note>";

	@Test
	public void testCreateSchemaFactory() throws SAXException, IOException {
		final SchemaFactory factory = SecureXML.createSchemaFactory(XMLConstants.W3C_XML_SCHEMA_NS_URI);
		final Schema schema = factory.newSchema(new StreamSource(new ByteArrayInputStream(VALID_XML_SCHEMA.getBytes())));
		final Validator validator = schema.newValidator();
		validator.validate(new StreamSource(new ByteArrayInputStream(VALID_XML_DOC_WITH_EXTERNAL_GENERAL_ENTITY.getBytes())));
	}

}
