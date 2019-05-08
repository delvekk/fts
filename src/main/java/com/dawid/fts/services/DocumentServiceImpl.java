package com.dawid.fts.services;

import com.dawid.fts.domain.Document;
import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.client.solrj.impl.HttpSolrClient;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;

@Service
public class DocumentServiceImpl implements DocumentService {

    private final HttpSolrClient client;

    public DocumentServiceImpl(HttpSolrClient client) {
        this.client = client;
    }

    @Override
    public List<Document> getAllDocuments() throws SolrServerException, IOException {
        SolrQuery query = new SolrQuery();
        query.set("q", "*:*");
        QueryResponse response = client.query("documents", query);
        List<Document> documents = response.getBeans(Document.class);
        return documents;
    }
}
