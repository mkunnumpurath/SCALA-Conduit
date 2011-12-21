package org.scalaconduit.spi
import java.io.StringReader
import java.io.StringWriter

import org.w3c.dom.Document
import org.w3c.dom.Node
import org.xml.sax.InputSource

import javax.xml.parsers.DocumentBuilderFactory
import javax.xml.transform.dom.DOMSource
import javax.xml.transform.stream.StreamResult
import javax.xml.transform.OutputKeys
import javax.xml.transform.TransformerFactory

case class Converter(payload: AnyRef) {

    def ~~~[T](targetType: Class[T]): T = {
        val documentClass = classOf[Document]
        val stringClass = classOf[String]
        (payload, targetType) match {
            case (s: String, documentClass) =>
                DocumentBuilderFactory.newInstance().newDocumentBuilder().parse(new InputSource(new StringReader(s))).asInstanceOf[T]
            case (n: Node, stringClass) =>
                val transformer = TransformerFactory.newInstance().newTransformer();
                transformer.setOutputProperty(OutputKeys.OMIT_XML_DECLARATION, "yes")
                val writer = new StringWriter();
                transformer.transform(new DOMSource(n), new StreamResult(writer));
                writer.toString().asInstanceOf[T]
            case _ =>
                Nil.asInstanceOf[T]
        }
    }

}