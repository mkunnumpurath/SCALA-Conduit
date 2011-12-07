package org.scalaconduit.server
import org.scalaconduit.spi.ConduitServer._
import org.scalaconduit.jms.JmsMessageReceptor

/**
 * @author ${user.name}
 */
object App {
  
    def main(args: Array[String]) = {
        
        start()
        register("jms", new JmsMessageReceptor())
        new Runner()
        stop()
        
    }

}
