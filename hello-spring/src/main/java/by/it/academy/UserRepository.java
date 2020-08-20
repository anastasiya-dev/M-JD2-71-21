package by.it.academy;

import org.springframework.stereotype.Repository;

@Repository
public class UserRepository {

    public Recipient find(String userId) {
        //POJO hibernate session load...
        Recipient recipient = new Recipient();
        recipient.setEmailAddress("user@mail.ru");
        recipient.setMobilePhone("+375291002030");
        return recipient;
    }

}
