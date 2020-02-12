package tech.ippon.generated.service.kafka.consumer;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.task.SimpleAsyncTaskExecutor;
import tech.ippon.generated.config.KafkaProperties;
import tech.ippon.generated.service.kafka.GenericConsumer;

@Configuration
public class StringConsumer extends GenericConsumer<String> {

    private final Logger log = LoggerFactory.getLogger(GenericConsumer.class);

    public StringConsumer(@Value("${kafka.consumer.string.name}") final String topic, final KafkaProperties kafkaProperties) {
        super(topic, kafkaProperties.getConsumerConfiguration("string"));
    }

    @Override
    protected void handleMessage(final ConsumerRecord<String, String> record) {
        log.info("Handling message for String record: {}", record.value());
    }

    @Bean
    public void executeKafkaStringRunner() {
        new SimpleAsyncTaskExecutor().execute(this);
    }
}
