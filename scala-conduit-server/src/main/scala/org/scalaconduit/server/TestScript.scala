package org.scalaconduit.server

import org.scalaconduit.spi.Uri
import org.scalaconduit.spi.IntegrationScript

class TestScript extends IntegrationScript {

    "jms:stockTicker" << { "jms:engineQueue" >> _ }

}