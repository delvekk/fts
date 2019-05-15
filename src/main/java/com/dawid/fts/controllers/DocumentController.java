package com.dawid.fts.controllers;


import com.dawid.fts.domain.Document;
import com.dawid.fts.services.DocumentService;
import org.apache.solr.client.solrj.SolrServerException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.io.IOException;

@Controller
public class DocumentController {

    private final DocumentService documentService;

    public DocumentController(DocumentService documentService) {
        this.documentService = documentService;
    }

    @GetMapping("/start")
    public String getIndexPage(Model model) {
        return "index";
    }

    @GetMapping("/search")
    public String getDocumentsByAllParams(@RequestParam("author") String author,
                                          @RequestParam("from") String from,
                                          @RequestParam("to") String to,
                                          @RequestParam("words") String words,
                                          Model model) {
        try{
            model.addAttribute("documents", documentService.getDocumentsByAllParams(author, from, to, words));
        } catch (IOException | SolrServerException e) {
            System.out.println("Something went wrong");
        }
        return "search/results";
    }
}
