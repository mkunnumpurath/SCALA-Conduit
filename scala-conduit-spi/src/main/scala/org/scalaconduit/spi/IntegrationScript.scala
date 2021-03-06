package org.scalaconduit.spi

class IntegrationScript {

    implicit def anyRefToConverter(payload: AnyRef) = Converter(payload)
    implicit def stringToSourceEndpoint(url: String) = Endpoint(url)
    implicit def anyRefToSplitter(payload: AnyRef) = Splitter(payload)

}