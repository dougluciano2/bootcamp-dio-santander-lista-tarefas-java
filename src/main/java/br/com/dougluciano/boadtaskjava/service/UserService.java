package br.com.dougluciano.boadtaskjava.service;

import br.com.dougluciano.boadtaskjava.entities.User;
import br.com.dougluciano.boadtaskjava.enums.SystemMessages;
import br.com.dougluciano.boadtaskjava.repository.UserRepository;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private final UserRepository repository;
    private final PasswordEncoder passwordEncoder;

    public UserService(UserRepository repository, PasswordEncoder passwordEncoder){

        this.repository = repository;
        this.passwordEncoder = passwordEncoder;
    }

    public User findByUsername(String username){
        return repository.findByUsername(username)
                .orElseThrow( () ->
                        new UsernameNotFoundException(
                                SystemMessages.USER_NOT_FOUND.getMessage()
                        ));
    }

    public void create(User user){

        String encodedPassword = passwordEncoder.encode(user.getPassword());
        user.setPassword(encodedPassword);

        repository.save(user);
    }
}
