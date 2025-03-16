package com.inmobiliaria.server.services.User;

import java.util.ArrayList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import com.inmobiliaria.server.models.User;
import com.inmobiliaria.server.repositories.User.UserRepository;
import jakarta.transaction.Transactional;
import com.inmobiliaria.server.dto.UserDto;

@Service
public class UserServiceImpl implements UserService, UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    private final UserMapper userMapper = UserMapper.INSTANCE;

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
    public UserDto registerUser(UserDto userDto) {
        
        User user, newUser;

        user = userMapper.ToUser(userDto);
        String encryptedPassword = passwordEncoder.encode(userDto.getPassword());
        user.setPassword(encryptedPassword);
        
        try {

            newUser = userRepository.save(user);
            return userMapper.ToUserDto(newUser);

        } catch (Exception e) {
            
            throw new RuntimeException("No se pudo registrar el agente. Por favor, intente nuevamente. " +e.getMessage());
        }
    }    
}


