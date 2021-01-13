package edu.pasudo123.study.demo;

import edu.pasudo123.study.demo.annotationevent.domain.PaymentAsync;
import edu.pasudo123.study.demo.annotationevent.domain.PaymentSync;
import lombok.RequiredArgsConstructor;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ThreadLocalRandom;

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
    public void asyncConditional() {
    }
}
