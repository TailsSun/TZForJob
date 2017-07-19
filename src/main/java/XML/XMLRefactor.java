package XML;

import javax.xml.transform.Source;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;
import java.io.File;


public class XMLRefactor {
    public static void refactorXSLT() {
        TransformerFactory tFactory = TransformerFactory.newInstance();
        Source file = new StreamSource(new File("1.xml"));
        try {
            Transformer transformer = tFactory.newTransformer(new StreamSource(new File("src/main/resources/prices.xsl")));
            transformer.transform(file, new StreamResult(new File("2.xml")));
        } catch (TransformerException e) {
            e.printStackTrace();
        }
    }
}
