package com.temelio.foundationemailhelper.services;

import com.temelio.foundationemailhelper.models.NonProfitEntity;
import com.temelio.foundationemailhelper.repositories.EmailRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Service
public class EmailService {

    @Autowired
    private EmailRepository emailRepository;

    public String sendEmailToNonProfitEntity(NonProfitEntity nonProfitEntity, String foundationEmail){
        /*
        Sending email to the non-profit can be implemented later using any other third party library, for now just printing it out
         */
        String body = "Sender: $sender | Receiver: $receiver | Date: $date | " +  "Sending money to nonprofit $name at address -> $address";
        body = body.replace("$sender", foundationEmail).replace("$receiver", nonProfitEntity.getEmail())
                        .replace("$date", LocalDateTime.now().format(DateTimeFormatter.ISO_DATE))
                                .replace("$name", nonProfitEntity.getName()).replace("$address", String.valueOf(nonProfitEntity.getAddress()));

        return body;

    }

}
