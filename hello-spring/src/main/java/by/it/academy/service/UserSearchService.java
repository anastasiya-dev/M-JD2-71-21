package by.it.academy.service;

import by.it.academy.pojo.Recipient;
import by.it.academy.repository.UserDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserSearchService {

    @Autowired
    UserDao userDao;

    public List<String> search(String searchStr) {
        return (List<String>)userDao.findAll(searchStr)
                .stream()
                .map(o -> ((Recipient) o).getMobilePhone() + " " + ((Recipient) o).getEmailAddress())
                .collect(Collectors.toList());
    }

}
