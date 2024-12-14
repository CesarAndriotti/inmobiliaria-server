package com.inmobiliaria.server.controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.inmobiliaria.server.models.Agent;
import com.inmobiliaria.server.models.User;
import com.inmobiliaria.server.models.UserType;
import com.inmobiliaria.server.services.User.UserServiceImpl;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
@RequestMapping("/api")
public class UserController {

    /*@Autowired
    UserServiceImpl userService;

    @GetMapping("user/user-list")
    public List<User> getUserList() {
        
        return userService.createList();
    }

    @PostMapping("/register-user")
    public ResponseEntity<User> postUser(@RequestBody Map<String, Object> payload) {
        
        try {
            
            String nick = String.valueOf(payload.get("nick"));
            String pass = String.valueOf(payload.get("pass"));
            ObjectMapper objectMapper = new ObjectMapper();

            Agent agent = objectMapper.convertValue(payload.get("agent"), Agent.class);
            UserType userType = objectMapper.convertValue(payload.get("userType"), UserType.class);
            User user = new User(0, nick, pass, agent, userType);
            user = userService.registerUser(user);
    
            return ResponseEntity.ok(user);

        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }*/
}
