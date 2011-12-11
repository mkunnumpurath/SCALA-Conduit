package org.scalaconduit.server
import org.scalaconduit.spi.ConduitServer._
import org.scalaconduit.jms.JmsMessageReceptor
import org.scalaconduit.spi.IntegrationScript
import javax.xml.bind.annotation.XmlRootElement

/**
 * @author ${user.name}
 */
object App {
  
    def main(args: Array[String]) = {
        
        start()
        register("jms", new JmsMessageReceptor())
        new TestScript()
        stop()
        
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
