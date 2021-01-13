package edu.pasudo123.study.demo.annotationevent;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class MyWorkerProcess {

    public MyWorkerProcess() {}

    public void doSomething() {
        try {
            log.info("MyWorkerProcess process ...");
            Thread.sleep(5000);
            log.info("MyWorkerProcess process completed !");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
