package by.it.academy;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

@Configuration
@ComponentScan("by.it.academy")
public class ApplicationConfiguration implements BeanPostProcessor {

    @Bean
    @Scope(BeanDefinition.SCOPE_PROTOTYPE)
    public NotificationCommand notificationCommand() {
        return new NotificationCommand();
    }

    @Bean
    @Scope(BeanDefinition.SCOPE_PROTOTYPE)
    public Recipient recipient(String emailAddress, String mobilePhone) {
        return new Recipient(emailAddress, mobilePhone);
    }

    @Bean
    @Scope(BeanDefinition.SCOPE_PROTOTYPE)
    public Message message(String subject, String content) {
        return new Message(subject, content);
    }

    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        System.out.println("Before name: " + beanName + " bean: " + bean);
        return bean;
    }

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        System.out.println("After name: " + beanName + " bean: " + bean);
        return bean;
    }
}
