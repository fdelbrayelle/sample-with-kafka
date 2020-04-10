package tech.ippon.generated.service.kafka;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import tech.ippon.generated.domain.Order;
import tech.ippon.generated.service.kafka.producer.OrderProducer;

import java.math.BigDecimal;

@RestController
@RequestMapping
public class TestController {

    private final OrderProducer orderProducer;

    public TestController(OrderProducer orderProducer) {
        this.orderProducer = orderProducer;
    }

    @GetMapping("/produce")
    public void produce() {
        Order order = new Order();
        order.setAmount(BigDecimal.TEN);
        orderProducer.send(order);
    }
}
