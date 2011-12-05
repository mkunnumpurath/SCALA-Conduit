package org.scalaconduit.spi
import java.net.URL
import scala.collection.mutable

/**
 * The main entry point to the server.
 */
object ConduitServer {
    
    val messageReceptors = mutable.Map.empty[String, MessageReceptor]
    
    /**
     * Starts the server.
     */
    def start() = {
    }
    
    /**
     * Stops the server.
     */
    def stop() = {
    }
    
    def register(protocol : String, messageReceptor : MessageReceptor) {
        messageReceptors += protocol -> messageReceptor
    }
    
    def getMessageReceptor(address : String) : MessageReceptor = {
        val tokens = address.split(":")
        messageReceptors(tokens(0))
    }

}