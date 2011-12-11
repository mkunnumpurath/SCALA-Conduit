package org.scalaconduit.jms

import org.scalaconduit.spi._

class JmsMessageReceptor extends MessageReceptor {
    
    override def receive(uri : Endpoint, callback: (AnyRef) => AnyRef)  = {
        val response = callback("<employee/>")
        println(response.getClass())
        println(response)
    }

}