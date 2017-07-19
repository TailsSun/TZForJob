package XML;

import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.io.File;
import java.io.IOException;


public class XMLParser {
    public static long sumFieldValues() {
        SAXParserFactory factory = SAXParserFactory.newInstance();
        SAXParser saxParser;
        ParsSAX parsSAX = new ParsSAX();

        try {
            saxParser = factory.newSAXParser();
            saxParser.parse(new File("2.xml"), parsSAX);
        } catch (ParserConfigurationException | SAXException | IOException e) {
            e.printStackTrace();
        }
        return parsSAX.getSumOfValuesF​field();
    }
}
