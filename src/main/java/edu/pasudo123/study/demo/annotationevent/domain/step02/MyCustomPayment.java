package edu.pasudo123.study.demo.annotationevent.domain.step02;

import lombok.Builder;
import lombok.Getter;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;

import java.util.UUID;

@Slf4j
@Getter
@ToString
public class MyCustomPayment {

    private final UUID uuid;
    private final String name;
    private final Long pay;

    private MyCustomPayment(final String name, final long pay) {
        this.uuid = UUID.randomUUID();
        this.name = name;
        this.pay = pay;
    }

    @Builder
    public static MyCustomPayment of(final String name, final long pay) {
        return new MyCustomPayment(name, pay);
    }
}
