package org.scalaconduit.jms

import org.scalaconduit.spi._

class JmsMessageHandler extends MessageHandler {
    
    override def receive(uri : Endpoint, callback: (AnyRef) => AnyRef)  = {
        val response = callback("<stockRequest/>")
        println(response)
    }
    
    override def dispatch(endpoint : Endpoint, payload: AnyRef) : AnyRef = {
        payload
    }

}