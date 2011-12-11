package org.scalaconduit.spi

class Endpoint(original: String) {
    
    val address : String = original
    
    def <<< (callback: (AnyRef) => AnyRef) = {
        val messageHandler = EndpointRegistry.getMessageHandler(address)
        messageHandler.receive(this, callback)
    }

    def >>> (message: AnyRef) : AnyRef = {
        val messageHandler = EndpointRegistry.getMessageHandler(address)
        messageHandler.dispatch(this, message)
    }

}