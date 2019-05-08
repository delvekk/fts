package com.dawid.fts.services;

import com.dawid.fts.domain.Document;
import org.apache.solr.client.solrj.SolrServerException;

import java.io.IOException;
import java.util.List;

public interface DocumentService {

    List<Document> getAllDocuments() throws SolrServerException, IOException;
}
