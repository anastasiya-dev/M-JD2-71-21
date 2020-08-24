package by.it.academy;

import by.it.academy.pojo.Recipient;
import by.it.academy.repository.UserDao;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.Arrays;

public class Main {

    static AnnotationConfigApplicationContext context;

    public static void main(String[] args) throws InterruptedException {
        context
                = new AnnotationConfigApplicationContext(ApplicationConfiguration.class);
        createUser("user1");
        createUser("user2");

        final NotificationCommand notificationCommand = createCommand("user1");
        final NotificationCommand notificationCommand2 = createCommand("user2");

        final NotificationCommandExecutor executor =
                (NotificationCommandExecutor) context.getBean("notificationCommandExecutor");
        System.out.println("Executor count: " + executor.getCount());

        executor.execute(notificationCommand);
        executor.execute(notificationCommand2);

        System.out.println(Arrays.toString(context.getBeanDefinitionNames()));
        context.close();

        Thread.sleep(3000);
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
