package edu.pasudo123.study.demo.annotationevent.domain.step01;

import lombok.Builder;
import lombok.Getter;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;

import java.util.UUID;

@Slf4j
@Getter
@ToString
public class PaymentAsync implements Payment {

    private final UUID uuid;
    private final Long pay;

    private PaymentAsync(final long pay) {
        this.uuid = UUID.randomUUID();
        this.pay = pay;
    }

    @Builder
    public static PaymentAsync of(final long pay) {
        return new PaymentAsync(pay);
    }

    @Override
    public void printCurrentPayInfo() {
        log.info(String.format("%s current info[%s] :: %dwon", getClass().getSimpleName(), uuid, pay));
    }
}
