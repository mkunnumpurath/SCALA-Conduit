package org.scalaconduit.server

import org.scalaconduit.spi.IntegrationScript
import javax.xml.bind.annotation.XmlRootElement
import java.net.URI
import javax.xml.bind.annotation.XmlElement
import java.util.List
import java.util.LinkedList
import org.w3c.dom.Document

/**
 * @author ${user.name}
 */
object App {
  
    def main(args: Array[String]) : Unit = {
        val server = new ConduitServer().addWebServer(8080).addProtocol("http").addProtocol("jms").start()
        try {
            new TestScript()
        } finally {
            server.stop()
        }
    }

}

class TestScript extends IntegrationScript {
    
    val employees = <employees><employee>Meeraj</employee><employee>Derek</employee></employees>
    "http://localhost:8080/stockTicker" <<< { message =>
        for (part <- message ~~~ classOf[Document] --< "//employee") {
            "jms://testQueue" >>> part ~~~ classOf[String]
        }
        null
    }
    "http://localhost:8080/stockTicker" >>>  employees.toString
}