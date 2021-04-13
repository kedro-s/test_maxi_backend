package com.maxi.test.components;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.core.io.Resource;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Component;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.io.File;
import java.io.IOException;

@Component
public class XmlLoader {
    public static void load(File file_in, XmlProcessor processor,
                            @Value("${loc.hibernate.batch-size:1}") Integer batchSize) throws IOException,
            ParserConfigurationException, SAXException {
        SAXParserFactory factory = SAXParserFactory.newInstance();
        DefaultHandler handler = new SaxHandler(processor, batchSize);
        SAXParser parser = factory.newSAXParser();
        parser.parse(file_in, handler);
    }
}
