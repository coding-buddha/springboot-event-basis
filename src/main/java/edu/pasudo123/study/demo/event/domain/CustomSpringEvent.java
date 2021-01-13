package edu.pasudo123.study.demo.event.domain;

import lombok.Getter;
import org.springframework.context.ApplicationEvent;

/**
 * spring event 를 이용하기 위한 애플리케이션 이벤트 객체
 */
@Getter
public class CustomSpringEvent extends ApplicationEvent {

    private final String message;

    /**
     * Create a new {@code ApplicationEvent}.
     *
     * @param source the object on which the event initially occurred or with
     *               which the event is associated (never {@code null})
     */
    public CustomSpringEvent(Object source, final String message) {
        super(source);
        this.message = message;
    }
}
