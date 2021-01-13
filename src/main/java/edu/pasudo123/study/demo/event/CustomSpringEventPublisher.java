package edu.pasudo123.study.demo.event;

import edu.pasudo123.study.demo.event.domain.CustomSpringEvent;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Component;

/**
 * 애플리케이션에서 발생한 이벤트에 대해서 publish 를 수행하는 객체
 */
@Slf4j
@Component
@RequiredArgsConstructor
public class CustomSpringEventPublisher {

    private final ApplicationEventPublisher publisher;

    public void publishCustomEvent(final String message) {
        log.info("[pub event] : {}", message);
        CustomSpringEvent event = new CustomSpringEvent(this, message);

        // main thread 가 수행된다.
        // ----------------------------------------------
        // publisher 가 수행 시 호출 thread 는 개별 수행된다.

        publisher.publishEvent(event);
    }
}
