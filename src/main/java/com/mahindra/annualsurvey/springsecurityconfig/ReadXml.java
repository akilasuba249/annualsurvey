package com.mahindra.annualsurvey.springsecurityconfig;

import java.io.StringReader;
import java.util.ArrayList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.CharacterData;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;

public class ReadXml {
	public ArrayList<String> parseXml(String xmlRecords) throws Exception {

		ArrayList<String> retStr = new ArrayList<String>();
		DocumentBuilder db = DocumentBuilderFactory.newInstance().newDocumentBuilder();
		InputSource is = new InputSource();
		is.setCharacterStream(new StringReader(xmlRecords));

		Document doc = db.parse(is);
		NodeList nodes = doc.getElementsByTagName("Authentication");

		for (int i = 0; i < nodes.getLength(); i++) {
			Element element = (Element) nodes.item(i);

			NodeList name = element.getElementsByTagName("authStatus");
			Element line = (Element) name.item(0);
			System.out.println("authStatus: " + getCharacterDataFromElement(line));
			retStr.add(getCharacterDataFromElement(line));

			NodeList dpNm = element.getElementsByTagName("displayName");
			Element dpNmEl = (Element) dpNm.item(0);
			System.out.println("displayName: " + getCharacterDataFromElement(line));
			retStr.add(getCharacterDataFromElement(dpNmEl));

		}

		return retStr;
	}

	public static String getCharacterDataFromElement(Element e) {
		Node child = e.getFirstChild();
		if (child instanceof CharacterData) {
			CharacterData cd = (CharacterData) child;
			return cd.getData();
		}
		return "";
	}
}