package org.scalaconduit.spi

trait MessageReceptor {
    
    def receive(uri : Uri, callback: (Object) => Object)

}