package org.example.sca.dsl.core

private [core] class ServiceClass[T](worker: Class[T]) extends Service[T] {
  def invoke(in: T, methodName: String): AnyRef = {
    val i = worker.newInstance()
    worker.getMethod(methodName, in.getClass).invoke(i, in.asInstanceOf[AnyRef])
  }
}