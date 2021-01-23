package pl.pizzeria.order.web;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import pl.pizzeria.order.domain.Order;

@Slf4j
@RequiredArgsConstructor
@Service
public class OrderListener {

    private final OrderServiceImpl orderService;
    private final RabbitTemplate rabbitTemplate;
    private final ObjectMapper objectMapper;

    @Value("${rabbit.send-mail-queue}")
    private String sendMailQueue;

    @RabbitListener(queues = "${rabbit.save-order-queue}")
    public void orderListener(Order order) {
        try {
            orderService.save(order.convertToEntity());
        } catch (Exception e) {
            log.warn("Better exception handling system/logger required here!");
            log.error(e.getMessage());
        }

        rabbitTemplate.convertAndSend(sendMailQueue, order);
    }
}
