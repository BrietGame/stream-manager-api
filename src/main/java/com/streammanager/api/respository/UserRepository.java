package com.streammanager.api.respository;

import com.streammanager.api.model.User;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends MongoRepository<User, String> {

    User findByUsername(String username);

    User findByEmailOrUsername(String email, String username);
    List<User> findAllByEmail(String email);
    
    Boolean existsByUsername(String username);

    Boolean existsByEmail(String email);
}
