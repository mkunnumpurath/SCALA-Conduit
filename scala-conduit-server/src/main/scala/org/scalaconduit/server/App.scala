package org.scalaconduit.server

import org.scalaconduit.spi.IntegrationScript
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
    
    def cast[T] (source : AnyRef) = source.asInstanceOf[T]

}

class TestScript extends IntegrationScript {
    
    "http://localhost:8080/employee" <<< { message =>
        for (part <- message ~~~ classOf[Document] --< "//name/text()") {
            "jms://testQueue" >>> part ~~~ classOf[String]
        }
        null
    }
    "http://localhost:8080/employee" >>> <employees><name>Meeraj</name><name>Derek</name></employees>
}