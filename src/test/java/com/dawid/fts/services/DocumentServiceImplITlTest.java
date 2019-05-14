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

        SolrDocumentList solrDocuments = documentService.countWordsInDocument("\"D:\\\\solr-7.7.0\\\\example\\\\docsforsolr\\\\solr-word.pdf\"", "is"
        );

        for(SolrDocument document : solrDocuments) {
            System.out.println(document.getFieldValue("termfreq(text,is)"));
        }


        assertThat(solrDocuments, hasSize(1));

//        final Map<String, String> queryParamMap = new HashMap<String, String>();
//        queryParamMap.put("q", "id:\"D:\\\\solr-7.7.0\\example\\\\docsforsolr\\\\solr-word.pdf\"");
//        queryParamMap.put("fl", "termfreq(text,'is')");
//        MapSolrParams queryParams = new MapSolrParams(queryParamMap);
//
//        final QueryResponse response = client.query("documents4", queryParams);
//        final SolrDocumentList documents = response.getResults();
//
//        for(SolrDocument document : documents) {
//            assertTrue(document.getFieldNames().contains("id"));
//            assertTrue(document.getFieldNames().contains("name"));
//        }

    }


}