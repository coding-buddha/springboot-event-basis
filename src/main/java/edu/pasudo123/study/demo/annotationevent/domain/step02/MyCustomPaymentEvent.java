package edu.pasudo123.study.demo.annotationevent.domain.step02;

import org.springframework.context.PayloadApplicationEvent;

public class MyCustomPaymentEvent extends PayloadApplicationEvent<MyCustomPayment> {

    /**
     * Create a new PayloadApplicationEvent.
     *
     * @param source  the object on which the event initially occurred (never {@code null})
     * @param payload the payload object (never {@code null})
     */
    public MyCustomPaymentEvent(Object source, MyCustomPayment payload) {
        super(source, payload);
    }
}
