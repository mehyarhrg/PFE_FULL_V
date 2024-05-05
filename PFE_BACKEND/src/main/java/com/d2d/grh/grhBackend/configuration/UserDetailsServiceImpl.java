package com.d2d.grh.grhBackend.configuration;

import com.d2d.grh.grhBackend.entity.Candidate;
import com.d2d.grh.grhBackend.entity.User;
import com.d2d.grh.grhBackend.exception.UserNotFoundException;
import com.d2d.grh.grhBackend.service.CandidateService;
import com.d2d.grh.grhBackend.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    private UserService userService;
    private CandidateService candidateService;

    @Autowired
    public UserDetailsServiceImpl(UserService userService, CandidateService candidateService) {
        this.userService = userService;
        this.candidateService = candidateService;
    }


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = this.userService.findUserByUsername2(username);
        Candidate candidate = this.candidateService.getCandidateByUsername(username);
        if(user == null && candidate ==null){
            throw new UsernameNotFoundException("invalid user");
        } else if (user != null && candidate ==null) {
            Collection<GrantedAuthority> authorities = new ArrayList<>();
            user.getRoles().forEach(r->{
                authorities.add(new SimpleGrantedAuthority(r.getRoleName()));
            });
            return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(), authorities);
        } else if (user == null && candidate !=null) {
            Collection<GrantedAuthority> authorities = new ArrayList<>();

                authorities.add(new SimpleGrantedAuthority("Candidate"));
            return new org.springframework.security.core.userdetails.User(candidate.getUsername(), candidate.getPassword(), authorities);

        }else {return null;}
    }
}