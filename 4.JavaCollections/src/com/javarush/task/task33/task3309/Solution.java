package com.javarush.task.task33.task3309;

import com.sun.xml.internal.txw2.Document;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.StringWriter;

/*
Комментарий внутри xml
*/
public class Solution {
    public static String toXmlWithComment(Object obj, String tagName, String comment) throws JAXBException {
        StringWriter writer = new StringWriter();
        JAXBContext context = JAXBContext.newInstance(obj.getClass());
        Marshaller marshaller = context.createMarshaller();
        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT,Boolean.TRUE);
        marshaller.marshal(obj,writer);
//        Document document =

        return writer.toString();
    }

    public static void main(String[] args) throws JAXBException {
        Test firstSecondObject = new Test();
        System.out.println(toXmlWithComment(firstSecondObject, "second", "it's a comment"));
    }

    @XmlRootElement
    public static class Test{
        public int anInt;
    }
}
