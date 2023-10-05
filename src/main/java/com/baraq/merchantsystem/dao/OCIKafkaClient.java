//package com.baraq.merchantsystem.dao;
//
//import com.oracle.bmc.streaming.Stream;
//import org.apache.kafka.clients.producer.*;
//import com.oracle.bmc.auth.*;
//import com.oracle.bmc.auth.internal.*;
//import com.oracle.bmc.streaming.*;
//import com.oracle.bmc.streaming.model.*;
//import com.oracle.bmc.streaming.requests.*;
//import com.oracle.bmc.streaming.responses.*;
//import java.util.Properties;
//
//public class OCIKafkaClient {
//    public static void main(String[] args) {
//        // OCI Streaming configuration
//        String streamOcid = "";
//        String region = "YOUR_STREAM_REGION";
//        String compartmentId = "YOUR_COMPARTMENT_OCID";
//
//        // Kafka producer configuration
//        Properties props = new Properties();
//        props.put("bootstrap.servers", "YOUR_KAFKA_BROKER");
//        props.put("key.serializer", "org.apache.kafka.common.serialization.StringSerializer");
//        props.put("value.serializer", "org.apache.kafka.common.serialization.StringSerializer");
//
//        // Create OCI Streaming client
//        AuthenticationDetailsProvider authProvider = new ResourcePrincipalAuthenticationDetailsProvider.Builder().build();
//        StreamingClient streamingClient = new StreamingClient(authProvider);
//        streamingClient.setRegion(Region.fromRegionId(region));
//
//        // Create Kafka producer
//        Producer<String, String> producer = new KafkaProducer<>(props);
//
//        // Produce messages to Kafka topic
//        try {
//            ListStreamsResponse listStreamsResponse = streamingClient.listStreams(
//                    ListStreamsRequest.builder()
//                            .compartmentId(compartmentId)
//                            .build()
//            );
//            Stream stream = listStreamsResponse.getItems().stream()
//                    .filter(s -> s.getId().equals(streamOcid))
//                    .findFirst()
//                    .orElse(null);
//
//            if (stream != null) {
//                ProducerRecord<String, String> record = new ProducerRecord<>("YOUR_KAFKA_TOPIC", null, "MessagePayload");
//
//                // Send the message to Kafka
//                producer.send(record, new Callback() {
//                    public void onCompletion(RecordMetadata metadata, Exception e) {
//                        if (e != null) {
//                            e.printStackTrace();
//                        } else {
//                            System.out.printf("Sent message to Kafka topic %s at partition %d offset %d%n",
//                                    metadata.topic(), metadata.partition(), metadata.offset());
//                        }
//                    }
//                });
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//        } finally {
//            producer.close();
//            streamingClient.close();
//        }
//    }
//}
//
