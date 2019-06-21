/**
 * 
 */
package com.hackathon.controllers.authentication.impl;

import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hackathon.controllers.authentication.AuthenticationController;
import com.hackathon.models.request.authentication.SignInRequest;
import com.hackathon.models.request.authentication.SignUpRequest;
import com.hackathon.models.response.authentication.SignInResponse;
import com.hackathon.models.response.authentication.SignUpResponse;
import com.hackathon.models.user.User;
import com.hackathon.services.authentication.AuthService;

/**
 * @author vivekhs
 *
 */
@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/auth")
public class AuthenticationControllerImpl implements AuthenticationController {

    @Autowired
    private AuthService authService;

    @PostMapping(value = "/sign_in")
    public SignInResponse signIn(@RequestBody SignInRequest signInRequest, HttpServletResponse response) {
        SignInResponse signInResponse = new SignInResponse();
        List<User> userList = authService.findUser(signInRequest.getUserId());
        if (userList == null || userList.size() == 0) {
            signInResponse.setError("User doesn't exist");
        } else {
            User user = userList.get(0);
            try {
                boolean isVerified = authService.verifyPassword(signInRequest.getPassword(), user.getPassword());
                if (!isVerified) {
                    response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
                    signInResponse.setError("UserId or password is invalid");
                } else {
                    String accessToken = authService.generateToken();
                    signInResponse.setAccessToken(accessToken);
                }
            } catch (NoSuchAlgorithmException | InvalidKeySpecException e) {
                response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
                signInResponse.setError("Something went wrong");
            } catch (Exception exception) {
                response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
                signInResponse.setError("UserId or password is invalid");
            }
        }
        return signInResponse;
    }

    @PostMapping(value = "/sign_up")
    public SignUpResponse signUp(@RequestBody SignUpRequest signUpRequest, HttpServletResponse response) {
        List<User> userList = authService.findUser(signUpRequest.getUserId());
        SignUpResponse signUpResponse = new SignUpResponse();
        if (userList != null && userList.size() > 0) {
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            signUpResponse.setError("User already exists");
            return signUpResponse;
        }
        try {
            signUpRequest = authService.formatSignUpRequest(signUpRequest);
            authService.addUser(signUpRequest);
            signUpResponse.setMessage("User registered successfully");
            return signUpResponse;
        } catch (NoSuchAlgorithmException | InvalidKeySpecException e) {
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            signUpResponse.setError("Something went wrong");
            return signUpResponse;
        } catch (Exception e) {
            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            signUpResponse.setError("Internal server error");
            return signUpResponse;
        }
    }
}
