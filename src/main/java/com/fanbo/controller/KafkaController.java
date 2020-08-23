package com.fanbo.controller;

import com.fanbo.constant.KafkaConfConstants;
import com.fanbo.model.basemodel.Response;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.clients.producer.Callback;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.clients.producer.RecordMetadata;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.apache.kafka.common.serialization.StringSerializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.Properties;

@RestController
@RequestMapping(value = "/kafka")
public class KafkaController {

    private Logger logger = LoggerFactory.getLogger(KafkaController.class);

    @RequestMapping("/productTest")
    public Response productTest() {
        Response result = new Response();
        try {
            Properties props = new Properties();
            props.put("bootstrap.servers", KafkaConfConstants.MQ_ADDRESS_COLLECTION);
            props.put("acks", "all");
            props.put("retries", 0);
            props.put("batch.size", 16384);
            props.put("key.serializer", StringSerializer.class.getName());
            props.put("value.serializer", StringSerializer.class.getName());

            KafkaProducer<String, String> producer = new KafkaProducer<String, String>(props);

            //消息实体
            ProducerRecord<String, String> record = null;
            for (int i = 0; i < 2; i++) {
                record = new ProducerRecord<String, String>(KafkaConfConstants.PRODUCER_TOPIC, "value" + i);
                //发送消息
                producer.send(record, new Callback() {
                    @Override
                    public void onCompletion(RecordMetadata recordMetadata, Exception e) {
                        if (null != e) {
                            logger.info("send error" + e.getMessage());
                        } else {
                            System.out.println(String.format("offset:%s,partition:%s", recordMetadata.offset(), recordMetadata.partition()));
                        }
                    }
                });
            }
            producer.close();

            return result;
        } catch (Exception e) {
            e.printStackTrace();
            result.setMessage("服务异常");
            result.setStatus(0);
            return result;
        }

    }

    @RequestMapping("/consumerTest")
    public Response consumerTest() {
        Response result = new Response();
        try {
            Properties props = new Properties();
            props.put("bootstrap.servers", KafkaConfConstants.MQ_ADDRESS_COLLECTION);
            props.put("group.id", KafkaConfConstants.CONSUMER_GROUP_ID);
            props.put("enable.auto.commit", KafkaConfConstants.CONSUMER_ENABLE_AUTO_COMMIT);
            props.put("auto.commit.interval.ms", KafkaConfConstants.CONSUMER_AUTO_COMMIT_INTERVAL_MS);
            props.put("session.timeout.ms", KafkaConfConstants.CONSUMER_SESSION_TIMEOUT_MS);
            props.put("max.poll.records", KafkaConfConstants.CONSUMER_MAX_POLL_RECORDS);
            props.put("auto.offset.reset", "earliest");
            props.put("key.deserializer", StringDeserializer.class.getName());
            props.put("value.deserializer", StringDeserializer.class.getName());

            KafkaConsumer<String, String> consumer = new KafkaConsumer<String, String>(props);
            consumer.subscribe(Arrays.asList(KafkaConfConstants.CONSUMER_TOPIC));

            while (true) {
                ConsumerRecords<String, String> records = consumer.poll(KafkaConfConstants.CONSUMER_POLL_TIME_OUT);
                records.forEach((ConsumerRecord<String, String> record) -> {
                    logger.info("revice: key ===" + record.key() + " value ====" + record.value() + " topic ===" + record.topic());
                });
            }

        } catch (Exception e) {
            e.printStackTrace();
            result.setMessage("服务异常");
            result.setStatus(0);
        }
        return result;

    }

}
