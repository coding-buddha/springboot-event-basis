package edu.pasudo123.study.demo.annotationevent.listener;

import edu.pasudo123.study.demo.annotationevent.domain.step01.Payment;
import edu.pasudo123.study.demo.annotationevent.domain.step02.Pay;
import edu.pasudo123.study.demo.annotationevent.domain.step02.PaymentCreateEvent;
import lombok.extern.slf4j.Slf4j;

/**
 * 이벤트에 따른 특정 프로세스 수행
 */
@Slf4j
public class MyWorkerProcess {

    public MyWorkerProcess() {}

    public void doSomething(final Payment payment) {
        try {
            log.info("MyWorkerProcess process ...");
            payment.printCurrentPayInfo();
            Thread.sleep(5000);
            log.info("MyWorkerProcess process completed !");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void doSomething(final PaymentCreateEvent event) {
        try {
            log.info("MyWorkerProcess process ...");
            event.printCustomPayInfo();
            Thread.sleep(3000);
            log.info("MyWorkerProcess process completed !");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
