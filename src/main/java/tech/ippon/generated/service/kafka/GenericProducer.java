package tech.ippon.generated.service.kafka;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.annotation.PostConstruct;
import java.util.Map;
import java.util.concurrent.atomic.AtomicBoolean;

public abstract class GenericProducer<T> {

    private final Logger log = LoggerFactory.getLogger(GenericProducer.class);

    private final KafkaProducer<String, T> kafkaProducer;

    private String topicName;

    public GenericProducer(final String topicName, final Map<String, Object> properties) {
        this.topicName = topicName;
        this.kafkaProducer = new KafkaProducer<>(properties);
    }

    @PostConstruct
    public void init() {
        Runtime.getRuntime().addShutdownHook(new Thread(this::shutdown));
    }

    public void send(T message) {
        ProducerRecord<String, T> record = new ProducerRecord<>(topicName, message);
        try {
            kafkaProducer.send(record);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
        }
    }

    public void shutdown() {
        log.info("Shutdown Kafka producer");
        kafkaProducer.close();
    }
}
