package edu.pasudo123.study.demo.annotationevent.domain.step02;

import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Getter
public class PaymentCreateEvent implements CreationEvent<Pay> {

    private final Pay pay;

    public PaymentCreateEvent(final Pay pay) {
        this.pay = pay;
    }

    public void printCustomPayInfo() {
        log.info("PaymentCreateEvent ==> {}", pay.getCustomPayInfo());
    }
}
