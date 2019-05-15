package com.dawid.fts.dto;

import lombok.Data;

import java.util.Map;

@Data
public class DocumentDTO {

    private String year;
    private String title;
    private String author;
    private Map<String, Integer> wordsCount;

}
