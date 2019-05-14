package com.dawid.fts.services;

import com.dawid.fts.domain.Document;
import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.client.solrj.impl.HttpSolrClient;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.apache.solr.common.SolrDocumentList;
import org.apache.solr.common.params.MapSolrParams;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.time.Year;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class DocumentServiceImpl implements DocumentService {

    private final HttpSolrClient client;
    private static final String COLLECTION = "documents4";

    public DocumentServiceImpl(HttpSolrClient client) {
        this.client = client;
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
    public List<Document> getDocumentsByAllParams(String authorName, String from, String to, String words) throws SolrServerException, IOException {
        String query = "author:" + authorName + " AND last_modified:[" + from + "-01-01T00:00:00Z TO " + to + "-12-31T00:00:00Z]" +
                " AND text:" + words;
        return qDoQuery(query);
    }

    public SolrDocumentList countWordsInDocument(String docId, String word) throws SolrServerException, IOException {
        final Map<String, String> queryParamMap = new HashMap<>();
        queryParamMap.put("q", "id:" + docId);
        queryParamMap.put("fl", "termfreq(text," + word + ")");
        MapSolrParams queryParams = new MapSolrParams(queryParamMap);

        QueryResponse response = client.query("documents4", queryParams);
        SolrDocumentList documents = response.getResults();

        return documents;
    }

    private List<Document> qDoQuery(String queryString) throws SolrServerException, IOException {
        SolrQuery query = new SolrQuery();
        query.set("q", queryString);
        QueryResponse response = client.query(COLLECTION, query);
        return response.getBeans(Document.class);
    }
}
