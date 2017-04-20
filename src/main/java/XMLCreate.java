import DB.DBService;
import DB.Pars;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import javax.xml.transform.*;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

/**
 * Created by DNS on 19.04.2017.
 */
public class XMLCreate {

    public static void createXML(DBService dbService) throws ParserConfigurationException, TransformerException {
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        factory.setNamespaceAware(true);
        Document doc = factory.newDocumentBuilder().newDocument();
        Element root = doc.createElement("entries");
        doc.appendChild(root);
        List list = dbService.get();
        Element entry;
        Element field;

        for (Object result : list) {
            entry = doc.createElement("entry");
            root.appendChild(entry);
            field = doc.createElement("field");
            field.appendChild(doc.createTextNode(result.toString()));
            entry.appendChild(field);
        }
        File file = new File("1.xml");

        Transformer transformer = TransformerFactory.newInstance().newTransformer();
        transformer.setOutputProperty(OutputKeys.INDENT, "yes");
        transformer.transform(new DOMSource(doc), new StreamResult(file));
    }

    public static void refactor() throws TransformerException, FileNotFoundException {
        TransformerFactory tFactory = TransformerFactory.newInstance();
        Transformer transformer = tFactory.newTransformer(new StreamSource(new File("prices.xsl")));
        Source file = new StreamSource(new File("1.xml"));
        transformer.transform(file, new StreamResult(new File("2.xml")));
    }

    public static long summ() throws ParserConfigurationException, SAXException, IOException {
        SAXParserFactory factory = SAXParserFactory.newInstance();
        SAXParser parser = factory.newSAXParser();
        Pars parse = new Pars();
        parser.parse(new File("2.xml"), parse);
        return parse.getSumm();
    }

}
