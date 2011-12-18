package org.scalaconduit.server
import org.scalaconduit.spi.ConduitServer
import org.scalaconduit.spi.IntegrationScript
import javax.xml.bind.annotation.XmlRootElement
import java.net.URI

/**
 * @author ${user.name}
 */
object App {
  
    def main(args: Array[String]) : Unit = {
        val server = new ConduitServer().addWebServer(8080).addProtocol("http").start()
        try {
            new TestScript()
        } finally {
            server.stop()
        }
        
    }

}

class TestScript extends IntegrationScript {
    "http://localhost:8080/stockTicker" <<< { payload => 
        val stockRequest = payload --- classOf[StockRequest]
        println("Stock request received for: " + stockRequest.symbol)
        new StockResponse() --- classOf[String]
    }
    val stockRequest = new StockRequest() --- classOf[String]
    val response = "http://localhost:8080/stockTicker" >>>  stockRequest
    val stockResponse = response --- classOf[StockResponse]
    println("Stock response received: " + stockResponse.quote)
}

@XmlRootElement
class StockRequest {
    val symbol = "ORCL"
}

@XmlRootElement
class StockResponse {
    val quote = 21.23
}
