package XML;

import DB.DBService;
import DB.Test;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.*;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import java.io.File;
import java.util.ArrayList;


public class XMLCreate {

    public static void createXML(DBService dbService) {
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        factory.setNamespaceAware(true);
        Document doc = null;
        try {
            doc = factory.newDocumentBuilder().newDocument();
        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        }
        Element root = doc.createElement("entries");
        doc.appendChild(root);
        ArrayList<Test> allValueFoBD = dbService.getAllElement();
        Element entry;
        Element field;

        for (Test result : allValueFoBD) {
            entry = doc.createElement("entry");
            root.appendChild(entry);
            field = doc.createElement("field");
            field.appendChild(doc.createTextNode(String.valueOf(result.getTestValue())));
            entry.appendChild(field);
        }
        File file = new File("1.xml");
        
        try {
            Transformer transformer = TransformerFactory.newInstance().newTransformer();
            transformer.setOutputProperty(OutputKeys.INDENT, "yes");
            transformer.transform(new DOMSource(doc), new StreamResult(file));
        } catch (TransformerConfigurationException e) {
            e.printStackTrace();
        } catch (TransformerException e) {
            e.printStackTrace();
        }
    }
}
