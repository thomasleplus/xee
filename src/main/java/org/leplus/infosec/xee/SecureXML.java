package org.leplus.infosec.xee;

import javax.xml.XMLConstants;
import javax.xml.validation.SchemaFactory;

import org.xml.sax.SAXNotRecognizedException;
import org.xml.sax.SAXNotSupportedException;

public class SecureXML {
	
    public static SchemaFactory createSchemaFactory(final String schemaLanguage) throws SAXNotRecognizedException, SAXNotSupportedException {
    	final SchemaFactory sf = SchemaFactory.newInstance(schemaLanguage);
		sf.setFeature(XMLConstants.FEATURE_SECURE_PROCESSING, true);
		sf.setFeature("http://apache.org/xml/features/disallow-doctype-decl", true);
		// sf.setProperty(XMLConstants.ACCESS_EXTERNAL_DTD, "");
		// sf.setProperty(XMLConstants.ACCESS_EXTERNAL_SCHEMA, "");
		// throws org.xml.sax.SAXNotRecognizedException: Property 'http://javax.xml.XMLConstants/property/...' is not recognized.
		return sf;
    }
    
}
