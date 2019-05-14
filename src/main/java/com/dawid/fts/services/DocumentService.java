package com.dawid.fts.services;

import com.dawid.fts.domain.Document;
import org.apache.solr.client.solrj.SolrServerException;

import java.io.IOException;
import java.util.List;

public interface DocumentService {

    List<Document> getAllDocuments() throws SolrServerException, IOException;

    List<Document> getDocumentsByAuthor(String authorName) throws SolrServerException, IOException;

    List<Document> getDocumentsByYear(String from, String to) throws SolrServerException, IOException;

    List<Document> getDocumentsByWords(String words) throws SolrServerException, IOException;

    List<Document> getDocumentsByPhrase(String phrase) throws SolrServerException, IOException;

    List<Document> getDocumentsByAuthorAndDate(String authorName, String from, String to) throws SolrServerException, IOException;

    List<Document> getDocumentsByAllParams(String authorName, String from, String to, String words) throws SolrServerException, IOException;
}
