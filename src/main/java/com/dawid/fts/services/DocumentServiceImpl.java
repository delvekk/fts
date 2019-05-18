package com.dawid.fts.services;

import com.dawid.fts.converters.DocumentToDocumentDTO;
import com.dawid.fts.domain.Document;
import com.dawid.fts.dto.DocumentDTO;
import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.client.solrj.impl.HttpSolrClient;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.apache.solr.common.SolrDocument;
import org.apache.solr.common.SolrDocumentList;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.time.Year;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class DocumentServiceImpl implements DocumentService {

    private final HttpSolrClient client;
    private final DocumentToDocumentDTO documentToDocumentDTO;
    private static final String COLLECTION = "documents4";

    public DocumentServiceImpl(HttpSolrClient client, DocumentToDocumentDTO documentToDocumentDTO) {
        this.client = client;
        this.documentToDocumentDTO = documentToDocumentDTO;
    }

    @Override
    public List<Document> getAllDocuments() throws SolrServerException, IOException {
        return qDoQuery("*:*");
    }

    @Override
    public List<Document> getDocumentsByAuthor(String authorName) throws SolrServerException, IOException {
        return qDoQuery("author:" + authorName + "~");
    }

    @Override
    public List<Document> getDocumentsByYear(String from, String to) throws SolrServerException, IOException {
        int yearNow = Year.now().getValue();
        int toYear = Integer.valueOf(to);
        if (yearNow == toYear) {
            return qDoQuery("last_modified:" + "[" + from + "-01-01T00:00:00Z TO NOW]");
        }
        return qDoQuery("last_modified:" + "[" + from + "-01-01T00:00:00Z TO " + to + "-12-31T00:00:00Z]");
    }

    @Override
    public List<Document> getDocumentsByWords(String words) throws SolrServerException, IOException {
        return qDoQuery("text:" + words);
    }

    @Override
    public List<Document> getDocumentsByPhrase(String phrase) throws SolrServerException, IOException {
        return qDoQuery("text:\"" + phrase + "\"");
    }

    @Override
    public List<Document> getDocumentsByAuthorAndDate(String authorName, String from, String to) throws SolrServerException, IOException {
        int yearNow = Year.now().getValue();
        int toYear = Integer.valueOf(to);
        if (yearNow == toYear) {
            return qDoQuery("last_modified:[" + from + "-01-01T00:00:00Z TO NOW] AND author:" + authorName);
        }
        return qDoQuery("last_modified:[" + from + "-01-01T00:00:00Z TO " + to + "-12-31T00:00:00Z]" +
                " AND author:" + authorName);
    }

    @Override
    public List<DocumentDTO> getDocumentsByAllParams(String authorName, Integer from, Integer to, String words) throws SolrServerException, IOException {
        String query = "author:" + authorName + " AND last_modified:[" + from + "-01-01T00:00:00Z TO " + to + "-12-31T00:00:00Z]" +
                " AND text:" + words;
        List<Document> foundDocuments =  qDoQuery(query);
        List<DocumentDTO> documentDTOS = new ArrayList<>();
        for(Document document : foundDocuments) {
            DocumentDTO documentDTO = documentToDocumentDTO.convert(document);
            documentDTO.setWordsCount(countWordInDocument(document.getId(), words));
            documentDTOS.add(documentDTO);
        }
        return documentDTOS;
    }

    @Override
     public Map<String, Integer> countWordInDocument(String docId, String words) throws SolrServerException, IOException {
        final Map<String, Integer> wordsCount = new HashMap<>();
        if(words.equals("*")){
            return wordsCount;
        }
        SolrQuery query = new SolrQuery();
        String[] wordsArray = words.split(" ");
        docId = docId.replace("\\", "\\\\");
        for(String word : wordsArray) {
            query.set("fl", "termfreq(text," + word + ")");
            query.set("q", "id:\"" + docId + "\"");
            QueryResponse response = client.query("documents4", query);
            SolrDocumentList documents = response.getResults();
            for(SolrDocument document : documents) {
                Integer count = (Integer) document.getFieldValue("termfreq(text," + word + ")");
                wordsCount.put(word, count);
            }
        }
        return wordsCount;
    }

    private List<Document> qDoQuery(String queryString) throws SolrServerException, IOException {
        SolrQuery query = new SolrQuery();
        query.set("q", queryString);
        QueryResponse response = client.query(COLLECTION, query);
        return response.getBeans(Document.class);
    }
}
