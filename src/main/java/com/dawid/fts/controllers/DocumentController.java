package com.dawid.fts.controllers;

import com.dawid.fts.domain.Document;
import com.dawid.fts.services.DocumentService;
import org.apache.solr.client.solrj.SolrServerException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/documents")
public class DocumentController {

    private final DocumentService documentService;

    public DocumentController(DocumentService documentService) {
        this.documentService = documentService;
    }

    @GetMapping("/getAll")
    public List<Document> getAllDocuments() {
        try {
            List<Document> documents = documentService.getAllDocuments();
            return documents;
        } catch (IOException | SolrServerException e) {
            return null;
        }
    }
}
