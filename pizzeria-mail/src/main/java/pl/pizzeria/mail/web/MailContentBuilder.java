package pl.pizzeria.mail.web;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;
import pl.pizzeria.order.domain.OrderInfo;

@RequiredArgsConstructor
@Service
public class MailContentBuilder {

    private final TemplateEngine templateEngine;

    public String build(OrderInfo orderInfo) {
        Context context = new Context();

        context.setVariable("orderId", orderInfo.getOrderId());
        context.setVariable("comment", orderInfo.getComment());
        context.setVariable("price", orderInfo.getPrice() + " PLN");
        context.setVariable("name", orderInfo.getName());

        return templateEngine.process("email-template", context);
    }

}
