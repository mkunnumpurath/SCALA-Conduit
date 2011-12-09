package org.example.sca.dsl.core

import collection.mutable.ListBuffer

trait ScaSupport {

  val registered: ListBuffer[Promotion] = new ListBuffer[Promotion]

  def services(name: String)(f: => Unit): Unit = f

  def register(p: Promotion) = {
    println("Registered: " + p.uri)
    registered += p
  }

  def exec(uri: String, arg: AnyRef, methodName: String): AnyRef = {
    val prom = registered find ((p: Promotion) => p.uri == uri)
    prom.get.exec (arg, methodName)
  }

  implicit def fun1(x: String) = new Promotion(x)
  implicit def fun2[T](f: T => AnyRef): Service[T] = new ServiceMethod(f)
  implicit def fun3[T](clz: Class[T]): Service[T] = new ServiceClass(clz)

}