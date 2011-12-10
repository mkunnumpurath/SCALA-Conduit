package org.scalaconduit.server

import org.scalaconduit.spi.Endpoint
import org.scalaconduit.spi.IntegrationScript

class TestScript extends IntegrationScript {

    "jms:stockTicker" << { payload => 
        "jms:engineQueue" >> payload 
    }

}