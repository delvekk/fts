package com.dawid.fts;

import org.apache.solr.client.solrj.impl.HttpSolrClient;
import org.apache.solr.client.solrj.impl.XMLResponseParser;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class FtsApplication {

    public static void main(String[] args) {
        SpringApplication.run(FtsApplication.class, args);
    }


    @Bean
    public HttpSolrClient httpSolrClient() {
        String urlString = "http://localhost:8983/solr";
        HttpSolrClient solr = new HttpSolrClient.Builder(urlString).build();
        return solr;
    }
}
