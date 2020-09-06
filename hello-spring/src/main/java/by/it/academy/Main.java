package by.it.academy;

import by.it.academy.pojo.Recipient;
import by.it.academy.repository.UserDao;
import by.it.academy.service.Channel;
import by.it.academy.service.MessageType;
import by.it.academy.service.NotificationCommand;
import by.it.academy.service.NotificationCommandExecutor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.mock.web.MockServletContext;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;

import java.util.Arrays;

public class Main {

    private static final Logger log = LoggerFactory.getLogger(Main.class);

    static AnnotationConfigWebApplicationContext context;

    public static void main(String[] args) throws InterruptedException {
        context
                = new AnnotationConfigWebApplicationContext();
        context.register(ApplicationConfiguration.class);
        context.setServletContext(new MockServletContext());
        context.refresh();
        createUser("user1");
        createUser("user2");

        final NotificationCommand notificationCommand = createCommand("user1");
        final NotificationCommand notificationCommand2 = createCommand("user2");

        final NotificationCommandExecutor executor =
                (NotificationCommandExecutor) context.getBean("notificationCommandExecutor");

        executor.execute(notificationCommand);
        executor.execute(notificationCommand2);

        log.info(Arrays.toString(context.getBeanDefinitionNames()));
        //context.close();
        //Thread.sleep(3000);
    }

    private static void createUser(String userId) {
        UserDao userRepository = (UserDao) context.getBean("userRepository");
        Recipient recipient = new Recipient(null, userId, userId + "@mail.ru", null);
        userRepository.create(recipient);
    }

    private static NotificationCommand createCommand(String userId) {
        NotificationCommand notificationCommand =
                (NotificationCommand)context.getBean("notificationCommand");
        notificationCommand.setChannel(Channel.EMAIL);
        notificationCommand.setMessageType(MessageType.ORDER_DELIVERED);
        notificationCommand.setUserId(userId);
        return notificationCommand;
    }
}
