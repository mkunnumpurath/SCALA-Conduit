package org.scalaconduit.spi

trait MessageHandler {
    
    def receive(endpoint : Endpoint, callback: (AnyRef) => AnyRef)
    
    def dispatch(endpoint : Endpoint, payload: AnyRef) : AnyRef

}