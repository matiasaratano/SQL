package com.solvd.app.utils.xml;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.xml.sax.SAXException;

import javax.xml.XMLConstants;
import javax.xml.transform.stream.StreamSource;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import javax.xml.validation.Validator;
import java.io.File;
import java.io.IOException;

public class XMLValidator {

    private final static Logger LOGGER = LogManager.getLogger(XMLValidator.class);

    public static void main(String[] args) {
        try {
            //
            String xsdPath = "./src/main/resources/xml.files/schema.xsd";
            String xmlPath = "./src/main/resources/xml.files/schema.xml";
            boolean valid = validateXMLSchema(xsdPath, xmlPath);
            LOGGER.info(String.format("XML file is %s", (valid ? "valid." : "invalid.")));


            // Create a SAXParserFactory
            //SAXParserFactory factory = SAXParserFactory.newInstance();

            // Create a SAXParser
            //SAXParser parser = factory.newSAXParser();

            //parser.parse("./src/main/resources/xmlFiles/schema.xml", new MyHandler());
        } catch (SAXException e) {
            throw new RuntimeException(e);
        }
    }

    public static boolean validateXMLSchema(String xsdPath, String xmlPath) throws SAXException {
        try {
            SchemaFactory factory = SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);
            Schema schema = factory.newSchema(new File(xsdPath));
            Validator validator = schema.newValidator();
            validator.validate(new StreamSource(new File(xmlPath)));
        } catch (IOException e) {
            LOGGER.error("Exception: " + e.getMessage());
            return false;
        }
        return true;
    }
}