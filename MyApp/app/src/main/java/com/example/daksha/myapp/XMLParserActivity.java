package com.example.daksha.myapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import java.io.InputStream;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

public class XMLParserActivity extends AppCompatActivity {

    private TextView tvEmployeeDetails;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.xmlparser_layout);

        tvEmployeeDetails = (TextView) findViewById(R.id.tvEmployeeDetails);

        try {
            InputStream is = getAssets().open("xmlParser.xml");

            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.parse(is);

            Element element=doc.getDocumentElement();
            element.normalize();

            NodeList nList = doc.getElementsByTagName("employee");

            for (int i=0; i<nList.getLength(); i++) {

                Node node = nList.item(i);
                if (node.getNodeType() == Node.ELEMENT_NODE) {
                    Element element2 = (Element) node;
                    tvEmployeeDetails.setText(tvEmployeeDetails.getText()+"\nName : " + getValue("name", element2)+"\n");
                    tvEmployeeDetails.setText(tvEmployeeDetails.getText()+"Salary : " + getValue("salary", element2)+"\n");
                    tvEmployeeDetails.setText(tvEmployeeDetails.getText()+"Role : " + getValue("role", element2)+"\n");
                    tvEmployeeDetails.setText(tvEmployeeDetails.getText()+"-----------------------");
                }
            }

        } catch (Exception e) {e.printStackTrace();}

    }

    private static String getValue(String tag, Element element) {
        NodeList nodeList = element.getElementsByTagName(tag).item(0).getChildNodes();
        Node node = nodeList.item(0);
        return node.getNodeValue();
    }
}
