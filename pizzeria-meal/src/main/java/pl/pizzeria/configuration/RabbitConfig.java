package pl.pizzeria.configuration;

import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitConfig {
    public static final String GRAPH_DELETE_QUEUE = "fortius-graph-delete";

    @Bean
    Queue queue() {
        return new Queue(GRAPH_DELETE_QUEUE, false);
    }
}
