package org.scalaconduit.server
import org.scalaconduit.spi.ConduitServer
import org.scalaconduit.jms.JmsMessageReceptor
import org.scalaconduit.spi.IntegrationScript
import javax.xml.bind.annotation.XmlRootElement
import org.scalaconduit.jetty.JettyWebServer

/**
 * @author ${user.name}
 */
object App {
  
    def main(args: Array[String]) : Unit = {
        val server = new ConduitServer().addWebServer(8080).addBinding("jms").start()
        new TestScript()
        server.stop()
    }

}

class TestScript extends IntegrationScript {

    "jms:stockTicker" <<< { payload => 
        val employee = payload --- classOf[StockRequest]
        val response = "jms:engineQueue" >>> employee
        response --- classOf[String]
    }

}

@XmlRootElement
class StockRequest {
}
