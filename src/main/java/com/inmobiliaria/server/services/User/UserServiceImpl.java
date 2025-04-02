package com.inmobiliaria.server.services.User;

import java.util.ArrayList;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import com.inmobiliaria.server.models.User;
import com.inmobiliaria.server.repositories.User.UserRepository;
import jakarta.transaction.Transactional;

@Service
public class UserServiceImpl implements UserService, UserDetailsService {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public UserDetails loadUserByUsername(String nick) throws UsernameNotFoundException {
        
        User user = userRepository.findByNick(nick)
            .orElseThrow(() -> new UsernameNotFoundException("Usuario no encontrado: " + nick));

        return new org.springframework.security.core.userdetails.User(
            user.getNick(),
            user.getPassword(),
            new ArrayList<>()
        );
    }

    @Transactional
    @Override
    public User registerUser(User user) {
        
        try {
            Optional<User> userDatabase = userRepository.findByNick(user.getNick());

            if(userDatabase.isPresent()){
                return null;
            }
            
            String encryptedPassword = passwordEncoder.encode(user.getPassword());
            user.setPassword(encryptedPassword);
            User newUser = userRepository.save(user);
            return newUser;

        } catch (Exception e) {
            
            throw new RuntimeException(e.getMessage());
        }
    }

    @Override
    public User findUserNick(String nick) {
    
        try {
            Optional<User> userDatabase = userRepository.findByNick(nick);

            return userDatabase.get();
        } catch (Exception e) {
            
            return null;
        }
    }    

    
}


