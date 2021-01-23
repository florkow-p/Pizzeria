package pl.pizzeria.configuration;

import org.springframework.amqp.core.Queue;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitConfig {

    @Value("${rabbit.save-order-queue}")
    private String saveOrderQueue;

    @Value("${rabbit.send-mail-queue}")
    private String sendMailQueue;

    @Bean
    public Queue saveOrderQueue() {
        return new Queue(saveOrderQueue);
    }

    @Bean
    public Queue sendMailQueue() {
        return new Queue(sendMailQueue);
    }
}
