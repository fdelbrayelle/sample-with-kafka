package tech.ippon.generated.service.kafka.consumer;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.core.task.SimpleAsyncTaskExecutor;
import org.springframework.stereotype.Service;
import tech.ippon.generated.config.KafkaProperties;
import tech.ippon.generated.domain.Order;
import tech.ippon.generated.service.kafka.GenericConsumer;

@Service
public class OrderConsumer extends GenericConsumer<Order> {

    private final Logger log = LoggerFactory.getLogger(Order.class);

    public OrderConsumer(@Value("${kafka.consumer.order.name}") final String topicName, final KafkaProperties kafkaProperties) {
        super(topicName, kafkaProperties.getConsumer().get("order"), kafkaProperties.getPollingTimeout());
    }

    @Override
    protected void handleMessage(final ConsumerRecord<String, Order> record) {
        log.info("Handling message for Order record: {}", record.value());

        // TODO: Here is where you can add specific code to handle your messages
    }

    @Bean
    public void executeKafkaOrderRunner() {
        new SimpleAsyncTaskExecutor().execute(this);
    }
}
