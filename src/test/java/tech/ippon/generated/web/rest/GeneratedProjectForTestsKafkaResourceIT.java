package tech.ippon.generated.web.rest;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.testcontainers.containers.KafkaContainer;
import tech.ippon.generated.config.KafkaProperties;

import java.time.Duration;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

public class GeneratedProjectForTestsKafkaResourceIT {

    private static boolean started = false;
    private static KafkaContainer kafkaContainer;
    private KafkaProperties kafkaProperties;

    private MockMvc restMockMvc;

    @BeforeAll
    static void startServer() {
        if (!started) {
            startTestcontainer();
            started = true;
        }
    }

    private static void startTestcontainer() {
        kafkaContainer = new KafkaContainer("5.4.0");
        kafkaContainer.start();
    }

    @BeforeEach
    void setup() {
        kafkaProperties = new KafkaProperties();
        Map<String, Map<String, Object>> producerProps = getProducerProps();
        kafkaProperties.setProducer(new HashMap<>(producerProps));

        Map<String, Map<String, Object>> props = getConsumerProps();
        props.get("string").put("client.id", "default-client");
        kafkaProperties.setConsumer(props);

        GeneratedProjectForTestsKafkaResource kafkaResource = new GeneratedProjectForTestsKafkaResource(kafkaProperties);

        restMockMvc = MockMvcBuilders.standaloneSetup(kafkaResource).build();
    }

    @Test
    void producesMessages() throws Exception {
        restMockMvc.perform(post("/api/generated-project-for-tests-kafka/publish/string?message=value-produced"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON));

        Map<String, Object> consumerProps = new HashMap<>(getConsumerProps().get("string"));
        KafkaConsumer<String, String> consumer = new KafkaConsumer<>(consumerProps);
        consumer.subscribe(Collections.singletonList("string"));
        ConsumerRecords<String, String> records = consumer.poll(Duration.ofSeconds(1));

        assertEquals(1, records.count());
        ConsumerRecord<String, String> record = records.iterator().next();
        assertEquals("value-produced", record.value());
    }

    @Test
    void consumesMessages() throws Exception {
        restMockMvc.perform(get("/api/generated-project-for-tests-kafka/consume?topic=string"))
            .andExpect(status().isOk())
            .andExpect(request().asyncStarted())
            .andReturn();

        restMockMvc.perform(post("/api/generated-project-for-tests-kafka/publish/string?message=value-to-consume"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON));
    }

    private Map<String, Map<String, Object>> getProducerProps() {
        Map<String, Map<String, Object>> props = new HashMap<>();
        Map<String, Object> producerProps = new HashMap<>();
        producerProps.put("key.serializer", "org.apache.kafka.common.serialization.StringSerializer");
        producerProps.put("value.serializer", "org.apache.kafka.common.serialization.StringSerializer");
        producerProps.put("bootstrap.servers", kafkaContainer.getBootstrapServers());
        props.put("string", producerProps);
        return props;
    }

    private Map<String, Map<String, Object>> getConsumerProps() {
        Map<String, Map<String, Object>> props = new HashMap<>();
        Map<String, Object> consumerProps = new HashMap<>();
        consumerProps.put("key.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
        consumerProps.put("value.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
        consumerProps.put("bootstrap.servers", kafkaContainer.getBootstrapServers());
        consumerProps.put("auto.offset.reset", "earliest");
        consumerProps.put("group.id", "generated-project-for-tests");
        props.put("string", consumerProps);
        return props;
    }
}

