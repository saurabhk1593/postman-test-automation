package com.baraq.merchantsystem.dao;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.common.serialization.StringSerializer;

import java.sql.Time;
import java.util.Properties;

public class Producer {

    static String bootstrapServers = "https://cell-1.streaming.me-jeddah-1.oci.oraclecloud.com:9092"; // usually of the form cell-1.streaming.<region>.oci.oraclecloud.com:9092 ;
    static String tenancyName = "tallyfinancialservicespoc";
    static String username = "skumar@baraq.com";
    static String streamPoolOCIId = "ocid1.streampool.oc1.me-jeddah-1.amaaaaaa5zfdd6yad4ejm3nlxkj5vuewnop4qqylirezefw5c7bhvelciawa";
    static String authToken = "75jNk{KiilmNBzV5BRT)"; // from step 8 of Prerequisites section
    static String streamOrKafkaTopicName = "authapp-1"; // from step 2 of Prerequisites section

    private static Properties getKafkaProperties() {
        Properties properties = new Properties();
        properties.put("bootstrap.servers", bootstrapServers);
        properties.put("security.protocol", "SASL_SSL");
        properties.put("sasl.mechanism", "PLAIN");
        properties.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());
        properties.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());

        final String value = "org.apache.kafka.common.security.plain.PlainLoginModule required username=\""
                + tenancyName + "/"
                + username + "/"
                + streamPoolOCIId + "\" "
                + "password=\""
                + authToken + "\";";
        properties.put("sasl.jaas.config", value);
        properties.put("retries", 3); // retries on transient errors and load balancing disconnection
        properties.put("max.request.size", 1024 * 1024); // limit request size to 1MB
        return properties;
    }

    public static void main(String args[]) {
        try {
            Properties properties = getKafkaProperties();
            KafkaProducer producer = new KafkaProducer<>(properties);
            int i = 0;
            while (true){
                Thread.sleep(1000);
                ProducerRecord<String, String> record = new ProducerRecord<>(streamOrKafkaTopicName, "messageValue" + i++);
                producer.send(record, (md, ex) -> {
                    if (ex != null) {
                        System.err.println("exception occurred in producer for review :" + record.value()
                                + ", exception is " + ex);
                        ex.printStackTrace();
                    } else {
                        System.err.println("Sent msg to " + md.partition() + " with offset " + md.offset() + " at " + md.timestamp());
                    }
                });
                producer.flush();
            }
            // producer.send() is async, to make sure all messages are sent we use producer.flush()
        } catch (Exception e) {
            System.err.println("Error: exception " + e);
        }
    }
}
