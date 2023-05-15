package com.pekao.projektpekao.controller.ElectronicJournal;

import com.pekao.projektpekao.domain.ElectronicJournal.ElectronicJournal;
import com.pekao.projektpekao.domain.ElectronicJournal.ElectronicJournalParams;
import com.pekao.projektpekao.domain.ElectronicJournal.ElectronicJournalParamsMapper;
import com.pekao.projektpekao.service.ElectronicJournalService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Objects;

@RestController
@RequestMapping("/api/eJournal")
public class ElectronicJournalController {

    private final ElectronicJournalService electronicJournalService;

    public ElectronicJournalController(ElectronicJournalService electronicJournalService) {
        this.electronicJournalService = electronicJournalService;
    }

    @GetMapping("/all")
    public ElectronicJournalsResponse getAllElectronicJournals() {
        List<ElectronicJournalDto> electronicJournalDtoList = ElectronicJournalDtoMapper.toElectronicJournalDtos(electronicJournalService.findAllElectronicJournals());
        return ElectronicJournalsResponse.builder()
                .electronicJournalResponseList(electronicJournalDtoList)
                .build();
    }

    @GetMapping("/{id}")
    public ElectronicJournal getBookById(@PathVariable("id") Long id) {
        // write get method to find book by id
        return electronicJournalService.findElectronicJournalById(id);
    }

    @DeleteMapping("/{id}")
    public void deleteBookById(@PathVariable("id") Long id) {
        electronicJournalService.removeElectronicJournalById(id);
    }

    @PostMapping()
    //write post method to add electronic journal
    public ElectronicJournalResponse addElectronicJournal(@RequestBody ElectronicJournalDto electronicJournalDto) {
        // write post method to ElectronicJournal  as parameter
        ElectronicJournalParams electronicJournalToPost = ElectronicJournalParamsMapper.toElectronicJournalParams(electronicJournalDto);
        //save ElectronicJournal with service
        ElectronicJournal electronicJournalSaved = electronicJournalService.addElectronicJournal(electronicJournalToPost);
        //map ElectronicJournal to ElectronicJournalDto
        ElectronicJournalDto electronicJournalDtoMapped = ElectronicJournalDtoMapper.toElectronicJournalDto(electronicJournalSaved);
        //return ElectronicJournalResponse.builder()
        return ElectronicJournalResponse.builder()
                .electronicJournalResponse(electronicJournalDtoMapped)
                .build();
    }

    @PutMapping("/{id}")
//write put method to modify electronic journal
    public void putElectronicJournal(@PathVariable("id") Long id, @RequestBody ElectronicJournalDto electronicJournalDto) {
        if (!Objects.equals(id, electronicJournalDto.getId())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "id does not match");
        }
        ElectronicJournalParams electronicJournalToPut = ElectronicJournalParamsMapper.toElectronicJournalParams(electronicJournalDto);
        electronicJournalService.updateElectronicJournal(electronicJournalToPut);
    }

}
