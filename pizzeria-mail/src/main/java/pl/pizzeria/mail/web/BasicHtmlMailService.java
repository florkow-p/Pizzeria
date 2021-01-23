package pl.pizzeria.mail.web;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import pl.pizzeria.mail.configuration.MailProperties;
import pl.pizzeria.order.domain.OrderInfo;

import javax.mail.internet.MimeMessage;

@Slf4j
@RequiredArgsConstructor
@Service
public class BasicHtmlMailService implements MailService {

    private final MailProperties mailProperties;
    private final JavaMailSender mailSender;
    private final MailContentBuilder contentBuilder;

    @Override
    public void send(OrderInfo orderInfo) {
        MimeMessage message = mailSender.createMimeMessage();

        try {
        MimeMessageHelper helper = new MimeMessageHelper(message, true);

        helper.setFrom(mailProperties.getFrom());
        helper.setTo(mailProperties.getTo());
        helper.setSubject("subject");
        helper.setText(contentBuilder.build(orderInfo), true);

        mailSender.send(message);
        } catch(Exception e) {
            log.error(e.getMessage());
        }
    }
}
