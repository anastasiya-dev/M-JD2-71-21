package by.it.academy;

import lombok.Data;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.*;

@Data
public class NotificationCommand implements BeanNameAware,
        BeanFactoryAware,
        InitializingBean,
        DisposableBean
{

    private String userId;
    private MessageType messageType;
    private Channel channel;
    private String beanName;

    @Override
    public void setBeanName(String s) {
        beanName = s;
        System.out.println("My name is " + s);
    }

    @Override
    public void setBeanFactory(BeanFactory beanFactory) throws BeansException {
        System.out.println("BeanFactory: " + beanFactory);
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        System.out.println("Call: afterPropertiesSet()");
    }

    @Override
    public void destroy() throws Exception {
        System.out.println("Bean " + beanName + " has been destroyed");
    }
}
