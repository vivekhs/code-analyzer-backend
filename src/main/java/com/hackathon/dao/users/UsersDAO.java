/**
 * 
 */
package com.hackathon.dao.users;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.hackathon.models.user.User;

/**
 * @author vivekhs
 *
 */
public interface UsersDAO extends MongoRepository<User, String> {

    List<User> findByUserId(String userId);

    User save(User user);

}
