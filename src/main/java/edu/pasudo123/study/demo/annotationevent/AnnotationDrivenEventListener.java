package edu.pasudo123.study.demo.annotationevent;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.ContextStartedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

/**
 * 애노테이션 기반의 이벤트 리스터 작성
 *
 * @EventListener 애노테이션을 추가해준다.
 *
 * 리스너는 동기방식으로 수행된다.
 * 해당 리스너에 대해서 비동기방식으로 수행하고자 한다면
 * @EnableAsync 애노테이션 및
 * @Aysnc 애노테이션을 같이 추가해준다.
 *
 */
//@Slf4j
//@Component
//public class AnnotationDrivenEventListener {
//
//    @EventListener
//    public void handleContext(final ContextRefreshedEvent event) {
//        log.info("[annotation-based-event] : sync event start !!");
//        new MyWorkerProcess().doSomething();
//        log.info("[annotation-based-event] : sync event end !!");
//    }
//}
