package org.scalaconduit.spi

import scala.collection.mutable

object EndpointRegistry {
    
    val messageReceptors = mutable.Map.empty[String, MessageReceptor]
    
    def register(protocol : String, messageReceptor : MessageReceptor) {
        messageReceptors += protocol -> messageReceptor
    }
    
    def getMessageReceptor(address : String) : MessageReceptor = {
        val tokens = address.split(":")
        messageReceptors(tokens(0))
    }

}