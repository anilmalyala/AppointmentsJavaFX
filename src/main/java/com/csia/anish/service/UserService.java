package com.csia.anish.service;

import com.csia.anish.data.User;
import com.csia.anish.repo.UsersRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {

    @Autowired
    UsersRepo usersRepo;

    public Optional<User> validateLogin(String userId, String password) {
        Optional<User> user= usersRepo.findById(userId);
        if(user.isEmpty())
            return null;
        if ((user.get()).getPassword ().equals(password))
            return user;

        return null;
    }

    public User createUser(User user) {
        return usersRepo.save(user);
    }
}
