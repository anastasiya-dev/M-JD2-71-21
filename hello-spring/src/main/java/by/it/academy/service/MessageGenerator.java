package by.it.academy.service;

import by.it.academy.service.Message;
import by.it.academy.service.MessageType;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

@Component
public class MessageGenerator implements ApplicationContextAware, DisposableBean {

    private ApplicationContext context;

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        context = applicationContext;
    }

    public Message generate(MessageType messageType) {
        return (Message) context.getBean("message",
                "Some subject",
                "Some content"
                );
    }

    @Override
    public void destroy() throws Exception {
        System.out.println("Call destroy in MessageGenerator");
    }
}
