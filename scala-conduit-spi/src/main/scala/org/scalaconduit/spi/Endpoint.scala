package org.scalaconduit.spi

class Endpoint(original: String) {
    
    val address : String = original
    
    def receive (callback: (Object) => Object) = {
        val messageReceptor = ConduitServer.getMessageReceptor(address)
        messageReceptor.receive(this, callback)
    }

    def send (payload: Object) : Object = {
        payload
    }

}