package org.launchcode.techjobsauth;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.launchcode.techjobsauth.controllers.AuthenticationController;
import org.launchcode.techjobsauth.models.User;
import org.launchcode.techjobsauth.models.data.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.HandlerInterceptor;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

//TODO: 3.1. Create an AuthenticationFilter class in the techjobsauth package.
// a) Have this class implement the HandlerInterceptor.
// b) Add autowired instances of both UserRepository and AuthenticationController.
// c) Add a preHandle method.
//   1. This must override the inherited method of the same name.
//   2. Grab the session information from the request object.
//   3. Query the the session data for a user.
//   4. If a user exists, return true. Otherwise, redirect to the login page and return false.

//TODO: 3.2. Create a whitelist.
// a) In the top of AuthenticationFilter, add a whitelist variable containing the paths
//    that can be accessed without a user session.
// b) Create a method next that checks a given path against the values in the whitelist.
// c) Update preHandle with a call to this method.
//    1. Before looking for session and user status, add a conditional
//       that checks the whitelist status of the current request object.

public class AuthenticationFilter implements HandlerInterceptor {

    @Autowired
    UserRepository userRepository;

    @Autowired
    AuthenticationController authenticationController;

    private static final List<String> whitelist = Arrays.asList("/login", "/register", "/logout", "/css");

    private static boolean isWhitelisted(String path) {
        for (String pathRoot : whitelist) {
            if (path.startsWith(pathRoot)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean preHandle(HttpServletRequest request,
                             HttpServletResponse response,
                             Object handler) throws IOException {

        // Don't require sign-in for whitelisted pages
        if (isWhitelisted(request.getRequestURI())) {
            // returning true indicates that the request may proceed
            return true;
        }

        HttpSession session = request.getSession();
        User user = authenticationController.getUserFromSession(session);

        // The user is logged in
        if (user != null) {
            return true;
        }

        // The user is NOT logged in
        response.sendRedirect("/login");
        return false;
    }
}
