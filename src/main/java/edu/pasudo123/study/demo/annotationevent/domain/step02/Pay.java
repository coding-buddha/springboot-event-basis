package edu.pasudo123.study.demo.annotationevent.domain.step02;

import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

import java.util.UUID;

@Getter
@ToString
public class Pay {

    private final UUID uuid;
    private final String name;
    private final Long pay;

    private Pay(final String name, final long pay) {
        this.uuid = UUID.randomUUID();
        this.name = name;
        this.pay = pay;
    }

    @Builder
    public static Pay of(final String name, final long pay) {
        return new Pay(name, pay);
    }

    public String getCustomPayInfo() {
        return String.format("%s custom current info[uuid :: %s] :: %dwon", getClass().getSimpleName(), uuid, pay);
    }
}
