package by.it.academy.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

@Component
public class MessageGenerator implements ApplicationContextAware, DisposableBean {

    private static final Logger log = LoggerFactory.getLogger(MessageGenerator.class);

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
        log.info("Call destroy in MessageGenerator");
    }
}
