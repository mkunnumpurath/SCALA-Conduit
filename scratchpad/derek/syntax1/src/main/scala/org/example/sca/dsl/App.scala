package org.example.sca.dsl

import core.ScaSupport
import core.Guarantees._

object App extends scala.App with ScaSupport {

  val sq: StockQuote = new StockQuote

  services("Trading Support") {

    register("webservice:stockService:clientStockTicker" >> (sq.clientPriceWorker _, Confidentiality, Integrity))
    register("webservice:stockService:stockTicker" >> sq.openPriceWorker _)
    register("webservice:fxQuoteService" >> classOf[FxQuote])

  }

  println("\nSomewhere in the runtime when the promoted service is accessed..\n")

  val endpointInputArg = new StockSymbol("IBM")
  var invokedEndpointMethod = "ignored" //because a worker function was registered - but keeps interface consistent
  println(exec("webservice:stockService:clientStockTicker", endpointInputArg, invokedEndpointMethod))
  println(exec("webservice:stockService:stockTicker", endpointInputArg, invokedEndpointMethod))

  println("")
  val arg = new CurrencySymbol("USD")
  invokedEndpointMethod = "buy"
  println(exec("webservice:fxQuoteService", arg, invokedEndpointMethod))

  invokedEndpointMethod = "sell"
  println(exec("webservice:fxQuoteService", arg, invokedEndpointMethod))

  println("")

}

class StockSymbol(val value: String)
class CurrencySymbol(val value: String)

class StockQuote {

  def clientPriceWorker(symbol: StockSymbol): String = {
    "You can can buy %s at 10 because you're our client" format symbol.value
  }

  def openPriceWorker(symbol: StockSymbol): String = {
    "You can can buy %s at 20" format symbol.value
  }

}

class FxQuote {

  def buy(symbol: CurrencySymbol): String = {
    "You can can buy %s FX at 10" format symbol.value
  }

  def sell(symbol: CurrencySymbol): String = {
    "We will buy %s FX at 8.75" format symbol.value
  }

}

