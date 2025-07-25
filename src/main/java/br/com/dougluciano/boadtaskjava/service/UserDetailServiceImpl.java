package br.com.dougluciano.boadtaskjava.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class UserDetailServiceImpl implements UserDetailsService {

    @Autowired
    private UserService service;


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        var user = service.findByUsername(username);

        return new User(user.getUsername(), user.getPassword(), new ArrayList<>());
    }
}
