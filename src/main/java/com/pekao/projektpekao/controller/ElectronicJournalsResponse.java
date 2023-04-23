package com.pekao.projektpekao.controller;

import lombok.Builder;
import lombok.Getter;

import java.util.List;
@Builder
@Getter
public class ElectronicJournalsResponse {

    private List<ElectronicJournalDto> electronicJournalResponseList;
}
