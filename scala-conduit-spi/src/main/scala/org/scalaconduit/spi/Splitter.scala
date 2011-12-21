package org.scalaconduit.spi
import org.w3c.dom.Document
import org.w3c.dom.NodeList
import javax.xml.xpath.XPathConstants
import javax.xml.xpath.XPathFactory
import org.w3c.dom.Node

case class Splitter(payload: AnyRef) {

    def --<(expression: String): List[AnyRef] = {

        if (payload.isInstanceOf[Document]) {
            val xpath = XPathFactory.newInstance().newXPath()
            val nodeList = xpath.evaluate(expression, payload.asInstanceOf[Document], XPathConstants.NODESET).asInstanceOf[NodeList]
            def convert(nodeList: NodeList, list: List[Node]) : List[Node] = {
                if (nodeList.getLength() == list.size) list
                else convert(nodeList, nodeList.item(list.size) :: list)
            }
            convert(nodeList, Nil)
        } else {
        	Nil
        }

    }

}