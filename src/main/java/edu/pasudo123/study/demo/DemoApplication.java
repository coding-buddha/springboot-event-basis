package edu.pasudo123.study.demo;

import edu.pasudo123.study.demo.springitself.event.ContextClosedListener;
import edu.pasudo123.study.demo.springitself.event.ContextRefreshedListener;
import edu.pasudo123.study.demo.springitself.event.ContextStartedListener;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ConfigurableApplicationContext;

@Slf4j
@SpringBootApplication
@RequiredArgsConstructor
public class DemoApplication implements ApplicationRunner {

    private final EventRunner eventRunner;

    public static void main(String[] args) {
        final ConfigurableApplicationContext context = new SpringApplicationBuilder(DemoApplication.class)
                .listeners(
                        // 스프링 자제 이벤트 리스너 등록
                        new ContextStartedListener(),
                        new ContextRefreshedListener(),
                        new ContextClosedListener())
                .run(args);

        // context.start(); // spring itself event 가 발행됨 : ContextStartedListener, 따로 start 가 되는게 아니라 start 되었다고 명시해주어야 함
        // context.close(); // spring itself event 가 발행됨 : ContextClosedListener
    }

    @Override
    public void run(ApplicationArguments args) throws Exception {
        log.info("[spring-event-basis-project] startup application");

        // spring 4.2 이하때 사용하는 spring event
        // eventRunner.run();
    }
}
