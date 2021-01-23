package pl.pizzeria.mail.web;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;
import pl.pizzeria.order.domain.OrderInfo;

@Slf4j
@RequiredArgsConstructor
@Service
public class MailListener {

    private final BasicHtmlMailService service;

    @RabbitListener(queues = "${rabbit.send-mail-queue}")
    public void orderListener(OrderInfo orderInfo) {
        try {
            service.send(orderInfo);
        } catch (Exception e) {
            log.warn("Better exception handling system/logger required here!");
            log.error(e.getMessage());
        }
    }
}
