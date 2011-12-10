package org.scalaconduit.jms

import org.scalaconduit.spi._

class JmsMessageReceptor extends MessageReceptor {
    
    override def receive(uri : Endpoint, callback: (Object) => Object)  = {
        val response = callback("Meeraj")
        println(response)
    }

}