package dom;


import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;
import reflection.ReflectionHelper;
import resources.Employee;


import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.IOException;


public class DOMParser {
    private NodeList nodes;
    private Element element;
    private Employee employee;

    public DOMParser(String filePath) throws ParserConfigurationException, IOException, SAXException {
        nodes = createDocumentBuilder().parse(new File(filePath)).getElementsByTagName("class");
    }

    private DocumentBuilder createDocumentBuilder() throws ParserConfigurationException {
        return DocumentBuilderFactory.newInstance().newDocumentBuilder();
    }
    public void createObject(){
        employee = (Employee) ReflectionHelper.createInstance(this.getClassName());
    }

    private String getClassName() {
        element = (Element) nodes.item(0);
        return element.getAttribute("name");
    }

    public Employee getObject() {
        return employee;
    }
    public NodeList getNodes(){
        return nodes;
    }

    public void setFields(int i) {
        element = (Element) nodes.item(i);
        try {
            ReflectionHelper.setFieldValue(employee, "age", this.getField("age"));
            ReflectionHelper.setFieldValue(employee, "name", this.getField("name"));
            ReflectionHelper.setFieldValue(employee, "role", this.getField("role"));
            ReflectionHelper.setFieldValue(employee, "gender", this.getField("gender"));
        } catch (IllegalAccessException | NoSuchFieldException e) {
            e.printStackTrace();
        }
    }

    private String getField(String fieldName) {
        return element.getElementsByTagName(fieldName).item(0).getChildNodes().item(0).getTextContent();
    }

}
