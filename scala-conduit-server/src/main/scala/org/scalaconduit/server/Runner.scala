package org.scalaconduit.server

import org.scalaconduit.spi.Uri

class Runner {

    implicit def uri(x: String) = new Uri(x)

    def run() = "jms:stockTicker" << { request => "jms:engine-queue" >> request }

}