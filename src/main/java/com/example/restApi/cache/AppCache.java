package com.example.restApi.cache;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.example.restApi.entity.ConfigJournalAppEntity;
import com.example.restApi.repository.ConfigJournalAppRepository;

import jakarta.annotation.PostConstruct;

@Component
public class AppCache{

    @Autowired
    private ConfigJournalAppRepository configJournalAppRepository;

    public Map<String, String> APP_CACHE;

    @PostConstruct
    public void init(){

        APP_CACHE = new HashMap<>();
        List<ConfigJournalAppEntity> all = configJournalAppRepository.findAll();

        for(ConfigJournalAppEntity entity : all){
            APP_CACHE.put(entity.getKey(), entity.getValue());
        }
    }
}

