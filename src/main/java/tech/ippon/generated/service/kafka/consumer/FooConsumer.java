package tech.ippon.generated.service.kafka.consumer;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.task.SimpleAsyncTaskExecutor;
import tech.ippon.generated.config.KafkaProperties;
import tech.ippon.generated.domain.Foo;
import tech.ippon.generated.service.kafka.GenericConsumer;

@Configuration
public class FooConsumer extends GenericConsumer<Foo> {

    private final Logger log = LoggerFactory.getLogger(Foo.class);

    public FooConsumer(@Value("${kafka.consumer.foo.name}") final String topic, final KafkaProperties kafkaProperties) {
        super(topic, kafkaProperties.getConsumerConfiguration("foo"));
    }

    @Override
    protected void handleMessage(final ConsumerRecord<String, Foo> record) {
        log.info("Handling message for Foo record: {}", record.value());

        // TODO: Here is where you can add specific code to handle your messages
    }

    @Bean
    public void executeKafkaFooRunner() {
        new SimpleAsyncTaskExecutor().execute(this);
    }
}
