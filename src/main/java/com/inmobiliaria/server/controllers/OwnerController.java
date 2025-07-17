package com.inmobiliaria.server.controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.inmobiliaria.server.dto.ResponseDto;
import com.inmobiliaria.server.exceptions.CustomException;
import com.inmobiliaria.server.models.Owner;
import com.inmobiliaria.server.services.Owner.OwnerServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PutMapping;

@RestController
@RequestMapping("/api/owners")
public class OwnerController {

    @Autowired
    Environment env;
    @Autowired 
    OwnerServiceImpl ownerServiceImpl;

    @PostMapping("/register")
    public ResponseEntity<ResponseDto> postOwner(@RequestBody Owner owner) throws CustomException {
        
        System.out.println("\n\n\n"+owner);
        if (owner == null)  {
            
            throw new CustomException(
                env.getProperty("http.client.bad-request"), 
                HttpStatus.BAD_REQUEST
            );
        }
        
        Owner ownerRegistered = ownerServiceImpl.addLinkAsOwner(owner);

        return ResponseEntity.status(HttpStatus.CREATED).body(new ResponseDto<>(
            
            ownerRegistered,
            env.getProperty("http.success.created"),
            HttpStatus.CREATED.value()
        ));
    }

    @PutMapping("/update")
    public ResponseEntity<ResponseDto> putOwner(@RequestBody Owner owner) throws CustomException {
        
        if (owner == null)  {
            
            throw new CustomException(
                env.getProperty("http.client.bad-request"), 
                HttpStatus.BAD_REQUEST
            );
        }
        
        Owner ownerRegistered = ownerServiceImpl.updateOwner(owner);

        return ResponseEntity.status(HttpStatus.ACCEPTED).body(new ResponseDto<>(
            
            ownerRegistered,
            env.getProperty("http.success.accepted"),
            HttpStatus.ACCEPTED.value()
        ));
    }
}
