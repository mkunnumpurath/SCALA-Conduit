package org.scalaconduit.spi
import javax.xml.bind.annotation.XmlRootElement
import javax.xml.bind.JAXBContext
import java.io.StringReader
import java.io.StringWriter

class Transformer(payload: AnyRef) {
    
    def | [T] (targetType : Class[T]) : T = {
        // String to JAXB
        if (payload.isInstanceOf[String] && targetType.isAnnotationPresent(classOf[XmlRootElement])) {
            val xml = payload.asInstanceOf[String]
            val unmarshaller = JAXBContext.newInstance(targetType).createUnmarshaller()
            val reader = new StringReader(xml)
            unmarshaller.unmarshal(reader).asInstanceOf[T]
        // XML to JAXB
        } else if (targetType.equals(classOf[String]) && payload.getClass().isAnnotationPresent(classOf[XmlRootElement])) {
            val marshaller = JAXBContext.newInstance(payload.getClass()).createMarshaller()
            val writer = new StringWriter()
            marshaller.marshal(payload, writer)
            writer.toString().asInstanceOf[T]
        }
        else {
        	null.asInstanceOf[T]
        }
    }

}