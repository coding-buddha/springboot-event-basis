package edu.pasudo123.study.demo.annotationevent.domain.step01;

import lombok.Builder;
import lombok.Getter;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;

import java.util.UUID;

@Slf4j
@Getter
@ToString
public class PaymentConditionalAsync implements Payment {

    private final UUID uuid;
    private final Long pay;
    private final boolean awesome;

    private PaymentConditionalAsync(Long pay, final boolean awesome) {
        this.uuid = UUID.randomUUID();
        this.pay = pay;
        this.awesome = awesome;
    }

    @Builder
    public static PaymentConditionalAsync of(final long pay, final boolean awesome) {
        return new PaymentConditionalAsync(pay, awesome);
    }

    @Override
    public void printCurrentPayInfo() {
        log.info(String.format("%s current info[%s] :: %dwon", getClass().getSimpleName(), uuid, pay));
    }
}
