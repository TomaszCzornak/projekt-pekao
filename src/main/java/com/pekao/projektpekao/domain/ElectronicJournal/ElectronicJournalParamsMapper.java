package com.pekao.projektpekao.domain.ElectronicJournal;

import com.pekao.projektpekao.controller.ElectronicJournal.ElectronicJournalDto;

import java.util.List;

public class ElectronicJournalParamsMapper {

    private ElectronicJournalParamsMapper() {
    }

    public static ElectronicJournalParams toElectronicJournalParams(ElectronicJournalDto electronicJournalDto) {
        return ElectronicJournalParams.builder()
                .id(electronicJournalDto.getId())
                .name(electronicJournalDto.getName())
                .eventType(electronicJournalDto.getEventType())
                .build();
    }

    public static List<ElectronicJournalParams> toElectronicJournalParamsList(List<ElectronicJournalDto> electronicJournalDtoList) {
        return electronicJournalDtoList.stream()
                .map(ElectronicJournalParamsMapper::toElectronicJournalParams)
                .toList();
    }
}
