package tech.ippon.generated.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.Map;

@Configuration
@ConfigurationProperties(prefix = "kafka")
public class KafkaProperties {

    private String bootStrapServers = "localhost:9092";

    private Map<String, Map<String, Object>> consumer = new HashMap<>();

    private Map<String, Map<String, Object>> producer = new HashMap<>();

    public String getBootStrapServers() {
        return bootStrapServers;
    }

    public void setBootStrapServers(String bootStrapServers) {
        this.bootStrapServers = bootStrapServers;
    }

    public Map<String, Object> getConsumerConfiguration(final String consumerName) {
        Map<String, Object> properties = consumer.get(consumerName);
        addBootstrapServers(properties);
        return properties;
    }

    public Map<String, Object> getProducerConfiguration(final String producerName) {
        Map<String, Object> properties = producer.get(producerName);
        addBootstrapServers(properties);
        return properties;
    }

    // TODO rename this property from the yml directly to avoid this "mapping" ?
    private void addBootstrapServers(Map<String, Object> properties) {
        if (!properties.containsKey("bootstrap.servers")) {
            properties.put("bootstrap.servers", this.bootStrapServers);
        }
    }

    public Map<String, Object> getConsumer() {
        Map<String, Object> properties = new HashMap<>(this.consumer);
        addBootstrapServers(properties);
        return properties;
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
