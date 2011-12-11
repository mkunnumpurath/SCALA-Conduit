package org.scalaconduit.jetty

import org.scalaconduit.spi.WebServer
import org.eclipse.jetty.server.Server
import scala.concurrent.ops._

class JettyWebServer extends WebServer {
    
    val server = new Server(8080);
    
    override def start {
        spawn {
	        server.start()
	        server.join()
        }
    }
    
    override def stop {
        server.stop()
    }

}