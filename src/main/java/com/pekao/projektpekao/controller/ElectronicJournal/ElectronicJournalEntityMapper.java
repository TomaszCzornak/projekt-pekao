package com.pekao.projektpekao.controller.ElectronicJournal;

import com.pekao.projektpekao.controller.Users.UserEntityMapper;
import com.pekao.projektpekao.entity.ElectronicJournal;

import java.util.List;

public class ElectronicJournalEntityMapper {

    private ElectronicJournalEntityMapper() {
    }

    public static ElectronicJournal toElectronicJournalEntity(ElectronicJournalDto electronicJournalDto) {
        return ElectronicJournal.builder()
                .id(electronicJournalDto.getId())
                .name(electronicJournalDto.getName())
                .eventType(electronicJournalDto.getEventType())
                .user(UserEntityMapper.userEntity(electronicJournalDto.getUserDto()))
                .created(electronicJournalDto.getCreated())
                .buildNew();
    }

    public static List<ElectronicJournal> toElectronicJournalEntity(List<ElectronicJournalDto> electronicJournalDtoList) {
        return electronicJournalDtoList.stream()
                .map(ElectronicJournalEntityMapper::toElectronicJournalEntity)
                .toList();
    }

}
