package tech.ippon.generated.service.kafka.producer;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import javax.annotation.PostConstruct;

import tech.ippon.generated.config.KafkaProperties;
import tech.ippon.generated.domain.Order;

@Service
public class OrderProducer {

    private final Logger log = LoggerFactory.getLogger(OrderProducer.class);

    private final KafkaProducer<String, Order> kafkaProducer;

    private String topicName;

    public OrderProducer(@Value("${kafka.producer.order.name}") final String topicName, final KafkaProperties kafkaProperties) {
        this.topicName = topicName;
        this.kafkaProducer = new KafkaProducer<>(kafkaProperties.getProducer().get("order"));
    }

    @PostConstruct
    public void init() {
        Runtime.getRuntime().addShutdownHook(new Thread(this::shutdown));
    }

    public void send(final Order message) {
        final ProducerRecord<String, Order> record = new ProducerRecord<>(topicName, message);
        try {
            kafkaProducer.send(record);
        } catch (final Exception e) {
            log.error(e.getMessage(), e);
        }
    }

    public void shutdown() {
        log.info("Shutdown Kafka producer");
        kafkaProducer.close();
    }
}
