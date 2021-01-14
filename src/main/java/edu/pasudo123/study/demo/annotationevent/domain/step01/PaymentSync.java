package edu.pasudo123.study.demo.annotationevent.domain.step01;

import lombok.Builder;
import lombok.Getter;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;

import java.util.UUID;

@Slf4j
@Getter
@ToString
public class PaymentSync implements Payment {

    private final UUID uuid;
    private final Long pay;

    private PaymentSync(final long pay) {
        this.uuid = UUID.randomUUID();
        this.pay = pay;
    }

    @Builder
    public static PaymentSync of(final long pay) {
        return new PaymentSync(pay);
    }

    @Override
    public void printCurrentPayInfo() {
        log.info(String.format("%s current info[%s] :: %dwon", getClass().getSimpleName(), uuid, pay));
    }
}
