package com.baraq.merchantsystem.controller;

import com.baraq.merchantsystem.model.Response;
import org.apache.avro.Schema;
import org.apache.avro.generic.GenericRecord;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import sa.com.barraq.sharedlibs.Consumer;
import sa.com.barraq.sharedlibs.KafkaClient;
import sa.com.barraq.sharedlibs.Producer;
import sa.com.barraq.sharedlibs.Utilities;

import java.io.File;
import java.io.IOException;
import java.util.*;

@RestController
@RequestMapping("/business")
public class Merchant {

    private static final Logger logger = LogManager.getLogger(Merchant.class);

    String testData = "abcd";
    @RequestMapping(value = "/merchant", method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity<Response> createMerchant(@RequestParam("test") String test) throws IOException, InterruptedException {
        Response response = new Response();
        if(test.equals(testData)){
            response.setMessage("Successfully Matched");
            response.setStatus_code("1");
//            testProducer();
//            testConsumer();
            return new ResponseEntity<>(response, HttpStatus.OK);
        }else{
            response.setMessage("Not Matched");
            response.setStatus_code("2");
            return new ResponseEntity<>(response, HttpStatus.OK);
        }
    }

    public static void main(String[] args) throws IOException {
        String topicName = "testTopic";
        String topicName1 = "testTopic1";

        String schemaPath = "/Users/saurabhkumar/personal/repository/postman-test-automation/src/main/resources/avroSchema/people.avsc";
        String bootstrapServers = "https://cell-1.streaming.me-jeddah-1.oci.oraclecloud.com:9092"; // usually of the form cell-1.streaming.<region>.oci.oraclecloud.com:9092 ;

        Map<String,String> configuration = new HashMap<>();
        configuration.put("bootstrap.servers",bootstrapServers);
        configuration.put("group.id ","baraq");
        configuration.put("security.protocol", "SASL_SSL");
        configuration.put("sasl.mechanism", "PLAIN");
        configuration.put("auto.offset.reset", "earliest");
        configuration.put("enable.auto.commit", "false");
        configuration.put("session.timeout.ms", "30000");

        String tenancyName = "tallyfinancialservicespoc";
        String username = "skumar@baraq.com";
        String streamPoolOCIId = "ocid1.streampool.oc1.me-jeddah-1.amaaaaaa5zfdd6yad4ejm3nlxkj5vuewnop4qqylirezefw5c7bhvelciawa";
        String authToken = "75jNk{KiilmNBzV5BRT)"; // from step 8 of Prerequisites section
        String streamOrKafkaTopicName = "authapp-1"; // from step 2 of Prerequisites section

        final String value = "org.apache.kafka.common.security.plain.PlainLoginModule required username=\""
                + tenancyName + "/"
                + username + "/"
                + streamPoolOCIId + "\" "
                + "password=\""
                + authToken + "\";";
        configuration.put("sasl.jaas.config", value);

        Consumer consumer = KafkaClient.buildConsumer(configuration);
        Schema schema = new Schema.Parser().parse(new File(schemaPath));
        List<String> listOfMessage = new ArrayList<>();

        consumer.consume(streamOrKafkaTopicName,schema,record ->{
            logger.info("Test Message: "+record.toString());
            logger.debug("Debug Test Message: "+record.toString());
        });

    }

//    public static void main(String[] args) throws IOException, InterruptedException {
//        String topicName = "testTopic";
//        String schemaPath = "/Users/saurabhkumar/personal/repository/postman-test-automation/src/main/resources/avroSchema/people.avsc";
//
//         String bootstrapServers = "https://cell-1.streaming.me-jeddah-1.oci.oraclecloud.com:9092"; // usually of the form cell-1.streaming.<region>.oci.oraclecloud.com:9092 ;
//         String tenancyName = "tallyfinancialservicespoc";
//         String username = "skumar@baraq.com";
//         String streamPoolOCIId = "ocid1.streampool.oc1.me-jeddah-1.amaaaaaa5zfdd6yad4ejm3nlxkj5vuewnop4qqylirezefw5c7bhvelciawa";
//         String authToken = "75jNk{KiilmNBzV5BRT)"; // from step 8 of Prerequisites section
//         String streamOrKafkaTopicName = "authapp-1"; // from step 2 of Prerequisites section
//
//        Map<String, String> configuration = new HashMap<>();
//        configuration.put("bootstrap.servers", bootstrapServers);
//        configuration.put("security.protocol", "SASL_SSL");
//        configuration.put("sasl.mechanism", "PLAIN");
//        configuration.put("tenet_id", tenancyName);
//        configuration.put("username", username);
//        configuration.put("streamPoolOCIId", streamPoolOCIId);
//        configuration.put("authToken", authToken);
//        final String value = "org.apache.kafka.common.security.plain.PlainLoginModule required username=\""
//                + tenancyName + "/"
//                + username + "/"
//                + streamPoolOCIId + "\" "
//                + "password=\""
//                + authToken + "\";";
//
//        configuration.put("sasl.jaas.config", value);
//        Producer producer = KafkaClient.buildProducer(configuration);
//        Schema schema = new Schema.Parser().parse(new File(schemaPath));
//
//        GenericRecord record = new sa.com.barraq.sharedlibs.AvroRecordBuilder(schema)
//                .setField("name", "Saurabh")
//                .setField("age", 32)
//                .build();
//        while(true){
//            Thread.sleep(1000);
//            producer.produceMessage(Utilities.recordSerialiser(schema, record), streamOrKafkaTopicName);
//        }
//    }

//    public static void main(String[] args){
//        props.put("security.protocol", "SASL_SSL");
//        props.put("sasl.mechanism", "PLAIN");
//        props.put("auto.offset.reset", "earliest");
//
//        final String value = "org.apache.kafka.common.security.plain.PlainLoginModule required username=\""
//                + tenancyName + "/"
//                + username + "/"
//                + streamPoolOCIId + "\" "
//                + "password=\""
//                + authToken + "\";";
//        props.put("sasl.jaas.config", value);
//    }
}
