package com.dawid.fts.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.util.Map;

@Data
@AllArgsConstructor
@RequiredArgsConstructor
public class DocumentDTO {

    private String id;
    private Integer year;
    private String title;
    private String author;
    private Map<String, Integer> wordsCount;
    private boolean containsPhrase;

}
