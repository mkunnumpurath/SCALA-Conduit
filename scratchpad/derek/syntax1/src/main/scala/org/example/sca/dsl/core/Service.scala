package org.example.sca.dsl.core

trait Service[T] {

  def invoke(in: T, methodName: String): AnyRef

}