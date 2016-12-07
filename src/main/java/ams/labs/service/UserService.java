package ams.labs.service;


import ams.labs.entity.User;
import ams.labs.repository.UserRepository;
import com.google.common.collect.Lists;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    final Logger logger = LoggerFactory.getLogger(UserService.class);

    @Autowired
    private UserRepository repository;

    public void save(User user) {
        repository.save(user);
    }

    public void deleteAll() {
        repository.deleteAll();
    }

    public User findByUserId(Long id) {
        return repository.findByUserId(id);
    }

    public List<User> findAll() {
        Iterable<User> userIterable = repository.findAll();

        return Lists.newArrayList(userIterable);
    }

    public User fetchUser(Long userId) {
        User user = repository.findByUserId(userId);
        if (user == null) {
            user = new User();
            user.setUserId(userId);
            repository.save(user);
        }

        return user;
    }
}
