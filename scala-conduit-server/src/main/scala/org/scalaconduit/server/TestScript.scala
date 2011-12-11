package org.scalaconduit.server

import org.scalaconduit.spi.Endpoint
import org.scalaconduit.spi.IntegrationScript
import javax.xml.bind.annotation.XmlRootElement

class TestScript extends IntegrationScript {

    "jms:stockTicker" << { payload => 
        "jms:engineQueue" >> payload | classOf[Employee] | classOf[String]
    }

}

@XmlRootElement
class Employee {
}