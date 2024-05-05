package com.d2d.grh.grhBackend.configuration;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.d2d.grh.grhBackend.constant.SecurityParams;
import com.d2d.grh.grhBackend.entity.Candidate;
import com.d2d.grh.grhBackend.entity.User;
import com.d2d.grh.grhBackend.models.RequestObject;
import com.d2d.grh.grhBackend.service.CandidateService;
import com.d2d.grh.grhBackend.service.UserService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.bind.annotation.ResponseStatus;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class JWTAuthenticationFilter extends UsernamePasswordAuthenticationFilter {

    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private UserService userService;
    @Autowired
    private CandidateService candidateService;


    public JWTAuthenticationFilter(AuthenticationManager authenticationManager, UserService userService, CandidateService candidateService) {
        this.authenticationManager = authenticationManager;
        this.userService = userService;
        this.candidateService = candidateService;
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {

        try {
            Object userObject = new ObjectMapper().readValue(request.getInputStream(), Object.class);
            String username = userObject.toString().substring(10, userObject.toString().indexOf(','));

            String password = userObject.toString().substring(userObject.toString().indexOf("password=")+"password=".length(), userObject.toString().length()-1);
            System.out.println("userObject: "+userObject.toString());
            System.out.println("p index : "+userObject.toString().indexOf("password="));

            System.out.println("username : "+username);
            System.out.println("passwd : "+password);

            User user = this.userService.findUserByUsername2(username);
            Candidate candidate = this.candidateService.getCandidateByUsername(username);

            if (user ==null ){
                System.out.println("ccc");
              return   authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
            }
            else if(user !=null){
                System.out.println("uuu");
                return   authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
            }
            System.out.println("return null");
            System.out.println(candidate.getEmail());
            System.out.println(user.getEmail());
//            RequestObject requestObject = new ObjectMapper().readValue(user.toString().toString(), RequestObject.class);
//            System.out.println(requestObject.getUsername());

            return null;
        } catch (IOException e){
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    @Override
    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain, Authentication authResult) throws IOException, ServletException {
        org.springframework.security.core.userdetails.User user = (org.springframework.security.core.userdetails.User) authResult.getPrincipal();
        List<String> roles = new ArrayList<>();
        System.out.println("aaa  "+user);
        authResult.getAuthorities().forEach(a->{
            roles.add(a.getAuthority());
        });
        String jwt = JWT.create()
                .withIssuer(request.getRequestURI())
                .withSubject(user.getUsername())
                .withArrayClaim("roles", roles.toArray(new String[roles.size()]))
                .withExpiresAt(new Date(System.currentTimeMillis()+SecurityParams.EXPIRATION))
                .sign(Algorithm.HMAC256(SecurityParams.SECRET));
        response.addHeader(SecurityParams.JWT_HEADER_NAME, SecurityParams.HEADER_PREFIX+jwt);
    }

    @Override
    protected void unsuccessfulAuthentication(HttpServletRequest request, HttpServletResponse response, AuthenticationException failed) throws IOException, ServletException {
        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED,"Authentication failed !! Verify your credentials. ");

    }

}