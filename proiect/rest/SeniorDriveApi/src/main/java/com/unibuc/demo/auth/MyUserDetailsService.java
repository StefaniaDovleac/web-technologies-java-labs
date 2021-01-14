package com.unibuc.demo.auth;

import com.unibuc.demo.exceptions.EntityNotFoundException;
import com.unibuc.demo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.MethodArgumentNotValidException;

import java.util.ArrayList;
import java.util.Optional;

@Service
public class MyUserDetailsService  implements UserDetailsService {
    private UserRepository repository;

    @Autowired
    public MyUserDetailsService(UserRepository repository) {
        this.repository = repository;
    }

    @Override
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
        Optional<com.unibuc.demo.domain.User> user =  repository.findByUserName(userName);
        if(!user.isPresent()){
            throw new EntityNotFoundException(String.format("User with username %s could not be found", userName));
        }
        return new User(user.get().getUserName(), user.get().getPassword(), new ArrayList<>());
//        return new User(user.get().getUserName(), "{noop}ad", new ArrayList<>());
    }
}
