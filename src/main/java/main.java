import DB.DBService;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;


/**
 * Created by DNS on 19.04.2017.
 */
public class main {
    public static void main(String[] args) throws IOException, TransformerException, ParserConfigurationException, SAXException {
        DBService dbService = new DBService();
        XMLCreate xmlCreate = new XMLCreate();
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        System.out.println("Insert N");
        int n = (int) Integer.parseInt(reader.readLine());
        dbService.add(n);
        try {
            xmlCreate.createXML(dbService);
        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        } catch (TransformerException e) {
            e.printStackTrace();
        }
        xmlCreate.refactor();
        System.out.println(xmlCreate.summ());
    }
}
