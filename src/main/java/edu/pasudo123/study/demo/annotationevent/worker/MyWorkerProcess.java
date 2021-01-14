package edu.pasudo123.study.demo.annotationevent.worker;

import edu.pasudo123.study.demo.annotationevent.domain.step01.Payment;
import lombok.extern.slf4j.Slf4j;

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
}
