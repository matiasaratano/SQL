package com.solvd.app.utils.xml;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.xml.sax.SAXException;

import javax.xml.XMLConstants;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import java.io.File;

public class XMLValidator {

    private final static Logger LOGGER = LogManager.getLogger(XMLValidator.class);

    public static void main(String[] args) {
        try {
            // Create a SAXParserFactory
            SAXParserFactory factory = SAXParserFactory.newInstance();

            // Set the schema to use for validation
            SchemaFactory schemaFactory = SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);
            Schema schema = schemaFactory.newSchema(new File("./src/main/resources/xmlFiles/schema.xsd"));
            factory.setSchema(schema);

            // Create a SAXParser
            SAXParser parser = factory.newSAXParser();

            // Parse the XML file and validate it against the schema
            parser.parse("./src/main/resources/xmlFiles/schema.xml", new MyHandler());

            LOGGER.info("XML file is valid against the schema.");
        } catch (SAXException e) {
            LOGGER.info("XML file is not valid against the schema.");
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}