package tech.ippon.generated.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import javax.annotation.PostConstruct;
import java.util.HashMap;
import java.util.Map;

@Configuration
@ConfigurationProperties(prefix = "kafka")
public class KafkaProperties {

    private Map<String, Map<String, Object>> consumer = new HashMap<>();

    private Map<String, Map<String, Object>> producer = new HashMap<>();

    @PostConstruct
    public void init() {

        for (String consumerKey: consumer.keySet()) {
            final Map<String, Object> properties = consumer.get(consumerKey);
            if (! properties.containsKey("bootstrap.servers")) {
                properties.put("bootstrap.servers", "localhost:9092");
            }
        }

        for (String consumerKey: producer.keySet()) {
            final Map<String, Object> properties = producer.get(consumerKey);
            if (! properties.containsKey("bootstrap.servers")) {
                properties.put("bootstrap.servers", "localhost:9092");
            }
        }
    }

    public Map<String, Map<String, Object>> getConsumer() {
        return this.consumer;
    }

    public void setConsumer(Map<String, Map<String, Object>> consumer) {
        this.consumer = consumer;
    }

    public Map<String, Map<String, Object>> getProducer() {
        return this.producer;
    }

    public void setProducer(Map<String, Map<String, Object>> producer) {
        this.producer = producer;
    }
}
