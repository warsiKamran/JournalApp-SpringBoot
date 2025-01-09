package com.example.restApi.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.bson.types.ObjectId;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.example.restApi.entity.JournalEntry;
import com.example.restApi.entity.User;
import com.example.restApi.repository.JournalEntryRepository;

@Component
public class JournalEntryService{

    @Autowired
    private JournalEntryRepository journalEntryRepository;

    @Autowired
    private UserService userService;

    @Transactional
    public void saveEntry(JournalEntry journalEntry, String userName){
        User user = userService.findByuserName(userName);
        journalEntry.setDate(LocalDateTime.now());
        JournalEntry saved = journalEntryRepository.save(journalEntry);
        user.getJournalEntries().add(saved);
        userService.saveEntry(user);
    }

    public void saveEntry(JournalEntry journalEntry){
        journalEntryRepository.save(journalEntry);
    }

    public List<JournalEntry> getAll(){
        return journalEntryRepository.findAll();
    }

    public Optional<JournalEntry> findById(ObjectId id){
        return journalEntryRepository.findById(id);
    }

    @Transactional
    public void deleteById(ObjectId id, String userName){

        try{

            User user = userService.findByuserName(userName);

            boolean isRemoved = user.getJournalEntries().removeIf(x -> x.getId().equals(id));

            if(isRemoved){
                userService.saveEntry(user);
                journalEntryRepository.deleteById(id);
            }
            
        } 
        
        catch (Exception e){
            System.out.println(e);
            throw new RuntimeException("Cannot delete", e);
        }
    }
}

