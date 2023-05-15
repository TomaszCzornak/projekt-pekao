package com.pekao.projektpekao.controller.ElectronicJournal;

import com.pekao.projektpekao.domain.ElectronicJournal.ElectronicJournal;
import com.pekao.projektpekao.domain.ElectronicJournal.ElectronicJournalParams;

import java.util.List;

public class ElectronicJournalEntityMapper {

    private ElectronicJournalEntityMapper() {
    }

    public static ElectronicJournal toElectronicJournalEntity(ElectronicJournalDto electronicJournalDto) {
        return ElectronicJournal.builder()
                .id(electronicJournalDto.getId())
                .name(electronicJournalDto.getName())
                .eventType(electronicJournalDto.getEventType())
                .created(electronicJournalDto.getCreated())
                .buildFrom();
    }

    public static List<ElectronicJournal> toElectronicJournalEntity(List<ElectronicJournalDto> electronicJournalDtoList) {
        return electronicJournalDtoList.stream()
                .map(ElectronicJournalEntityMapper::toElectronicJournalEntity)
                .toList();
    }
    public static ElectronicJournal toElectronicJournalEntity(ElectronicJournalParams electronicJournalDtoParams) {
        return ElectronicJournal.builder()
                .id(electronicJournalDtoParams.getId())
                .name(electronicJournalDtoParams.getName())
                .eventType(electronicJournalDtoParams.getEventType())
                .buildFrom();
    }

}
