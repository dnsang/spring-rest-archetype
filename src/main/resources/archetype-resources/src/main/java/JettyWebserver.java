#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
package ${package};

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import org.eclipse.jetty.util.thread.QueuedThreadPool;
import org.eclipse.jetty.util.thread.ThreadPool;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.embedded.jetty.JettyServletWebServerFactory;
import org.springframework.boot.web.server.WebServerFactoryCustomizer;
import org.springframework.stereotype.Component;


@Component
public class JettyWebserver implements WebServerFactoryCustomizer<JettyServletWebServerFactory> {
    @Autowired
    Config config;
    @Override
    public void customize(JettyServletWebServerFactory factory) {
        int minThread = config.get("jetty.minThread",Runtime.getRuntime().availableProcessors(),Integer.class);
        int maxThread = config.get("jetty.maxThread",minThread*2,Integer.class);
        int idleTimeout = config.get("jetty.idleTimeout",5000,Integer.class);
        int maxQueueSize = config.get("jetty.maxQueueSize",maxThread*10000,Integer.class);
        BlockingQueue<Runnable> blockingQueue = new ArrayBlockingQueue<>(maxQueueSize);
        ThreadPool threadPool = new QueuedThreadPool(
                maxThread,
                minThread,
                idleTimeout,
                blockingQueue
        );
        factory.setThreadPool(threadPool);
        String serverInfo = """
                =================== Jetty Server ==========
                === min thread: %10d              ===
                === max thread: %10d              ===
                === idle timeout: %10d            ===
                === max queue size: %10d          ===
                ===========================================
                """;
        System.out.println(String.format(serverInfo, minThread,maxThread, idleTimeout, maxQueueSize ));
    }
}
