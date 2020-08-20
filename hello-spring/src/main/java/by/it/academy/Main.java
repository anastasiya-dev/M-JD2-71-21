package by.it.academy;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.Arrays;

public class Main {

    public static void main(String[] args) {
        final AnnotationConfigApplicationContext context
                = new AnnotationConfigApplicationContext(ApplicationConfiguration.class);

        final NotificationCommand notificationCommand =
                (NotificationCommand)context.getBean("notificationCommand");
        notificationCommand.setChannel(Channel.EMAIL);
        notificationCommand.setMessageType(MessageType.ORDER_DELIVERED);
        notificationCommand.setUserId("user");

        final NotificationCommand notificationCommand2 =
                (NotificationCommand)context.getBean("notificationCommand");
        notificationCommand2.setChannel(Channel.SMS);
        notificationCommand2.setMessageType(MessageType.NEW_ORDER);
        notificationCommand2.setUserId("user");

        final NotificationCommandExecutor executor =
                (NotificationCommandExecutor) context.getBean("notificationCommandExecutor");

        executor.execute(notificationCommand);
        executor.execute(notificationCommand2);

        System.out.println(Arrays.toString(context.getBeanDefinitionNames()));

    }
}
