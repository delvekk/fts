package com.dawid.fts.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.apache.solr.client.solrj.beans.Field;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

@Data
@AllArgsConstructor
@RequiredArgsConstructor
public class Document {

    @Field
    private String id;
    @Field
    private List<String> content;
    @Field(value = "last_modified")
    private Date date;
    @Field
    private List<String> title;
    @Field
    private String author;
}
