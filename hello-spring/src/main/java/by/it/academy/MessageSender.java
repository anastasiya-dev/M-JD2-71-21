package by.it.academy;

import by.it.academy.pojo.Recipient;

public interface MessageSender {

    void send(Recipient recipient, Message message);
}
