package DB;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

/**
 * Created by DNS on 20.04.2017.
 */
public class Pars extends DefaultHandler {
    long summ = 0;



    @Override
    public void startElement(String namespaceURI, String localName, String qName, Attributes atts) throws SAXException {
        if (qName.equals("entry")) {
            summ += Integer.valueOf(atts.getValue(0));
        }
    }

    public long getSumm() {
        return summ;
    }
}
