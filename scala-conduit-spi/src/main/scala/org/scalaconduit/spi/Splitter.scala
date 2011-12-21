package org.scalaconduit.spi
import org.w3c.dom.Document
import org.w3c.dom.NodeList
import javax.xml.xpath.XPathConstants
import javax.xml.xpath.XPathFactory
import org.w3c.dom.Node

case class Splitter(payload: AnyRef) {

    def --<(expression: String): List[AnyRef] = {
        payload match {
            case document: Document =>
                val xpath = XPathFactory.newInstance().newXPath()
                val nodeList = xpath.evaluate(expression, document, XPathConstants.NODESET).asInstanceOf[NodeList]
                def convert(nodeList: NodeList, list: List[Node]): List[Node] = {
                    if (nodeList.getLength() == list.size) list
                    else convert(nodeList, nodeList.item(list.size) :: list)
                }
                convert(nodeList, Nil)
            case _ =>
                Nil
        }

    }

}