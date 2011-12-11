package org.scalaconduit.http

import org.scalaconduit.spi._
import org.scalaconduit.jetty.JettyWebServer

class HttpMessageHandler extends MessageHandler {

    override def receive(uri : Endpoint, callback: (AnyRef) => AnyRef)  = {
        
        JettyWebServer.register(uri.path, callback)
        val response = callback("<stockRequest/>")
        println(response)
    }
    
    override def dispatch(endpoint : Endpoint, payload: AnyRef) : AnyRef = {
        payload
    }

}