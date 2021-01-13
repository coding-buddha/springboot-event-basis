package edu.pasudo123.study.demo.annotationevent.domain;

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

    private PaymentSync(final UUID uuid, final long pay) {
        this.uuid = uuid;
        this.pay = pay;
    }

    @Builder
    public static PaymentSync of(final UUID uuid, final long pay) {
        return new PaymentSync(uuid, pay);
    }

    @Override
    public void printCurrentPayInfo() {
        log.info(String.format("%s current info[%s] :: %dwon", getClass().getSimpleName(), uuid, pay));
    }
}
