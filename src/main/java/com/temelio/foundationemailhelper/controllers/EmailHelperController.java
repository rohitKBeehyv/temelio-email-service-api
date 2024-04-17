package com.temelio.foundationemailhelper.controllers;

import com.temelio.foundationemailhelper.models.NonProfitEntity;
import com.temelio.foundationemailhelper.repositories.EmailRepository;
import com.temelio.foundationemailhelper.repositories.NonProfitEntityRepository;
import com.temelio.foundationemailhelper.services.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/email")
public class EmailHelperController {

    @Autowired
    private NonProfitEntityRepository nonprofitRepository;

    @Autowired
    private EmailService emailService;

    @Autowired
    private EmailRepository emailRepository;


    @PostMapping("/sendEmail")
    public ResponseEntity<?> sendEmailToNonprofit(@RequestParam String nonprofitEmail, @RequestParam String foundationEmail) {
        NonProfitEntity nonprofit = nonprofitRepository.findByEmail(nonprofitEmail);
        if (nonprofit != null) {
            String mailBody = emailService.sendEmailToNonProfitEntity(nonprofit, foundationEmail);
            emailRepository.save(foundationEmail, nonprofit.getEmail(), mailBody);
            return new ResponseEntity<>(mailBody, HttpStatus.OK);
        }
        return new ResponseEntity<>("no non-profit for this email", HttpStatus.NOT_FOUND);
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> listAllEmails(){
        try{
            return new ResponseEntity<>(emailRepository.getAllMails(), HttpStatus.OK);
        }catch(Exception exception){
            return new ResponseEntity<>(exception.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
