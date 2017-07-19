package XML;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;


public class ParsSAX extends DefaultHandler {
    private long sumOfValuesF​field = 0;

    @Override
    public void startElement(String namespaceURI, String localName, String qName, Attributes atts) throws SAXException {
        if (qName.equals("entry")) {
            sumOfValuesF​field += Integer.valueOf(atts.getValue(0));
        }
    }

    public long getSumOfValuesF​field() {
        return sumOfValuesF​field;
    }
}
