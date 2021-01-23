package pl.pizzeria.mail.web;


import pl.pizzeria.order.domain.OrderInfo;

public interface MailService {
    void send(OrderInfo orderInfo);
}
