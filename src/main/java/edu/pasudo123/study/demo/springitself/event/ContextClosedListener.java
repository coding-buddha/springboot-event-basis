package edu.pasudo123.study.demo.springitself.event;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextClosedEvent;

/**
 * SpringApplicationBuilder listeners() 에 등록하여야 한다.
 */
@Slf4j
public class ContextClosedListener implements ApplicationListener<ContextClosedEvent> {

    @Override
    public void onApplicationEvent(ContextClosedEvent event) {
        log.info("[spring-event-basis-project] handling context closed event.");
    }
}
