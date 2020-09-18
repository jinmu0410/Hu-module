package com.hjb.config;

import org.apache.http.HttpHost;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestHighLevelClient;

import java.io.IOException;

public class ElasticConfig {

    private static final String HOST = "127.0.0.1";
    private static final int PORT = 9200;
    private static final String SCHEMA = "http";
    private static RestHighLevelClient restHighLevelClient;

    static {
        init();
    }

    public static void init(){
        if(restHighLevelClient == null) {
            restHighLevelClient = new RestHighLevelClient(
                    //集群模式下(new HttpHost(HOST, PORT, SCHEMA),new HttpHost(),...);
                    RestClient.builder(new HttpHost(HOST, PORT, SCHEMA)));
        }
    }

    public static RestHighLevelClient client(){
        return restHighLevelClient;
    }

    public static void close() {
        if (restHighLevelClient != null) {
            try {
                restHighLevelClient.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
