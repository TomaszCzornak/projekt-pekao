package com.pekao.projektpekao.controller.ElectronicJournal;

import com.pekao.projektpekao.controller.Users.UserDtoMapper;
import com.pekao.projektpekao.domain.ElectronicJournal;

import java.util.List;

public class ElectronicJournalDtoMapper {

    private ElectronicJournalDtoMapper() {
    }

    public static ElectronicJournalDto toElectronicJournalDto(ElectronicJournal electronicJournal) {
        return ElectronicJournalDto.builder()
                .id(electronicJournal.getId())
                .created(electronicJournal.getCreated())
                .name(electronicJournal.getName())
                .userDto(UserDtoMapper.toUserDto(electronicJournal.getUser()))
                .eventType(electronicJournal.getEventType())
                .build();
    }

    public static List<ElectronicJournalDto> toElectronicJournalDtos(List<ElectronicJournal> electronicJournalList) {
        return electronicJournalList.stream()
                .map(ElectronicJournalDtoMapper::toElectronicJournalDto)
                .toList();
    }
}
