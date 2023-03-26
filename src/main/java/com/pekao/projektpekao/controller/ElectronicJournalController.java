package com.pekao.projektpekao.controller;

import com.pekao.projektpekao.entity.Book;
import com.pekao.projektpekao.entity.ElectronicJournal;
import com.pekao.projektpekao.service.ElectronicJournalService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@RestController
@RequestMapping("/api/eJournal")
public class ElectronicJournalController {

    private final ElectronicJournalService electronicJournalService;

    public ElectronicJournalController(ElectronicJournalService electronicJournalService) {
        this.electronicJournalService = electronicJournalService;
    }

    @GetMapping("/all")
    public List<ElectronicJournal> getAllElectronicJournals() {
        return electronicJournalService.findAllElectronicJournals();
    }

    @GetMapping("/{id}")
    public Optional<ElectronicJournal> getBookById(@PathVariable("id") Long id) {
        return electronicJournalService.findElectronicJournalById(id);
    }

    @DeleteMapping("/{id}")
    public void deleteBookById(@PathVariable("id") Long id) {
        electronicJournalService.removeElectronicJournalById(id);
    }

    @PostMapping()
    public ElectronicJournal postElectronicJournal(@RequestBody ElectronicJournal electronicJournal) {
        return electronicJournalService.addElectronicJournal(electronicJournal);
    }

    @PutMapping("/{id}")
    public void putElectronicJournal(@PathVariable("id") Long id, @RequestBody ElectronicJournal electronicJournal) {
        if(!Objects.equals(id, electronicJournal.getId())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "nie ma takiego dziennika");
        }
        electronicJournalService.updateElectronicJournal(id, electronicJournal);
    }
}
