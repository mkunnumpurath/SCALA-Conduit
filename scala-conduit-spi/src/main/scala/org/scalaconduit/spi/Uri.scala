package org.scalaconduit.spi

class Uri(original: String) {
    
    val address : String = original
    var callback : (Object) => Object = null
    
    def receive (callback: (Object) => Object) = {
        this.callback = callback
        val messageReceptor = ConduitServer.getMessageReceptor(address)
        messageReceptor.receive(this)
    }
    
    def onReceive(payload : AnyRef) {
        val response = callback(payload)
        println(response)
    }

    def send (payload: Object) : Object = {
        payload
    }

    def ensure (intent: String) : Uri = {
        this
    }

    def and (intent: String) : Uri = {
        this
    }

}