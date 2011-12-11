package org.scalaconduit.spi

class Transformer(payload: AnyRef) {
    
    def | [T] (targetType : Class[T]) : T = payload.toString().toInt.asInstanceOf[T]

}