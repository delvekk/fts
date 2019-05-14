//package com.dawid.fts.controllers;
//
//import com.dawid.fts.domain.Document;
//import com.dawid.fts.services.DocumentService;
//import org.apache.solr.client.solrj.SolrServerException;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RequestParam;
//import org.springframework.web.bind.annotation.RestController;
//
//import java.io.IOException;
//import java.util.List;
//
//@RestController
//@RequestMapping("/documents")
//public class DocumentRESTController {
//
//    private final DocumentService documentService;
//
//    public DocumentRESTController(DocumentService documentService) {
//        this.documentService = documentService;
//    }
//
//    @GetMapping("/getAll")
//    public ResponseEntity<List<Document>> getAllDocuments() {
//        try {
//            List<Document> documents = documentService.getAllDocuments();
//            return new ResponseEntity<>(documents, HttpStatus.OK);
//        } catch (IOException | SolrServerException e) {
//            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
//        }
//    }
//
//    @GetMapping(params = "author")
//    public ResponseEntity<List<Document>> getDocumentsByAuthor(@RequestParam("author") String author) {
//        try {
//            List<Document> documents = documentService.getDocumentsByAuthor(author);
//            return new ResponseEntity<>(documents, HttpStatus.OK);
//        } catch (IOException | SolrServerException e) {
//            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
//        }
//    }
//
//    @GetMapping(params = {"from","to"})
//    public ResponseEntity<List<Document>> getDocumentsByDate(@RequestParam("from") String from, @RequestParam("to") String to) {
//        try {
//            List<Document> documents = documentService.getDocumentsByYear(from, to);
//            return new ResponseEntity<>(documents, HttpStatus.OK);
//        } catch (IOException | SolrServerException e) {
//            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
//        }
//    }
//
//    @GetMapping(params = "words")
//    public ResponseEntity<List<Document>> getDocumentsByWords(@RequestParam("words") String words) {
//        try {
//            List<Document> documents = documentService.getDocumentsByWords(words);
//            return new ResponseEntity<>(documents, HttpStatus.OK);
//        } catch (IOException | SolrServerException e) {
//            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
//        }
//    }
//
//    @GetMapping(params = "phrase")
//    public ResponseEntity<List<Document>> getDocumentsByPhrase(@RequestParam("phrase") String phrase) {
//        try {
//            List<Document> documents = documentService.getDocumentsByPhrase(phrase);
//            return new ResponseEntity<>(documents, HttpStatus.OK);
//        } catch (IOException | SolrServerException e) {
//            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
//        }
//    }
//
//    @GetMapping(params = {"author", "from", "to"})
//    public ResponseEntity<List<Document>> getDocumentsByAuthorAndDate(@RequestParam("author") String author,
//                                                                      @RequestParam("from") String from,
//                                                                      @RequestParam("to") String to) {
//        try {
//            List<Document> documents = documentService.getDocumentsByAuthorAndDate(author, from, to);
//            return new ResponseEntity<>(documents, HttpStatus.OK);
//        } catch (IOException | SolrServerException e) {
//            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
//        }
//    }
//
////    @GetMapping
////    public ResponseEntity<List<Document>> getDocumentsByAllParams(@RequestParam("author") String author,
////                                                                  @RequestParam("from") String from,
////                                                                  @RequestParam("to") String to,
////                                                                  @RequestParam("words") String words) {
////
////
////    }
//}
