package by.it.academy.service;

import by.it.academy.pojo.Recipient;
import by.it.academy.repository.GenericDao;
import lombok.Getter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
@Getter
public class NotificationCommandExecutor {

    private static final Logger log = LoggerFactory.getLogger(NotificationCommandExecutor.class);

    @Autowired
    @Value("#{emailMessageSender}")
    MessageSender messageSender;

    MessageSender smsMessageSender;

    @Autowired
    @Value("#{recipientRepository}")
    GenericDao<Recipient> recipientRepository;

    @Autowired
    MessageGenerator messageGenerator;


    public boolean execute(NotificationCommand command) {
        log.info("Command to execute:");
        log.info(command.toString());

        final Recipient recipient = (Recipient) recipientRepository.find(command.getUserId());
        final Message message = messageGenerator.generate(command.getMessageType());

        switch (command.getChannel()) {
            case SMS: {
                log.info("Not implemented");
                //smsMessageSender.send(...);
                break;
            }
            case EMAIL: {
                messageSender.send(recipient, message);
                break;
            }
            case VIBER: {
                log.info("Not implemented");
                break;
            }
            default: {
                log.info("No channel");
            }
        }


        log.info("Executed successfully");
        return true;
    }


}
