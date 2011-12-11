package org.scalaconduit.jetty

import org.scalaconduit.spi.WebServer
import org.eclipse.jetty.server.Server
import scala.concurrent.ops._

object JettyWebServer extends WebServer {
    
    var server : Server = null
    var port : Int = 8085
    
    def setPort(newPort : Int) {
        port = newPort
    }
    
    override def start {
        spawn {
            server = new Server(port)
	        server.start()
	        server.join()
        }
    }
    
    override def stop {
        server.stop()
    }

}