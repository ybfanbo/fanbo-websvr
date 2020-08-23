package com.fanbo.service;

import com.fanbo.domain.EsEntity;
import io.searchbox.client.JestClient;
import io.searchbox.client.JestResult;
import io.searchbox.core.Bulk;
import io.searchbox.core.Index;
import io.searchbox.core.Search;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EsEntityService {

    private Logger logger = LoggerFactory.getLogger(EsEntityService.class);
    @Autowired
    private JestClient jestClient;

    //保存数据到ES
    public void saveEntity(EsEntity esEntity) {
        Index index = new Index.Builder(esEntity).index(EsEntity.INDEX_NAME).type(EsEntity.TYPE).build();
        try {
            jestClient.execute(index);
            logger.info("es写入数据成功..");
        }catch (Exception e){
            e.printStackTrace();
            logger.error("es写入数据失败..");
        }
    }

    //批量保存数据到ES
    public void saveEntity(List<EsEntity> esEntityList) {
        Bulk.Builder bulk = new Bulk.Builder();
        for (EsEntity esEntity : esEntityList){
            Index index = new Index.Builder(esEntity).index(EsEntity.INDEX_NAME).type(EsEntity.TYPE).build();
            bulk.addAction(index);
        }
        try {
            jestClient.execute(bulk.build());
            logger.info("es批量写入数据成功..");
        }catch (Exception e){
            e.printStackTrace();
            logger.error("es批量写入数据失败..");
        }
    }

    //在ES中搜索数据
    public List<EsEntity> searchEntity(String searchContent) {
        SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();
        searchSourceBuilder.query(QueryBuilders.matchQuery("name", searchContent));
        Search search = new Search.Builder(searchSourceBuilder.toString()).addIndex(EsEntity.INDEX_NAME).addType(EsEntity.TYPE).build();

        try {
            JestResult result = jestClient.execute(search);
            return result.getSourceAsObjectList(EsEntity.class);
        }catch (Exception e){
            e.printStackTrace();
            logger.error("搜索出错..");
        }

        return null;
    }
}
