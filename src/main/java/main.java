import DB.DBService;
import XML.XMLCreate;
import XML.XMLParser;
import XML.XMLRefactor;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;


/**
 * sorry for the bad code, but I'll be better
 */
public class main {
    public static void main(String[] args) {
        DBService dbService = new DBService();
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        System.out.println("Insert N");
        int n = 0;
        try {
            n = Integer.parseInt(reader.readLine());
        } catch (IOException e) {
            e.printStackTrace();
        }
        dbService.add(n);
        XMLCreate.createXML(dbService);
        XMLRefactor.refactorXSLT();
        System.out.println(XMLParser.sumFieldValues());
    }
}
