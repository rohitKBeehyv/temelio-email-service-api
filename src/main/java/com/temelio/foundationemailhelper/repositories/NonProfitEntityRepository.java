package com.temelio.foundationemailhelper.repositories;

import com.temelio.foundationemailhelper.models.NonProfitEntity;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class NonProfitEntityRepository {

    private Map<String, NonProfitEntity> nonprofits = new HashMap<>();

    public void save(NonProfitEntity nonprofit) {
        nonprofits.put(nonprofit.getEmail(), nonprofit);
    }

    public NonProfitEntity findByEmail(String email) {
        return nonprofits.get(email);
    }

    public void deleteByEmail(String email){
        nonprofits.remove(email);
    }

    public List<NonProfitEntity> findAll(){
        return nonprofits.values().stream().toList();
    }
}
