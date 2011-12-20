package org.scalaconduit.spi
import org.w3c.dom.Document
import org.w3c.dom.NodeList

import javax.xml.xpath.XPathConstants
import javax.xml.xpath.XPathFactory

case class Splitter(payload: AnyRef) {

    def --< (expression: String): List[AnyRef] = {

        var list: List[AnyRef] = List()
        if (payload.isInstanceOf[Document]) {
            val xpath = XPathFactory.newInstance().newXPath()
            val nodeList = xpath.evaluate(expression, payload.asInstanceOf[Document], XPathConstants.NODESET).asInstanceOf[NodeList]
            for (i <- 0 until nodeList.getLength()) {
                list = nodeList.item(i) :: list
            }
        }
        list

    }

}