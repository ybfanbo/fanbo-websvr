package com.fanbo.test;

import org.elasticsearch.action.bulk.BulkRequestBuilder;
import org.elasticsearch.client.Client;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.settings.ImmutableSettings;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.transport.InetSocketTransportAddress;
import org.junit.Test;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

/**
 * Created by fanbo on 2016/10/1.
 */
public class Elasticsearch_Junit {

    @Test
    public void importDateTest() {
        Settings settings = ImmutableSettings.settingsBuilder()
                .put("cluster.name", "HS_INDEX_SERVER").build();
//        Client client = new TransportClient(settings)
//                .addTransportAddress(new InetSocketTransportAddress(
//                        "localhost", 9300));
        try {
            // 读取刚才导出的ES数据
            BufferedReader br = new BufferedReader(new FileReader("d:\\es-test-data.txt"));
            String json = null;
            int count = 0;
            // 开启批量插入
//            BulkRequestBuilder bulkRequest = client.prepareBulk();
//            while ((json = br.readLine()) != null) {
//                bulkRequest.add(client.prepareIndex("bigdata", "student")
//                        .setSource(json));
//                 每一千条提交一次
//                if (count % 1000 == 0) {
//                    bulkRequest.execute().actionGet();
//                    System.out.println("提交了：" + count);
//                }
//                count++;
//            }
//            bulkRequest.execute().actionGet();
//            System.out.println("插入完毕");
//            br.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

}
