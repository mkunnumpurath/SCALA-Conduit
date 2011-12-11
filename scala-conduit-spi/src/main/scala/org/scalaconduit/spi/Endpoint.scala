package org.scalaconduit.spi

class Endpoint(original: String) {
    
    val address : String = original
    val protocol = address.split(":")(0)
    val path = address.split(":")(1)
    
    def <<< (callback: (AnyRef) => AnyRef) = {
        val messageHandler = EndpointRegistry.getMessageHandler(protocol)
        messageHandler.receive(this, callback)
    }

    def >>> (message: AnyRef) : AnyRef = {
        val messageHandler = EndpointRegistry.getMessageHandler(protocol)
        messageHandler.dispatch(this, message)
    }

}