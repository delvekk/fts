package com.dawid.fts.services;

import com.dawid.fts.domain.Document;
import org.apache.solr.client.solrj.impl.HttpSolrClient;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.apache.solr.common.SolrDocument;
import org.apache.solr.common.SolrDocumentList;
import org.apache.solr.common.params.MapSolrParams;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.hamcrest.Matchers.greaterThan;
import static org.hamcrest.Matchers.hasSize;
import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class DocumentServiceImplITlTest {

    @Autowired
    private DocumentServiceImpl documentService;

    @Test
    public void getAllDocuments() throws Exception{
        List<Document> solrDocuments = documentService.getAllDocuments();
        assertThat(solrDocuments, hasSize(greaterThan(0)));
    }

    @Test
    public void testWhatReturnsFlParameter() throws Exception {

        Map<String, Integer> wordsCount = documentService.countWordInDocument("\"D:\\\\solr-7.7.0\\\\example\\\\docsforsolr\\\\solr-word.pdf\"", "is this"
        );

        for(Map.Entry<String,Integer> entry : wordsCount.entrySet()){
            System.out.println(entry.getKey() + " " + entry.getValue());
        }
        assertEquals( 2, wordsCount.size());
    }


}