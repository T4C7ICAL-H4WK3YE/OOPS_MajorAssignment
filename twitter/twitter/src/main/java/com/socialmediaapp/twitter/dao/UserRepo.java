package com.socialmediaapp.twitter.dao;

import com.socialmediaapp.twitter.user.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepo extends JpaRepository<User, Integer> {
    boolean existsByEmail(String email);
    User findByEmail(String email);

    Optional<User>findById(Integer userID);
}
