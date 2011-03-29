package concurrency.usage01;

import org.mortbay.jetty.Request;
import org.mortbay.jetty.Server;
import org.mortbay.jetty.handler.AbstractHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * User: john
 * Date: 2010-8-8
 * Time: 19:26:53
 */
public class SimpleServer {
    public static void main(String[] args) throws Exception {
        Server server = new Server(9000);
        server.addHandler(new AbstractHandler(){
            public void handle(
                    String target, HttpServletRequest request, final HttpServletResponse response, int dispatch)
                    throws IOException, ServletException {
                ((Request)request).setHandled(true);
                response.getOutputStream().println("hello");
                
                new Thread(){
                    @Override
                    public void run() {
                        try {
                            try {
                                Thread.sleep(1000);
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                            response.getOutputStream().println("world");
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                }.start();
            }
        });
        server.start();
    }
}
