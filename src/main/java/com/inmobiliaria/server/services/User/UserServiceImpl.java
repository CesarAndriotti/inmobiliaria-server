package com.inmobiliaria.server.services.User;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.ConversionFailedException;
import org.springframework.core.env.Environment;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.InvalidDataAccessApiUsageException;
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
import org.springframework.transaction.UnexpectedRollbackException;
import org.springframework.web.client.HttpServerErrorException.InternalServerError;
import com.inmobiliaria.server.exceptions.CustomException;
import com.inmobiliaria.server.models.Address;
import com.inmobiliaria.server.models.User;
import com.inmobiliaria.server.repositories.Address.AddressRepository;
import com.inmobiliaria.server.repositories.Agent.AgentRepository;
import com.inmobiliaria.server.repositories.User.UserRepository;
import com.inmobiliaria.server.security.JwtUtil;
import jakarta.transaction.Transactional;

@Service
public class UserServiceImpl implements UserService, UserDetailsService {

    // Inyecta el PasswordEncoder (debería ser BCryptPasswordEncoder)
    @Autowired
    private PasswordEncoder passwordEncoder;

    // Acceso a variables de entorno si hiciera falta
    @Autowired
    private Environment env;

    // Repositorios necesarios (usuarios, agentes y direcciones)
    @Autowired
    private AgentRepository agentRepository;

    @Autowired
    private AddressRepository addressRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private AuthenticationConfiguration authenticationConfiguration;

    @Autowired
    JwtUtil jwtUtil;

    @Override
    @Transactional
    public User registerUserAndAgent(User user) throws CustomException {

        try { 
            if (userRepository.findFirstUserOrderedById().isEmpty()) user.getUser_type().setId(1);
            else user.getUser_type().setId(user.getUser_type().getId());
                
            if (userRepository.findByNick(user.getNick()).isPresent()) {
                throw new CustomException(
                    "The nick already exists",
                    HttpStatus.CONFLICT
                );
            }
            
            if (agentRepository.findByIdentificationNumberOrEmailOrAgentRegistrationOrPhoneNumber(
                user.getAgent().getIdentificationNumber(), 
                user.getAgent().getEmail(),
                user.getAgent().getAgentRegistration(), 
                user.getAgent().getPhoneNumber()).isPresent()) {
                throw new CustomException(
                    "The identification number or email already exists", 
                    HttpStatus.CONFLICT
                );
            }

            Address address = user.getAgent().getAddress();
            Optional <Address> existingAddress = addressRepository.findByStreetnameAndNumber(address.getStreetName(), address.getNumber());
            
            if (existingAddress.isPresent()) user.getAgent().setAddress(existingAddress.get());
            else addressRepository.save(address);

            agentRepository.save(user.getAgent());

            String encryptedPassword = passwordEncoder.encode(user.getPassword());
            user.setPassword(encryptedPassword);
            User newUser = userRepository.save(user);

            return newUser;
        } 
        catch(ConversionFailedException e){

            throw new CustomException(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
        catch(InternalServerError e){

            throw new CustomException(env.getProperty("http.server.internal-server"), HttpStatus.INTERNAL_SERVER_ERROR);
        }
        catch(NullPointerException e){

            throw new CustomException(env.getProperty("http.server.internal-server"), HttpStatus.INTERNAL_SERVER_ERROR);
        }
        catch(InvalidDataAccessApiUsageException e){

            throw new CustomException(env.getProperty("http.server.internal-server"), HttpStatus.INTERNAL_SERVER_ERROR);
        }
        catch(DataIntegrityViolationException e){

            throw new CustomException(env.getProperty("database.data-integrity-violation"), HttpStatus.INTERNAL_SERVER_ERROR);
        }
        catch(UnexpectedRollbackException e){
            
            throw new CustomException(env.getProperty("database.data-integrity-violation"), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public Map<String, Object> authenticateUser(String nick, String password) throws CustomException {
        try {
            /*Paso 1: Obtener AuthenticationManager
            AuthenticationManager se obtiene desde la configuración de Spring Security 
            a través de la clase AuthenticationConfiguration. Este administrador se encargará de autenticar al usuario. */
            AuthenticationManager authenticationManager = authenticationConfiguration.getAuthenticationManager();
    
            /*Paso 2: Se usa el AuthenticationManager para autenticar las credenciales proporcionadas. 
            Esto se hace mediante la creación de un UsernamePasswordAuthenticationToken con el nick y password del usuario.
            El AuthenticationManager delega la validación a un UserDetailsService, 
            que es la función encargada de cargar los detalles del usuario desde la base de datos. */
            Authentication auth = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(nick, password)
            );
    
            /*Paso 3: Una vez que el usuario ha sido autenticado, auth.getPrincipal() contiene un objeto UserDetails, 
            que representa al usuario autenticado. A partir de este objeto, 
            se genera el token JWT mediante el método jwtUtil.generateToken(userDetails). */
            UserDetails userDetails = (UserDetails) auth.getPrincipal();
            String token = jwtUtil.generateToken(userDetails);
    
            /*Paso 4: Obtener el Usuario desde el Repositorio
            Después de la autenticación, se obtiene el usuario real desde el repositorio (userRepository), 
            buscando por su nick. Esto asegura que los detalles completos del usuario sean cargados. */
            User user = userRepository.findByNick(nick)
                .orElseThrow(() -> new UsernameNotFoundException("User not found: " + nick));
    
            /*Paso 5: Generación de Respuesta
            El token generado y otros detalles del usuario (como nick y userType) se colocan en un Map<String, 
            Object>, que es la respuesta que se enviará de vuelta al frontend. */
            Map<String, Object> response = new HashMap<>();
            response.put("token", token);
            response.put("nick", user.getNick());
            response.put("userType", user.getUser_type().getType());
    
            return response;
            
        } catch (BadCredentialsException e) {
            throw new CustomException("Invalid credentials", HttpStatus.UNAUTHORIZED);
        } catch (Exception e) {
            throw new CustomException("Authentication failed", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    
    /*loadUserByUsername() es llamado internamente por Spring Security 
    cuando authenticationManager.authenticate() es invocado.

    authenticationManager.authenticate() no consulta directamente la base de datos. 
    La consulta se realiza en loadUserByUsername() (tu implementación personalizada de UserDetailsService), 
    que es invocada dentro del proceso de autenticación. */
    
    @Override
    public UserDetails loadUserByUsername(String nick) {
        
        User user = userRepository.findByNick(nick)
            .orElseThrow(() -> new UsernameNotFoundException("User not found: " + nick));

        // Devuelve un objeto UserDetails con nick, contraseña cifrada, y lista vacía de roles (puede extenderse)
        return new org.springframework.security.core.userdetails.User(
            user.getNick(),
            user.getPassword(),
            Collections.singletonList(new SimpleGrantedAuthority("ROLE_" + user.getUser_type().getType().toUpperCase()))
        );
    }
}


