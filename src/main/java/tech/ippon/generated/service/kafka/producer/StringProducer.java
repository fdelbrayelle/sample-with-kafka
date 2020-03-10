package tech.ippon.generated.service.kafka.producer;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import tech.ippon.generated.config.KafkaProperties;
import tech.ippon.generated.service.kafka.GenericProducer;

@Component
public class StringProducer extends GenericProducer<String> {

    public StringProducer(@Value("${kafka.producer.string.name}") final String topic, final KafkaProperties kafkaProperties) {
        super(topic, kafkaProperties.getProducerConfiguration("string"));
    }
}
