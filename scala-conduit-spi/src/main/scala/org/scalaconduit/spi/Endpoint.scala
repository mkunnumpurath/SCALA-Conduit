package org.scalaconduit.spi
import java.net.URI

case class Endpoint(url: String) {

    val address: URI = new URI(url)
    val protocol = address.getScheme()
    var path = address.getPath()

    def <<<(callback: (AnyRef) => AnyRef) = {
        val messageHandler = EndpointRegistry.getMessageHandler(protocol)
        messageHandler.receive(this, callback)
    }

    def >>>(message: AnyRef): AnyRef = {
        val messageHandler = EndpointRegistry.getMessageHandler(protocol)
        messageHandler.dispatch(this, message)
    }

}