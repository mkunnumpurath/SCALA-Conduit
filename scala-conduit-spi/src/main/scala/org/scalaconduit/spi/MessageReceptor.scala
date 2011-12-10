package org.scalaconduit.spi

trait MessageReceptor {
    
    def receive(endpoint : Endpoint, callback: (AnyRef) => AnyRef)

}