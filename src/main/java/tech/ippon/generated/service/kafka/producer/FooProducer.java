package tech.ippon.generated.service.kafka.producer;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import tech.ippon.generated.config.KafkaProperties;
import tech.ippon.generated.domain.Foo;
import tech.ippon.generated.service.kafka.GenericProducer;

@Component
public class FooProducer extends GenericProducer<Foo> {

    public FooProducer(@Value("${kafka.producer.foo.name}") final String topic, final KafkaProperties kafkaProperties) {
        super(topic, kafkaProperties.getProducerConfiguration("foo"));
    }
}
