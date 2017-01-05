package com.jiuyi.bcms.util;

import java.io.File;
import java.io.InputStream;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.apache.commons.io.IOUtils;
import org.w3c.dom.Document;

public class DocumentUtil {
    public static Document getDocFromInputStream(InputStream is) throws Exception {
        return getDocBuilder().parse(is);
    }

    public static Document getDocFromFile(File f) throws Exception {
        return getDocBuilder().parse(f);
    }

    public static DocumentBuilder getDocBuilder() throws ParserConfigurationException {
        DocumentBuilderFactory docBuilderFactory = DocumentBuilderFactory.newInstance();
        docBuilderFactory.setNamespaceAware(true);
        DocumentBuilder docBuilder = docBuilderFactory.newDocumentBuilder();
        return docBuilder;
    }

    public static Document getDocFromString(String xml) throws Exception {
        InputStream is = IOUtils.toInputStream(xml, "UTF-8");
        return getDocFromInputStream(is);
    }

}
