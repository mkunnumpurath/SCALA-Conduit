package org.scalaconduit.spi

class IntegrationScript {

    implicit def uri(x: String) = new Uri(x)

}