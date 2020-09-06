package by.it.academy.simple;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SimpleConfiguration {

    private static final Logger log = LoggerFactory.getLogger(SimpleConfiguration.class);

    @Bean
    public A a1() {
        return new A("A1");
    }

    @Bean
    public A a2() {
        return new A("A2");
    }

    @Bean
    public B b1(@Qualifier("a2") A a) {
        return new B(a);
    }

    public static void main(String[] args) {
        AnnotationConfigApplicationContext context =
                new AnnotationConfigApplicationContext(SimpleConfiguration.class);

        B b = (B) context.getBean("b1");
        log.info(b.getA().getA());
    }

}

class A {
    private String a;

    public A(String a) {
        this.a = a;
    }

    public String getA() {
        return a;
    }
}

class B {
    private A a;

    public B(A a) {
        this.a = a;
    }

    public A getA() {
        return a;
    }
}
