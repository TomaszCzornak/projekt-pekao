package com.pekao.projektpekao.controller.ElectronicJournal;


import com.pekao.projektpekao.controller.Users.UserDto;
import com.pekao.projektpekao.domain.ElectronicJournal.ElectronicJournal;

import java.text.SimpleDateFormat;
import java.util.Date;

public class ElectronicJournalDto {

    private Long id;
    private String created = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss z")
            .format(new Date());
    private String name;
    private UserDto userDto;
    private ElectronicJournal.EventType eventType;

    protected ElectronicJournalDto() {
    }

    public Long getId() {
        return id;
    }

    public String getCreated() {
        return created;
    }

    public String getName() {
        return name;
    }

    public UserDto getUserDto() {
        return userDto;
    }

    public ElectronicJournal.EventType getEventType() {
        return eventType;
    }

    public static ElectronicJournalDto.Builder builder() {
        return new ElectronicJournalDto.Builder();
    }

    public static final class Builder {
        private Long id;
        private String created;
        private String name;
        private UserDto userDto;
        private ElectronicJournal.EventType eventType;

        private Builder() {
        }
        public Builder id(Long id) {
            this.id = id;
            return this;
        }

        public Builder created(String created) {
            this.created = created;
            return this;
        }

        public Builder name(String name) {
            this.name = name;
            return this;
        }

        public Builder userDto(UserDto userDto) {
            this.userDto = userDto;
            return this;
        }

        public Builder eventType(ElectronicJournal.EventType eventType) {
            this.eventType = eventType;
            return this;
        }

        public ElectronicJournalDto build() {
            ElectronicJournalDto electronicJournalDto = new ElectronicJournalDto();
            electronicJournalDto.id = this.id;
            electronicJournalDto.userDto = this.userDto;
            electronicJournalDto.eventType = this.eventType;
            electronicJournalDto.created = this.created;
            electronicJournalDto.name = this.name;
            return electronicJournalDto;
        }
    }

}
