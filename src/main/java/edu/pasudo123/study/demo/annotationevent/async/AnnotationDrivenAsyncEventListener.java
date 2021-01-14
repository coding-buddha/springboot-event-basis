package edu.pasudo123.study.demo.annotationevent.async;

import edu.pasudo123.study.demo.annotationevent.domain.step01.PaymentAsync;
import edu.pasudo123.study.demo.annotationevent.domain.step01.PaymentConditionalAsync;
import edu.pasudo123.study.demo.annotationevent.worker.MyWorkerProcess;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class AnnotationDrivenAsyncEventListener {

    @Async
    @EventListener
    public void handleEvent(final PaymentAsync event) {
        log.info("");
        log.info("==============================================");
        log.info("[annotation-based-event] : async event start !!");
        // 해당 작업은 비동기로 메인 스레드가 아닌 별도의 task 스레드로 병렬 실행된다.
        new MyWorkerProcess().doSomething(event);
        log.info("[annotation-based-event] : async event end !!");
    }

    /**
     * condition 이 해당 이벤트 수행여부를 결정한다.
     * @param event
     */
    @Async
    @EventListener(condition = "#event.awesome")
    public void handleConditionalEvent(final PaymentConditionalAsync event) {
        log.info("");
        log.info("==============================================");
        log.info("[annotation-based-event] : conditional async event start !!");
        new MyWorkerProcess().doSomething(event);
        log.info("[annotation-based-event] : conditional async event end !!");
    }
}
