package org.leplus.infosec.xee;

import java.io.InputStream;
import java.io.Reader;
import java.io.StringReader;
import javax.xml.XMLConstants;
import javax.xml.validation.SchemaFactory;
import javax.xml.validation.Validator;
import org.w3c.dom.ls.LSInput;
import org.xml.sax.SAXNotRecognizedException;
import org.xml.sax.SAXNotSupportedException;

/** Secure XML parser factory. */
public final class SecureXML {

  /** Private constructor to prevent instantiation. */
  private SecureXML() {
    super();
  }

  /**
   * Creates a secured schema factory.
   *
   * @param schemaLanguage the desired schema language.
   * @return the schema factory.
   * @throws SAXNotRecognizedException if a required security feature is not recognized.
   * @throws SAXNotSupportedException if a required security feature is not supported.
   */
  public static SchemaFactory createSchemaFactory(final String schemaLanguage)
      throws SAXNotRecognizedException, SAXNotSupportedException {
    final SchemaFactory sf = SchemaFactory.newInstance(schemaLanguage);
    sf.setFeature(XMLConstants.FEATURE_SECURE_PROCESSING, true);
    sf.setFeature("http://apache.org/xml/features/disallow-doctype-decl", true);
    // sf.setProperty(XMLConstants.ACCESS_EXTERNAL_DTD, "");
    // sf.setProperty(XMLConstants.ACCESS_EXTERNAL_SCHEMA, "");
    // throws org.xml.sax.SAXNotRecognizedException: Property
    // 'http://javax.xml.XMLConstants/property/...' is not recognized.
    return sf;
  }

  /**
   * Secures an existing validator against XML External Entity (XEE) attacks.
   *
   * @param validator the validator to secure.
   * @return the secured validator.
   * @throws SAXNotRecognizedException if a required security feature is not recognized.
   * @throws SAXNotSupportedException if a required security feature is not supported.
   */
  public static Validator secureValidator(final Validator validator)
      throws SAXNotRecognizedException, SAXNotSupportedException {
    validator.setFeature(XMLConstants.FEATURE_SECURE_PROCESSING, true);
    validator.setResourceResolver(
        (type, namespaceURI, publicId, systemId, baseURI) ->
            new LSInput() {
              @Override
              public Reader getCharacterStream() {
                return new StringReader("");
              }

              @Override
              public void setCharacterStream(final Reader characterStream) {}

              @Override
              public InputStream getByteStream() {
                return null;
              }

              @Override
              public void setByteStream(final InputStream byteStream) {}

              @Override
              public String getStringData() {
                return "";
              }

              @Override
              public void setStringData(final String stringData) {}

              @Override
              public String getSystemId() {
                return systemId;
              }

              @Override
              public void setSystemId(final String systemId) {}

              @Override
              public String getPublicId() {
                return publicId;
              }

              @Override
              public void setPublicId(final String publicId) {}

              @Override
              public String getBaseURI() {
                return baseURI;
              }

              @Override
              public void setBaseURI(final String baseURI) {}

              @Override
              public String getEncoding() {
                return null;
              }

              @Override
              public void setEncoding(final String encoding) {}

              @Override
              public boolean getCertifiedText() {
                return false;
              }

              @Override
              public void setCertifiedText(final boolean certifiedText) {}
            });
    return validator;
  }
}
