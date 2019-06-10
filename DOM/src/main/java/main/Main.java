package main;

import dom.DOMParser;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;
import resources.Employee;


import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


public class Main {
    public static void main(String[] args) {
        try {
            DOMParser domParser = new DOMParser("./data/employee.xml");
            NodeList nodeList = domParser.getNodes();
            List<Employee>employeeList = new ArrayList<Employee>();
            for(int i = 0; i < nodeList.getLength(); i++){
                domParser.createObject();
                domParser.setFields(i);
                employeeList.add(domParser.getObject());
            }
            employeeList.forEach(System.out::println);
        } catch (ParserConfigurationException | IOException | SAXException e) {
            e.printStackTrace();
        }

    }

    }

