package org.scalaconduit.jetty

import org.scalaconduit.spi.WebServer
import org.eclipse.jetty.server.Server
import org.eclipse.jetty.server.Request
import org.eclipse.jetty.server.handler.AbstractHandler
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import scala.concurrent.ops._
import scala.collection.mutable
import org.scalaconduit.spi.Endpoint

object JettyWebServer extends WebServer {

    var server: Server = null
    var port: Int = 8085
    var messageReceiver: MessageReceiver = null
    val callbacks = mutable.Map.empty[String, (AnyRef) => AnyRef]

    def setPort(newPort: Int) {
        port = newPort
    }

    override def start {
        spawn {
            server = new Server(port)
            messageReceiver = new MessageReceiver()
            server.setHandler(messageReceiver)
            server.start()
            server.join()
        }
    }

    override def stop {
        if (server != null) {
	        server.stop()
	        server.destroy()
        }
    }

    def register(path: String, callback: (AnyRef) => AnyRef) = {
        callbacks += path -> callback
    }

    class MessageReceiver extends AbstractHandler {
        def handle(target: String, baseRequest: Request, request: HttpServletRequest, response: HttpServletResponse) : Unit = {
            response.setStatus(HttpServletResponse.SC_OK)
            baseRequest.setHandled(true)
            response.getWriter().println(callbacks(request.getRequestURI())(io.Source.fromInputStream(request.getInputStream()).getLines.mkString))
        }
    }

}