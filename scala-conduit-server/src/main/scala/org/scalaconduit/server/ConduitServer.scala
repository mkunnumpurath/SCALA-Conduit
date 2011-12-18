package org.scalaconduit.spi

import scala.collection.mutable
import java.net.URL
import org.scalaconduit.http.HttpMessageHandler
import org.scalaconduit.jetty.JettyWebServer

/**
 * The main entry point to the server.
 */
class ConduitServer {
    
    val messageHandlers = Map("http" -> new HttpMessageHandler())
    var webServer : Boolean = false
    
    def addWebServer(port : Int) = {
        webServer = true
        JettyWebServer.port = port
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
        JettyWebServer.start
        this
    }
    
    /**
     * Stops the server.
     */
    def stop() = {
        JettyWebServer.stop
    }

}