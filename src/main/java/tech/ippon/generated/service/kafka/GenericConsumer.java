package tech.ippon.generated.service.kafka;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.common.errors.WakeupException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.annotation.PostConstruct;
import java.time.Duration;
import java.util.Collections;
import java.util.Map;
import java.util.concurrent.atomic.AtomicBoolean;

public abstract class GenericConsumer<T> implements Runnable {

    private final Logger log = LoggerFactory.getLogger(GenericConsumer.class);

    public static final int POLL_TIMEOUT = 10_000;

    private final AtomicBoolean closed = new AtomicBoolean(false);

    private final KafkaConsumer<String, T> kafkaConsumer;

    private String topicName;

    public GenericConsumer(final String topicName, final Map<String, Object> properties) {
        this.topicName = topicName;
        this.kafkaConsumer = new KafkaConsumer<>(properties);
    }

    @PostConstruct
    public void init() {
        Runtime.getRuntime().addShutdownHook(new Thread(this::shutdown));
    }

    @Override
    public void run() {
        try {
            kafkaConsumer.subscribe(Collections.singleton(topicName));
            while (!closed.get()) {
                ConsumerRecords<String, T> records = kafkaConsumer.poll(Duration.ofMillis(POLL_TIMEOUT));
                for (ConsumerRecord<String, T> record : records) {
                    handleMessage(record);
                }
                kafkaConsumer.commitSync();
            }
        } catch (WakeupException e) {
            // Ignore exception if closing
            if (!closed.get()) throw e;
        } catch (Exception e) {
            log.error(e.getMessage(), e);
        } finally {
            kafkaConsumer.close();
        }
    }

    public void shutdown() {
        log.info("Shutdown Kafka consumer");
        closed.set(true);
        kafkaConsumer.wakeup();
    }

    protected abstract void handleMessage(ConsumerRecord<String, T> record);
}
