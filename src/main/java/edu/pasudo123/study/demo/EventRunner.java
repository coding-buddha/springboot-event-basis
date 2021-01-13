package edu.pasudo123.study.demo;

import edu.pasudo123.study.demo.event.CustomSpringEventPublisher;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
@RequiredArgsConstructor
public class EventRunner {

    private final CustomSpringEventPublisher publisher;

    public void run() {
        // 5개의 이벤트를 전송한다.
        for(int i = 0; i < 3; i++) {
            publisher.publishCustomEvent(String.format("[event-runner] : %s", LocalDateTime.now().toString()));
        }
    }
}
