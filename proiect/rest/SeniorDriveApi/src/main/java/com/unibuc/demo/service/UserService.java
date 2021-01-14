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
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return userRepository.save(user);
    }

    public User get(Long id) {
        return userRepository.findBy(id)
                .orElseThrow(() -> new EntityNotFoundException(String.format("User with id %s could not be found", id.toString())));
    }

    private boolean emailExists(String email) {
        return userRepository.findByEmail(email).isPresent();
    }

}
