package net.revincompany.JournalApp.controller;

import net.revincompany.JournalApp.entity.JournalEntry;
import net.revincompany.JournalApp.service.JournalEntryService;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


//controller -> service -> repo

@RestController
@RequestMapping("/journal")
public class JournalEntryControllerv2 {

    @Autowired
    private JournalEntryService journalEntryService;

    @GetMapping
    public List<JournalEntry> getall() {
        return journalEntryService.getAll();
    }

    @PostMapping
    public JournalEntry createEntry(@RequestBody JournalEntry myEntry) {
        myEntry.setDate(LocalDateTime.now());
        journalEntryService.saveEntry(myEntry);
        return myEntry;
    }

    @GetMapping("id/{myId}")
    public JournalEntry getJournalEntryById(@PathVariable ObjectId myId) {
        return journalEntryService.findByID(myId).orElse(null);
    }


    @DeleteMapping("id/{myId}")
    public boolean deleteJournalEntryById(@PathVariable ObjectId myId) {
        journalEntryService.deleteByID(myId);
        return true;
    }

    @PutMapping("id/{myId}")
    public JournalEntry updateJournalEntryById(@PathVariable ObjectId myId, @RequestBody JournalEntry newEntry) {
        JournalEntry old = journalEntryService.findByID(myId).orElse(null);
        if(old!=null)
        {
            old.setTitle(newEntry.getTitle()!=null && !newEntry.getTitle().equals("")? newEntry.getTitle(): old.getTitle());
            old.setContent(newEntry.getContent()!=null && !newEntry.getContent().equals("")? newEntry.getContent(): old.getContent());
        }
        journalEntryService.saveEntry(old);
        return newEntry;
    }
}
