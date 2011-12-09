package org.example.sca.dsl.core

private [core] class Promotion(val uri: String) {

  private var svc: Service[_] = _

  def >> (worker: Service[_], guarantees: Guarantees.Value*): Promotion = {
    svc = worker
    this
  }

  //Called by the runtime when the endpoint is accessed
  def exec(in: AnyRef, methodName: String): AnyRef = {
    val i = svc.invoke _
    i.asInstanceOf[(AnyRef, String) => AnyRef](in, methodName)
  }

}
