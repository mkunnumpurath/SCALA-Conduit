package org.scalaconduit.spi

class IntegrationScript {

    implicit def endpoint(original: String) = new Endpoint(original)
    implicit def transformer(payload: AnyRef) = new Transformer(payload)
    implicit def messageUtility(payload: AnyRef) = new MessageUtility(payload)

}