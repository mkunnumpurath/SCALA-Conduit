package org.example.sca.dsl.core

private [core] class ServiceMethod[T](worker: T => AnyRef) extends Service[T] {
  def invoke(in: T, methodName: String): AnyRef = worker(in)
}