package com.temelio.foundationemailhelper.controllers;

import com.temelio.foundationemailhelper.models.Foundation;
import com.temelio.foundationemailhelper.repositories.FoundationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/foundation")
public class FoundationController {

    @Autowired
    private FoundationRepository foundationRepository;

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> createFoundation(@RequestBody Foundation foundation) {
        try {
            foundationRepository.save(foundation);
            return new ResponseEntity<>(foundation, HttpStatus.CREATED);
        } catch (Exception exception) {
            return new ResponseEntity<>(exception.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> getAllFoundations(){
        try{
            List<Foundation> foundationList = foundationRepository.findAll();
            return new ResponseEntity<>(foundationList, HttpStatus.OK);
        }catch (Exception exception) {
            return new ResponseEntity<>(exception.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // Read operation
    @GetMapping(value = "/{email}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> getFoundationByEmail(@PathVariable("email") String email) {
        try {
            Foundation foundation = foundationRepository.findByEmail(email);
            if (foundation != null) {
                return new ResponseEntity<>(foundation, HttpStatus.OK);
            } else {
                return new ResponseEntity<>("No foundation found for this email", HttpStatus.NOT_FOUND);
            }
        } catch (Exception exception) {
            return new ResponseEntity<>(exception.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> updateFoundation(@RequestBody Foundation foundation) {
        try {
            Foundation existingFoundation = foundationRepository.findByEmail(foundation.getEmail());
            if (existingFoundation != null) {

                foundationRepository.save(existingFoundation);
                return new ResponseEntity<>(existingFoundation, HttpStatus.OK);
            } else {
                return new ResponseEntity<>("No foundation found for this email", HttpStatus.NOT_FOUND);
            }
        } catch (Exception exception) {
            return new ResponseEntity<>(exception.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/{email}")
    public ResponseEntity<?> deleteFoundation(@PathVariable String email) {
        try {
            Foundation foundation = foundationRepository.findByEmail(email);
            if (foundation != null) {
                foundationRepository.deleteByEmail(email);
                return new ResponseEntity<>("Foundation deleted successfully", HttpStatus.OK);
            } else {
                return new ResponseEntity<>("No foundation found for this email", HttpStatus.NOT_FOUND);
            }
        } catch (Exception exception) {
            return new ResponseEntity<>(exception.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
