package edu.pasudo123.study.demo.annotationevent.async;

import edu.pasudo123.study.demo.annotationevent.domain.PaymentAsync;
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
    public void handleContext(final PaymentAsync event) {
        log.info("[annotation-based-event] : async event start !!");
        // 해당 작업은 비동기로 메인 스레드가 아닌 별도의 task 스레드로 병렬 실행된다.
        new MyWorkerProcess().doSomething(event);
        log.info("[annotation-based-event] : async event end !!");
    }
}
