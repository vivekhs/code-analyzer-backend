/**
 * 
 */
package com.hackathon.services.authentication.impl;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.util.Arrays;
import java.util.Base64;
import java.util.List;

import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.hackathon.dao.users.UsersDAO;
import com.hackathon.models.analyzerproperties.AnalyzerProperties;
import com.hackathon.models.request.authentication.SignUpRequest;
import com.hackathon.models.user.User;
import com.hackathon.services.authentication.AuthService;

/**
 * @author vivekhs
 *
 */
@Service
public class AuthServiceImpl implements AuthService {

    private AnalyzerProperties analyzerProps;
    private final Algorithm algorithm;
    private final JWTVerifier verifier;
    private final int HASH_KEY_LENGTH;
    private final String SALT_SECRET;
    private final int HASH_ITERATIONS;
    private final String HASH_ALGORITHM;

    @Autowired
    private UsersDAO usersDAO;

    @Autowired
    public AuthServiceImpl(AnalyzerProperties analyzerProps) {
        this.analyzerProps = analyzerProps;
        this.algorithm = Algorithm.HMAC256(this.analyzerProps.getTokenSecret());
        this.verifier = JWT.require(this.algorithm).withIssuer("auth0").build();
        this.HASH_KEY_LENGTH = analyzerProps.getPasswordHashKeyLength();
        this.SALT_SECRET = analyzerProps.getSaltSecret();
        this.HASH_ALGORITHM = analyzerProps.getPasswordHashAlgorithm();
        this.HASH_ITERATIONS = analyzerProps.getPasswordHashIterations();
    }

    public List<User> findUser(String userId) {
        return usersDAO.findByUserId(userId);
    }

    public SignUpRequest formatSignUpRequest(SignUpRequest signUpRequest)
            throws NoSuchAlgorithmException, InvalidKeySpecException {
        String hashedPassword = hashPassword(signUpRequest.getPassword());
        signUpRequest.setPassword(hashedPassword);
        return signUpRequest;
    }

    public User addUser(SignUpRequest signUpRequest) {
        User user = new User();
        user.setFirstName(signUpRequest.getFirstName());
        user.setLastName(signUpRequest.getLastName());
        user.setPassword(signUpRequest.getPassword());
        user.setUserId(signUpRequest.getUserId());
        return usersDAO.save(user);
    }

    @Override
    public String generateToken() throws JWTCreationException {
        String token = JWT.create().withIssuer("auth0").sign(this.algorithm);
        return token;
    }

    @Override
    public Boolean verifyToken(String token) {
        try {
            // need not to do anything with the decoded payload in basic implementation
            this.verifier.verify(token);
            return true;
        } catch (JWTVerificationException exception) {
            return false;
        }
    }

    private String generateSalt(String password) throws NoSuchAlgorithmException {
        password += SALT_SECRET;
        MessageDigest digest = MessageDigest.getInstance("SHA-256");
        byte[] salt = digest.digest(password.getBytes(StandardCharsets.UTF_8));
        return Base64.getEncoder().encodeToString(salt);
    }

    private String hashPassword(String password) throws NoSuchAlgorithmException, InvalidKeySpecException {

        char[] chars = password.toCharArray();
        String salt = generateSalt(password);
        byte[] bytes = salt.getBytes();
        PBEKeySpec spec = new PBEKeySpec(chars, bytes, HASH_ITERATIONS, HASH_KEY_LENGTH);
        Arrays.fill(chars, Character.MIN_VALUE);
        SecretKeyFactory fac = SecretKeyFactory.getInstance(HASH_ALGORITHM);
        byte[] securePassword = fac.generateSecret(spec).getEncoded();
        spec.clearPassword();
        return Base64.getEncoder().encodeToString(securePassword);

    }

    public boolean verifyPassword(String password, String passwordHash)
            throws NoSuchAlgorithmException, InvalidKeySpecException {
        String optEncrypted = hashPassword(password);
        return optEncrypted.equals(passwordHash);
    }

}
