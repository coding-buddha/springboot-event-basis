package edu.pasudo123.study.demo;

import edu.pasudo123.study.demo.annotationevent.domain.step01.Payment;
import edu.pasudo123.study.demo.annotationevent.domain.step01.PaymentAsync;
import edu.pasudo123.study.demo.annotationevent.domain.step01.PaymentConditionalAsync;
import edu.pasudo123.study.demo.annotationevent.domain.step01.PaymentSync;
import lombok.RequiredArgsConstructor;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ThreadLocalRandom;

import static javax.swing.UIManager.put;

@RestController
@RequiredArgsConstructor
public class DemoController {

    private final ApplicationEventPublisher publisher;

    @GetMapping("sync")
    public Map<String, Object> sync() {
        publisher.publishEvent(PaymentSync.of(UUID.randomUUID(), ThreadLocalRandom.current().nextLong(10000)));

        return new HashMap<>(){{
            put("current-time", LocalDateTime.now());
        }};
    }

    @GetMapping("async")
    public Map<String, Object> async() {
        publisher.publishEvent(PaymentAsync.of(UUID.randomUUID(), ThreadLocalRandom.current().nextLong(10000)));

        return new HashMap<>(){{
            put("current-time", LocalDateTime.now());
        }};
    }

    @GetMapping("async-conditional")
    public Map<String, Object> asyncConditional(@RequestParam(defaultValue = "false", value = "condition") Boolean condition) {
        final PaymentConditionalAsync payment = PaymentConditionalAsync
                .of(UUID.randomUUID(), ThreadLocalRandom.current().nextLong(10000), condition);

        // condition 값에 따라서 이벤트 pub 여부를 결정할 수 있다. : SpEL 이용
        publisher.publishEvent(payment);

        return new HashMap<>(){{
            put("current-time", LocalDateTime.now());
            put("value", payment);
        }};
    }
}
