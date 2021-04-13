package com.maxi.test.components;

import com.maxi.test.dao.check.Check;
import com.maxi.test.dao.salelines.SaleLine;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Scope;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;
import org.w3c.dom.ls.LSException;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Component("saxHandler")
@Scope("prototype")
public class SaxHandler extends DefaultHandler{

    private Check check;
    private SaleLine line;
    private final List<SaleLine> lines = new ArrayList<>();
    private int counter = 0;
    private String content = null;

    private final XmlProcessor processor;
    private final Integer batchSize;

    public SaxHandler(XmlProcessor processor, @Value("${loc.hibernate.batch-size:1}") Integer batchSize) {
        this.batchSize = batchSize;
        this.processor = processor;
    }

    @Override
    public void startDocument() throws SAXException {
        super.startDocument();
    }

    @Override
    public void endDocument() throws SAXException {
        super.endDocument();
    }

    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
        switch (qName) {
            case "SALE":
                this.check = new Check();
                break;
            case "PRODUCT":
                this.line = new SaleLine();
                break;
        }
    }

    @Override
    public void endElement(String uri, String localName, String qName) throws SAXException {
        switch (qName) {
            case "SALE":
                counter++;
                if (counter >= batchSize) {
                    processor.process(check, lines);
                    lines.clear();
                    counter = 0;
                }
                break;
            case "CARD_NUMBER":
                this.check.setKppNumber(Integer.parseInt(content));
                break;
            case "DATE":
                this.check.setPrintedDate(new Timestamp(Long.parseLong(content)));
                break;
            case "PRODUCT":
                this.line.setCheck(check);
                lines.add(line);
                break;
            case "PRODUCT_CODE":
                this.line.setArticleNumber(Integer.parseInt(content));
                break;
            case "NAME":
                this.line.setName(content);
                break;
            case "PRICE":
                this.line.setPriceOne(Double.parseDouble(content.replace(",", ".")));
                break;
            case "COUNT":
                this.line.setQuantity(Integer.parseInt(content));
                break;
        }
    }

    @Override
    public void characters(char[] ch, int start, int length) throws SAXException {
        content = String.copyValueOf(ch, start, length).trim();
    }


}
