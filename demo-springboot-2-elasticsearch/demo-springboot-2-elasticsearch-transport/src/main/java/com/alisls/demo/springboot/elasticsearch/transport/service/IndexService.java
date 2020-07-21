package com.alisls.demo.springboot.elasticsearch.transport.service;

import com.alisls.demo.springboot.elasticsearch.transport.config.Settings;
import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.index.IndexWriterConfig;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.nio.file.Paths;

/**
 * 索引服务
 *
 * @author Ke Wang
 */
@Service("indexService")
public class IndexService {

    /**
     * 创建索引
     *
     * @param indexName
     */
    public void createIndex(String indexName) {
        IndexWriter writer = null;
        try {
            String indexDir = Settings.DATA_DIR + "\\" + indexName;
            //准备目录
            Directory directory = FSDirectory.open(Paths.get((indexDir)));
            //准备分词器
            Analyzer analyzer = new StandardAnalyzer();
            //准备config
            IndexWriterConfig iwConfig = new IndexWriterConfig(analyzer);
            //创建索引的实例
            writer = new IndexWriter(directory, iwConfig);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (writer != null) {
                try {
                    writer.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public void deleteIndex() {
        throw new UnsupportedOperationException();
    }
}
