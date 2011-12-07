package org.scalaconduit.server

import org.scalaconduit.spi.Uri

class Runner {

    implicit def uri(x: String) = new Uri(x)

    "jms:stockTicker" << { "jms:engineQueue" >> _ }

}