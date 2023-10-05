package com.baraq.merchantsystem.dao;

import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;

import java.util.Collections;
import java.util.Properties;

public class Consumer {
    static String bootstrapServers = "https://cell-1.streaming.me-jeddah-1.oci.oraclecloud.com:9092"; // usually of the form cell-1.streaming.<region>.oci.oraclecloud.com:9092 ;
    static String tenancyName = "tallyfinancialservicespoc";
    static String username = "skumar@baraq.com";
    static String streamPoolOCIId = "ocid1.streampool.oc1.me-jeddah-1.amaaaaaa5zfdd6yad4ejm3nlxkj5vuewnop4qqylirezefw5c7bhvelciawa";
    static String authToken = "75jNk{KiilmNBzV5BRT)"; // from step 8 of Prerequisites section
    static String streamOrKafkaTopicName = "authapp-1"; // from step 2 of Prerequisites section
    static String consumerGroupName = "baraq-1";

    private static Properties getKafkaProperties(){
        Properties props = new Properties();
        props.put("bootstrap.servers", bootstrapServers);
        props.put("group.id", consumerGroupName);
        props.put("enable.auto.commit", "false");
        props.put("session.timeout.ms", "30000");
        props.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG,
                "org.apache.kafka.common.serialization.StringDeserializer");
        props.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG,
                "org.apache.kafka.common.serialization.StringDeserializer");
        props.put("security.protocol", "SASL_SSL");
        props.put("sasl.mechanism", "PLAIN");
        props.put("auto.offset.reset", "earliest");
        final String value = "org.apache.kafka.common.security.plain.PlainLoginModule required username=\""
                + tenancyName + "/"
                + username + "/"
                + streamPoolOCIId + "\" "
                + "password=\""
                + authToken + "\";";
        props.put("sasl.jaas.config", value);
        return props;
    }

    public static void main(String[] args) {
        final KafkaConsumer<Integer, String> consumer = new KafkaConsumer<>(getKafkaProperties());;
        consumer.subscribe(Collections.singletonList(streamOrKafkaTopicName));
        ConsumerRecords<Integer, String> records = consumer.poll(10000);

        System.out.println("size of records polled is "+ records.count());
        for (ConsumerRecord<Integer, String> record : records) {
            System.out.println("Received message: (" + record.key() + ", " + record.value() + ") at offset " + record.offset());
        }

        consumer.commitSync();
        consumer.close();
    }
}
