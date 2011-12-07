package org.scalaconduit.spi

class Uri(original: String) {
    
    val address : String = original
    
    def << (callback: (Object) => Object) = {
        val messageReceptor = ConduitServer.getMessageReceptor(address)
        messageReceptor.receive(this, callback)
    }

    def >> (payload: Object) : Object = {
        payload
    }

}