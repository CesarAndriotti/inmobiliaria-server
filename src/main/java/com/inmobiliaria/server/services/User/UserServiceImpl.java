package com.inmobiliaria.server.services.User;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.inmobiliaria.server.dto.User.UserResponse;
import com.inmobiliaria.server.exceptions.CustomException;
import com.inmobiliaria.server.mappers.UserMapper;
import com.inmobiliaria.server.models.User;
import com.inmobiliaria.server.repositories.User.UserRepository;
import com.inmobiliaria.server.security.JwtUtil;

@Service
public class UserServiceImpl implements UserService, UserDetailsService {

    @Autowired
    private UserMapper userMapper;

    // Inyecta el PasswordEncoder (debería ser BCryptPasswordEncoder)
    @Autowired
    private PasswordEncoder passwordEncoder;

    // Acceso a variables de entorno si hiciera falta
    @Autowired
    private Environment env;

    // Repositorios necesarios (usuarios, agentes y direcciones

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private AuthenticationConfiguration authenticationConfiguration;

    @Autowired
    JwtUtil jwtUtil;

    public Map<String, Object> authenticateUser(String nick, String password) throws CustomException {
        try {
            //AuthenticationManager se obtiene desde la configuración de Spring Security
            //Autentica al usuario usando el nick y password proporcionados, y si es exitoso, devuelve un token JWT
            AuthenticationManager authenticationManager = authenticationConfiguration.getAuthenticationManager();

            //Autenticación del Usuario
            Authentication auth = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(nick, password));

            //Obtiene el objeto UserDetails con la info del usuario autenticado
            UserDetails userDetails = (UserDetails) auth.getPrincipal();
            //Asi genera el token
            String token = jwtUtil.generateToken(userDetails);

            //Obtenet el usuario desde el repo
            User user = userRepository.findByNick(nick)
                    .orElseThrow(() -> new UsernameNotFoundException("User not found: " + nick));

            //Generacion de respuesta con token, nick y tipo de usuario
            Map<String, Object> response = new HashMap<>();
            response.put("token", token);
            response.put("nick", user.getNick());
            response.put("user_type", user.getUserType().getName());

            return response;

        } catch (BadCredentialsException e) {
            throw new CustomException("Invalid credentials", HttpStatus.UNAUTHORIZED);
        } catch (Exception e) {
            throw new CustomException("Authentication failed", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    /*
     * loadUserByUsername() es llamado internamente por Spring Security
     * cuando authenticationManager.authenticate() es invocado.
     * 
     * authenticationManager.authenticate() no consulta directamente la base de
     * datos.
     * La consulta se realiza en loadUserByUsername() (tu implementación
     * personalizada de UserDetailsService),
     * que es invocada dentro del proceso de autenticación.
     */

    @Override
    public UserDetails loadUserByUsername(String nick) {

        User user = userRepository.findByNick(nick)
                .orElseThrow(() -> new UsernameNotFoundException("User not found: " + nick));

        // Devuelve un objeto UserDetails con nick, contraseña cifrada, y lista vacía de
        // roles (puede extenderse)
        return new org.springframework.security.core.userdetails.User(
                user.getNick(),
                user.getPassword(),
                Collections.singletonList(
                        new SimpleGrantedAuthority("ROLE_" + user.getUserType().getName().toUpperCase())));
    }

    @Override
    public List<UserResponse> getAllUsers() throws CustomException {

        try {
            List<User> usersDatabase = userRepository.findAll();
            return userMapper.toDtoList(usersDatabase);
        } catch (DataAccessException e) {

            throw new CustomException(
                    env.getProperty("data.access-error") + ": " + e.getMessage(),
                    HttpStatus.INTERNAL_SERVER_ERROR);
        } catch (Exception e) {
            throw new CustomException(
                    env.getProperty("unhandled-exception") + ": " + e.getMessage(),
                    HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    public User saveUser(User user) throws CustomException {

        return userRepository.save(user);
    }

    /*
     * public List<User> propertiesFilter(Map<String, String> filter, List<User>
     * users) {
     * 
     * for (Map.Entry<String, String> filtro : filter.entrySet()) {
     * String clave = filtro.getKey();
     * String valor = filtro.getValue().toLowerCase();
     * 
     * switch (clave) {
     * case "name":
     * users = users.stream()
     * .filter(e -> e.getName() != null &&
     * e.getName().toLowerCase().contains(valor))
     * .collect(Collectors.toList());
     * break;
     * case "lastname":
     * users = users.stream()
     * .filter(e -> e.getLastname() != null &&
     * e.getLastname().toLowerCase().contains(valor))
     * .collect(Collectors.toList());
     * break;
     * case "email":
     * users = users.stream()
     * .filter(e -> e.getEmail() != null &&
     * e.getEmail().toLowerCase().contains(valor))
     * .collect(Collectors.toList());
     * break;
     * case "sector":
     * 
     * default:
     * // Ignorar filtros desconocidos o podrías lanzar excepción si lo preferís
     * break;
     * }
     * }
     * 
     * return user;
     * }
     */

}
