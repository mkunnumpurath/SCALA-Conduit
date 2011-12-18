package org.scalaconduit.spi
import javax.xml.parsers.DocumentBuilderFactory
import java.io.StringReader
import org.xml.sax.InputSource
import javax.xml.xpath.XPathFactory
import javax.xml.xpath.XPathConstants
import org.w3c.dom.NodeList
import javax.xml.transform.TransformerFactory
import java.io.StringWriter
import javax.xml.transform.stream.StreamResult
import javax.xml.transform.dom.DOMSource
import javax.xml.transform.OutputKeys

class MessageUtility(payload: AnyRef) {

    def split(expression: String, callback: AnyRef => Unit) : Unit = {
        if (payload.isInstanceOf[String]) {
            val documentBuilder = DocumentBuilderFactory.newInstance().newDocumentBuilder()
            val inputSource = new InputSource(new StringReader(payload.toString()))
            val document = documentBuilder.parse(inputSource)
            val xPath = XPathFactory.newInstance().newXPath()
            val nodeList = xPath.evaluate(expression, document, XPathConstants.NODESET).asInstanceOf[NodeList]
            for (i <- 0 until nodeList.getLength()) {
                val factory = TransformerFactory.newInstance();
                val transformer = factory.newTransformer();
                transformer.setOutputProperty(OutputKeys.OMIT_XML_DECLARATION, "yes")
                val writer = new StringWriter();
                val result = new StreamResult(writer);
                val source = new DOMSource(nodeList.item(i));
                transformer.transform(source, result);
                writer.close();
                val xml = writer.toString();
                callback(xml)
            }
        }
    }

}