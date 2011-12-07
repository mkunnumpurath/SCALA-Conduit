package org.scalaconduit.jms

import org.scalaconduit.spi._

class JmsMessageReceptor extends MessageReceptor {
    
    override def receive(uri : Uri, callback: (Object) => Object)  = {
        callback("Hello")
    }

}