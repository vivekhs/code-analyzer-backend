/**
 * 
 */
package com.hackathon.services.authentication;

import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.util.List;

import com.auth0.jwt.exceptions.JWTCreationException;
import com.hackathon.models.request.authentication.SignUpRequest;
import com.hackathon.models.user.User;

/**
 * @author vivekhs
 *
 */
public interface AuthService {

    List<User> findUser(String userId);

    SignUpRequest formatSignUpRequest(SignUpRequest signUpRequest)
            throws NoSuchAlgorithmException, InvalidKeySpecException;

    public String generateToken() throws JWTCreationException;

    User addUser(SignUpRequest signUpRequest);

    public Boolean verifyToken(String token);

    boolean verifyPassword(String password, String passwordHash)
            throws NoSuchAlgorithmException, InvalidKeySpecException;
}
