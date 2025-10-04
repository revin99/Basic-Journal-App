package net.revincompany.JournalApp.service;

import net.revincompany.JournalApp.entity.JournalEntry;
import net.revincompany.JournalApp.repository.JournalEntryRepository;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
public class JournalEntryService  {

    @Autowired
    private JournalEntryRepository journalEntryRepository;

    public void saveEntry(JournalEntry journalEntry){
        journalEntryRepository.save(journalEntry);
    }

    public List<JournalEntry> getAll(){
        return journalEntryRepository.findAll();
    }

    public Optional<JournalEntry> findByID(ObjectId id){
        return journalEntryRepository.findById(id);
    }

    public void deleteByID(ObjectId id){
        journalEntryRepository.deleteById(id);
    }



}
