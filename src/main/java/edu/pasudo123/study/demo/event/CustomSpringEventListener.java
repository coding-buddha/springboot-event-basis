package edu.pasudo123.study.demo.event;

import edu.pasudo123.study.demo.event.domain.CustomSpringEvent;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

/**
 * 애플리케이션에서 발생된 이벤트를 subscribe 수행하는 객체
 */
@Slf4j
@Component
public class CustomSpringEventListener implements ApplicationListener<CustomSpringEvent> {

    /**
     * @param event ApplicationListener 에서 ApplicationEvent 를 상속받은 제네릭 타입으로 받고있다.
     */
    @Override
    public void onApplicationEvent(CustomSpringEvent event) {
        log.info("[sub event] : {}", event.getMessage());
        log.info("");

        try {
            // 메시지를 보내기 이전에 1초간 강제 슬립을 수행한다.
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
