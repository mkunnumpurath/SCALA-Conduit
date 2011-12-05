package org.scalaconduit.spi

trait MessageReceptor {
    
    def receive(uri : Uri)

}