package org.scalaconduit.spi
import java.net.URL
import org.scalaconduit.jms.JmsMessageHandler
import org.scalaconduit.jetty.JettyWebServer

/**
 * The main entry point to the server.
 */
class ConduitServer {
    
    val messageHandlers = Map("jms" -> new JmsMessageHandler())
    var webServer : WebServer = null
    
    def addWebServer(port : Int) = {
        webServer = new JettyWebServer(port)
        this
    }
    
    def addProtocol(protocol : String) = {
        EndpointRegistry.registerMessageHandler(protocol, messageHandlers(protocol))
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