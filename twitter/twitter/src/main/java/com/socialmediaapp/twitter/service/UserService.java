package com.socialmediaapp.twitter.service;

import com.socialmediaapp.twitter.dao.UserRepo;
import com.socialmediaapp.twitter.user.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService
{
    @Autowired
    private UserRepo userRepo;

    public boolean userExists(User user) {
        return userRepo.existsByEmail(user.getEmail());
    }
    public boolean userpwd(User user) {
        if(userRepo.existsByEmail(user.getEmail()))
        {
            User returninguser = userRepo.findByEmail(user.getEmail());
            if(returninguser.getPassword().equals(user.getPassword()))
            {
                return true;
            }
            return false;
        }
        return false;
    }

    public boolean optionaluserexists(Optional<User> optionalUser)
    {
        return userRepo.existsByEmail(optionalUser.get().getEmail());
    }
}
