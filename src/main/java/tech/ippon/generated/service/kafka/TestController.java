package tech.ippon.generated.service.kafka;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import tech.ippon.generated.service.kafka.producer.StringProducer;

@RestController
@RequestMapping
public class TestController {

    private final StringProducer stringProducer;

    public TestController(StringProducer stringProducer) {
        this.stringProducer = stringProducer;
    }

    @GetMapping("/produce")
    public void produce() {
        stringProducer.send("HELLO");
    }
}
