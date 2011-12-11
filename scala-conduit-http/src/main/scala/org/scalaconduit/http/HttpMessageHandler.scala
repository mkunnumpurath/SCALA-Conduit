package org.scalaconduit.http

import org.scalaconduit.spi._
import org.scalaconduit.jetty.JettyWebServer
import org.apache.http.impl.client.DefaultHttpClient
import org.apache.http.client.methods.HttpPost
import org.apache.http.impl.client.BasicResponseHandler
import org.apache.http.entity.StringEntity
import org.apache.http.protocol.HTTP

class HttpMessageHandler extends MessageHandler {

    override def receive(uri : Endpoint, callback: (AnyRef) => AnyRef)  = {
        JettyWebServer.register(uri.path, callback)
    }
    
    override def dispatch(endpoint : Endpoint, payload: AnyRef) : AnyRef = {
        val httpClient = new DefaultHttpClient()
        val httpPost = new HttpPost(endpoint.address)
        val entity = new StringEntity(payload.toString(), HTTP.UTF_8);
        httpPost.setEntity(entity)
        val responseHandler = new BasicResponseHandler()
        httpClient.execute(httpPost, responseHandler)
    }

}