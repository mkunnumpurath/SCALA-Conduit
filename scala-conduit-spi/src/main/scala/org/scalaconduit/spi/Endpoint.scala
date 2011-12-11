package org.scalaconduit.spi

class Endpoint(original: String) {
    
    val address : String = original
    
    def <<< (callback: (AnyRef) => AnyRef) = {
        val messageReceptor = EndpointRegistry.getMessageReceptor(address)
        messageReceptor.receive(this, callback)
    }

    def >>> (message: AnyRef) : AnyRef = {
        println(message.getClass())
        message
    }

}