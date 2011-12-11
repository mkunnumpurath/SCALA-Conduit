package org.scalaconduit.spi

import scala.collection.mutable

object EndpointRegistry {
    
    val messageReceptors = mutable.Map.empty[String, MessageHandler]
    
    def registerMessageHandler(protocol : String, messageHandler : MessageHandler) {
        messageReceptors += protocol -> messageHandler
    }
    
    def getMessageHandler(address : String) : MessageHandler = {
        messageReceptors(address.split(":")(0))
    }

}