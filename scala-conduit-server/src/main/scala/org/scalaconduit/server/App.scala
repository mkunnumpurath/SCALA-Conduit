package org.scalaconduit.server
import org.scalaconduit.spi.ConduitServer
import org.scalaconduit.spi.MessageUtility
import org.scalaconduit.spi.IntegrationScript
import javax.xml.bind.annotation.XmlRootElement
import java.net.URI
import javax.xml.bind.annotation.XmlElement
import java.util.List
import java.util.LinkedList

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
        payload.split("//symbol/text()") { x => 
            println(x)
        }
        new StockResponse() as classOf[String]
    }
    val stockRequest = new StockRequest()
    stockRequest.list.add("SUN")
    stockRequest.list.add("ORCL")
    val inputXml = stockRequest as classOf[String]
    val response = "http://localhost:8080/stockTicker" >>>  inputXml
    val stockResponse = response as classOf[StockResponse]
    println("Stock response received: " + stockResponse)
}

@XmlRootElement
class StockRequest {
    @XmlElement(name = "symbol")
    var list : List[String] = new LinkedList[String]
}

@XmlRootElement
class StockResponse {
}
