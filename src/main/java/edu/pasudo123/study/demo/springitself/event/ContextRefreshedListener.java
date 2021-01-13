package edu.pasudo123.study.demo.springitself.event;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;

/**
 * SpringApplicationBuilder listeners() 에 등록하여야 한다.
 */
@Slf4j
public class ContextRefreshedListener implements ApplicationListener<ContextRefreshedEvent> {

    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {
        log.info("[spring-event-basis-project] handling context re-freshed event.");
    }
}
