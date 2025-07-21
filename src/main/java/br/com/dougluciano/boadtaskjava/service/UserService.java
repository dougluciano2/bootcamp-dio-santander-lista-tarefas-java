package br.com.dougluciano.boadtaskjava.service;

import br.com.dougluciano.boadtaskjava.entities.User;
import br.com.dougluciano.boadtaskjava.enums.SystemMessages;
import br.com.dougluciano.boadtaskjava.repository.UserRepository;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private final UserRepository repository;

    public UserService(UserRepository repository){
        this.repository = repository;
    }

    public User findByUsername(String username){
        return repository.findByUsername(username)
                .orElseThrow( () ->
                        new UsernameNotFoundException(
                                SystemMessages.USER_NOT_FOUND.getMessage()
                        ));
    }
}
