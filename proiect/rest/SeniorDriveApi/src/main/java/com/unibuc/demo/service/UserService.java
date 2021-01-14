package com.unibuc.demo.service;

import com.unibuc.demo.domain.User;
import com.unibuc.demo.exceptions.EmailExistsException;
import com.unibuc.demo.exceptions.EntityNotFoundException;
import com.unibuc.demo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;


@Service
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public User create(User user) throws EmailExistsException {
        if (emailExists(user.getEmail())) {
            throw new EmailExistsException("There is an account with that email address: " + user.getEmail());
        }
//        String encodedPassword = passwordEncoder.encode(user.getPassword()); // doesn' t work :(
//        System.out.println(encodedPassword);
//        user.setPassword(encodedPassword);
        return userRepository.save(user);
    }

    public User get(Integer id) {
        return userRepository.findBy(id)
                .orElseThrow(() -> new EntityNotFoundException(String.format("User with id %s could not be found", id.toString())));
    }

    private boolean emailExists(String email) {
        return userRepository.findByEmail(email).isPresent();
    }

    public Boolean delete(Integer id) {
        Boolean result =  userRepository.delete(id);
        if(result == false){
            throw new EntityNotFoundException(String.format("User with id %d could not be found", id));
        }
        return result;
    }

}
