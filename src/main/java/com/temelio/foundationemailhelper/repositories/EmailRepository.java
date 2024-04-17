package com.temelio.foundationemailhelper.repositories;

import com.temelio.foundationemailhelper.models.Foundation;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.stereotype.Repository;

import java.time.Instant;
import java.util.*;

@Repository
public class EmailRepository {

    private List<MailRecord> mailRecords = new ArrayList<>();

    public void save(String foundationEmail, String nonProfitEmail, String mailBody) {
        mailRecords.add(new MailRecord(foundationEmail, nonProfitEmail, Date.from(Instant.now()), mailBody));
    }

    public List<MailRecord> getAllMails(){
        return mailRecords;
    }
}

@Data
@AllArgsConstructor
class MailRecord{
    private String sender;
    private String receiver;
    private Date date;
    private String mailBody;
}
