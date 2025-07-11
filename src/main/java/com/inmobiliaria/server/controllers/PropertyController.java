package com.inmobiliaria.server.controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.inmobiliaria.server.dto.ResponseDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.PathVariable;

@RestController
@RequestMapping("/api/properties")
public class PropertyController {

    @GetMapping("/show-list")
    public ResponseEntity<ResponseDto> getMethodName(@RequestParam String param) {
        return null;
    }

    @PostMapping("/register")
    public ResponseEntity<ResponseDto> postMethodName(@RequestBody String entity) {
        //TODO: process POST request
        
        return null;
    }
    
    @PutMapping("/update")
    public ResponseEntity<ResponseDto> putMethodName(@PathVariable String id, @RequestBody String entity) {
        //TODO: process PUT request
        
        return null;
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<ResponseDto> deleteProperty(){
        return null;
    }
}
