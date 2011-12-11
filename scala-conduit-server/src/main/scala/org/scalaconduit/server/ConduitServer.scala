package org.scalaconduit.spi
import java.net.URL
import org.scalaconduit.jms.JmsMessageReceptor
import org.scalaconduit.jetty.JettyWebServer

/**
 * The main entry point to the server.
 */
class ConduitServer {
    
    val messageReceptors = Map("jms" -> new JmsMessageReceptor())
    var webServer : WebServer = null
    
    def addWebServer(port : Int) = {
        webServer = new JettyWebServer(port)
        this
    }
    
    def addBinding(protocol : String) = {
        EndpointRegistry.register(protocol, messageReceptors(protocol))
        this
    }
    
    /**
     * Starts the server.
     */
    def start() = {
        webServer.start
        this
    }
    
    /**
     * Stops the server.
     */
    def stop() = {
        webServer.stop
    }

}