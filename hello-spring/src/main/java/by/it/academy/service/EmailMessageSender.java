package by.it.academy.service;

import by.it.academy.pojo.Recipient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
public class EmailMessageSender implements MessageSender {

    private static final Logger log = LoggerFactory.getLogger(EmailMessageSender.class);

    @Override
    public void send(Recipient recipient, Message message) {
        if (recipient == null || recipient.getEmailAddress() == null ||
                "".equals(recipient.getEmailAddress())) {
            throw new IllegalArgumentException("Email address cannot be empty");
        }
        log.info("Send by email to:" + recipient);
        log.info("Message content: " + message);
    }
}
