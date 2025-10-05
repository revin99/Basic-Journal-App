package net.revincompany.JournalApp.controller;

import net.revincompany.JournalApp.entity.JournalEntry;
import net.revincompany.JournalApp.entity.User;
import net.revincompany.JournalApp.service.JournalEntryService;
import net.revincompany.JournalApp.service.UserService;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.*;


//controller -> service -> repo

@RestController
@RequestMapping("/journal")
public class JournalEntryControllerv2 {

    @Autowired
    private JournalEntryService journalEntryService;

    @Autowired
    private UserService userService;

    @GetMapping("{userName}")
    public ResponseEntity<List<JournalEntry>> getAllJournalEntriesOfUser(@PathVariable String userName) {

        User user = userService.findbyUserName(userName);
        List<JournalEntry> all = user.getJournalEntries();

        if(all!=null && !all.isEmpty())
        {
            return new ResponseEntity<>(all,HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PostMapping("{userName}")
    public ResponseEntity<JournalEntry> createEntry(@RequestBody JournalEntry myEntry, @PathVariable String userName) {
        try {
            myEntry.setDate(LocalDateTime.now());
            journalEntryService.saveEntry(myEntry,userName);
            return new ResponseEntity<JournalEntry>(myEntry, HttpStatus.CREATED);
        }catch (Exception e){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("id/{myId}")
    public ResponseEntity<JournalEntry>getJournalEntryById(@PathVariable ObjectId myId) {
        Optional<JournalEntry> journalEntry = journalEntryService.findByID(myId);
        if(journalEntry.isPresent()){
            return new ResponseEntity<>(journalEntry.get(), HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }


    @DeleteMapping("id/{userName}/{myId}")
    public ResponseEntity<JournalEntry> deleteJournalEntryById(@PathVariable ObjectId myId, @PathVariable String userName) {
        journalEntryService.deleteByID(myId,userName);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PutMapping("id/{userName}/{myId}")
    public ResponseEntity<JournalEntry> updateJournalEntryById(
            @PathVariable ObjectId myId,
            @RequestBody JournalEntry newEntry,
            @PathVariable String userName) {
        JournalEntry old = journalEntryService.findByID(myId).orElse(null);
        if(old!=null)
        {
            old.setTitle(newEntry.getTitle()!=null && !newEntry.getTitle().equals("")? newEntry.getTitle(): old.getTitle());
            old.setContent(newEntry.getContent()!=null && !newEntry.getContent().equals("")? newEntry.getContent(): old.getContent());
            journalEntryService.saveEntry(old);
            return new ResponseEntity<>(old,HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);

    }
}
