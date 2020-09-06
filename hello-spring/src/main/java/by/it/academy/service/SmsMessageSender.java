package by.it.academy.service;

import by.it.academy.pojo.Recipient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
public class SmsMessageSender implements MessageSender {

    private static final Logger log = LoggerFactory.getLogger(SmsMessageSender.class);

    @Override
    public void send(Recipient recipient, Message message) {
        log.info("Send sms");
    }
}
