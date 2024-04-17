package com.temelio.foundationemailhelper.repositories;

import com.temelio.foundationemailhelper.models.Foundation;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class FoundationRepository {
    private Map<String, Foundation> foundations = new HashMap<>();

    public void save(Foundation foundation) {
        foundations.put(foundation.getEmail(), foundation);
    }

    public Foundation findByEmail(String email) {
        return foundations.get(email);
    }

    public void deleteByEmail(String email){
        foundations.remove(email);
    }

    public List<Foundation> findAll(){
        return foundations.values().stream().toList();
    }
}
