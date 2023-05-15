package com.pekao.projektpekao.controller.ElectronicJournal;

import com.pekao.projektpekao.domain.ElectronicJournal.ElectronicJournal;

import java.util.List;

public class ElectronicJournalDtoMapper {

    private ElectronicJournalDtoMapper() {
    }

    public static ElectronicJournalDto toElectronicJournalDto(ElectronicJournal electronicJournal) {
        return ElectronicJournalDto.builder()
                .id(electronicJournal.getId())
                .created(electronicJournal.getCreated())
                .name(electronicJournal.getName())
                .eventType(electronicJournal.getEventType())
                .build();
    }

    public static List<ElectronicJournalDto> toElectronicJournalDtos(List<ElectronicJournal> electronicJournalList) {
        return electronicJournalList.stream()
                .map(ElectronicJournalDtoMapper::toElectronicJournalDto)
                .toList();
    }
}
