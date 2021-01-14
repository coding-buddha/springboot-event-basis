package edu.pasudo123.study.demo;

import edu.pasudo123.study.demo.annotationevent.domain.step01.PaymentAsync;
import edu.pasudo123.study.demo.annotationevent.domain.step01.PaymentConditionalAsync;
import edu.pasudo123.study.demo.annotationevent.domain.step01.PaymentSync;
import edu.pasudo123.study.demo.annotationevent.domain.step02.Pay;
import edu.pasudo123.study.demo.annotationevent.domain.step02.PaymentCreateEvent;
import lombok.RequiredArgsConstructor;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ThreadLocalRandom;

@RestController
@RequiredArgsConstructor
public class DemoController {

    private final ApplicationEventPublisher publisher;

    @GetMapping("sync")
    public Map<String, Object> sync() {
        publisher.publishEvent(PaymentSync.of(ThreadLocalRandom.current().nextLong(10000)));

        return new HashMap<>(){{
            put("current-time", LocalDateTime.now());
        }};
    }

    @GetMapping("async")
    public Map<String, Object> async() {
        publisher.publishEvent(PaymentAsync.of(ThreadLocalRandom.current().nextLong(10000)));

        return new HashMap<>(){{
            put("current-time", LocalDateTime.now());
        }};
    }

    @GetMapping("async-conditional")
    public Map<String, Object> asyncConditional(@RequestParam(defaultValue = "false", value = "condition") Boolean condition) {
        final PaymentConditionalAsync payment = PaymentConditionalAsync
                .of(ThreadLocalRandom.current().nextLong(10000), condition);

        // condition 값에 따라서 이벤트 pub 여부를 결정할 수 있다. : SpEL 이용
        publisher.publishEvent(payment);

        return new HashMap<>(){{
            put("current-time", LocalDateTime.now());
            put("value", payment);
        }};
    }

    @GetMapping("auto-wrapping-event")
    public Map<String, Object> asyncPayload() {
        final Pay pay = Pay.of("park", ThreadLocalRandom.current().nextLong(50000));

        // payment
        publisher.publishEvent(new PaymentCreateEvent(pay));

        return new HashMap<>(){{
            put("current-time", LocalDateTime.now());
            put("value", pay);
        }};
    }
}
