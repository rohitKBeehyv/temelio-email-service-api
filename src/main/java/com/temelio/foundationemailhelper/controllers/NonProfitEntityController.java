package com.temelio.foundationemailhelper.controllers;

import com.temelio.foundationemailhelper.models.NonProfitEntity;
import com.temelio.foundationemailhelper.repositories.NonProfitEntityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/nonprofit")
public class NonProfitEntityController {

    @Autowired
    private NonProfitEntityRepository nonProfitEntityRepository;

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> createNonProfit(@RequestBody NonProfitEntity nonProfitEntity) {
        try {
            nonProfitEntityRepository.save(nonProfitEntity);
            return new ResponseEntity<>(nonProfitEntity, HttpStatus.CREATED);
        } catch (Exception exception) {
            return new ResponseEntity<>(exception.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // Read operation
    @GetMapping(value = "/{email}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> getNonProfitByEmail(@PathVariable("email") String email) {
        try {
            NonProfitEntity nonProfitEntity = nonProfitEntityRepository.findByEmail(email);
            if (nonProfitEntity != null) {
                return new ResponseEntity<>(nonProfitEntity, HttpStatus.OK);
            } else {
                return new ResponseEntity<>("No nonprofit found for this email", HttpStatus.NOT_FOUND);
            }
        } catch (Exception exception) {
            return new ResponseEntity<>(exception.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> getNonProfitByEmail() {
        try {
            List<NonProfitEntity> nonProfitEntityList = nonProfitEntityRepository.findAll();
            return new ResponseEntity<>(nonProfitEntityList, HttpStatus.OK);
        } catch (Exception exception) {
            return new ResponseEntity<>(exception.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> updateNonProfit(@RequestBody NonProfitEntity nonProfitEntity) {
        try {
            NonProfitEntity existingNonProfitEntity = nonProfitEntityRepository.findByEmail(nonProfitEntity.getEmail());
            if (existingNonProfitEntity != null) {

                existingNonProfitEntity.setName(nonProfitEntity.getName());
                existingNonProfitEntity.setAddress(nonProfitEntity.getAddress());

                nonProfitEntityRepository.save(existingNonProfitEntity);
                return new ResponseEntity<>(existingNonProfitEntity, HttpStatus.OK);
            } else {
                return new ResponseEntity<>("No nonprofit found for this email", HttpStatus.NOT_FOUND);
            }
        } catch (Exception exception) {
            return new ResponseEntity<>(exception.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/{email}")
    public ResponseEntity<?> deleteNonProfit(@PathVariable String email) {
        try {
            NonProfitEntity nonProfitEntity = nonProfitEntityRepository.findByEmail(email);
            if (nonProfitEntity != null) {
                nonProfitEntityRepository.deleteByEmail(email);
                return new ResponseEntity<>("Nonprofit deleted successfully", HttpStatus.OK);
            } else {
                return new ResponseEntity<>("No nonprofit found for this email", HttpStatus.NOT_FOUND);
            }
        } catch (Exception exception) {
            return new ResponseEntity<>(exception.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
