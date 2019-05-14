package com.dawid.fts.services;

import com.dawid.fts.domain.Document;
import org.apache.solr.client.solrj.impl.HttpSolrClient;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.hamcrest.Matchers;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class DocumentServiceImplTest {

    @Mock
    private HttpSolrClient client;
    @InjectMocks
    private DocumentServiceImpl documentService;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void checkIfReturnProperDocuments() throws Exception {
        // given
        Document document = new Document();
        List<Document> documentList = new ArrayList<>();
        documentList.add(document);
        QueryResponse response = Mockito.mock(QueryResponse.class);
        Mockito.when(client.query(Mockito.anyString(), Mockito.any())).thenReturn(response);
        Mockito.when(response.getBeans(Document.class)).thenReturn(documentList);

        // when
        List<Document> resultAuthor = documentService.getDocumentsByAuthor("Author");
        List<Document> resultWords = documentService.getDocumentsByWords("words");
        List<Document> resultPhrase = documentService.getDocumentsByPhrase("phrase");
        List<Document> resultDate = documentService.getDocumentsByYear("2018", "2018");

        // given
        assertThat(resultAuthor, Matchers.hasSize(1));
        assertThat(resultWords, Matchers.hasSize(1));
        assertThat(resultPhrase, Matchers.hasSize(1));
        assertThat(resultDate, Matchers.hasSize(1));
    }
}