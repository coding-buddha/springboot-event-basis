# springboot-event-basis
spring 에서 제공하는 event pub/sub 은 유용하다.

## spring 4.2 이하
```java
// pub 객체는 ApplicationEventPublisher 를 구성하여 event 를 발송한다.
@Slf4j
@Component
@RequiredArgsConstructor
public class CustomSpringEventPublisher {

    private final ApplicationEventPublisher publisher;

    public void publishCustomEvent(final String message) {
        log.info("[pub event] : {}", message);
        publisher.publishEvent(event);
    }
}

// sub 객체는 ApplicationListener<Event> 를 상속받고 event 룰 수신한다.
@Slf4j
@Component
public class CustomSpringEventListener implements ApplicationListener<CustomSpringEvent> {

    @Override
    public void onApplicationEvent(CustomSpringEvent event) {
        log.info("[sub event] : {}", event.getMessage());
        log.info("");
    }
}

// 비동기로 보내고 싶다면? ApplicationEventMulticaster 를 빈으로 등록하자
@Bean(name = "applicationEventMulticaster")
public ApplicationEventMulticaster simpleApplicationEventMulticaster() {
    SimpleApplicationEventMulticaster eventMulticaster =
            new SimpleApplicationEventMulticaster();

    eventMulticaster.setTaskExecutor(new SimpleAsyncTaskExecutor());
    return eventMulticaster;
}
```

## spring 4.2 이후
```java
// @EventListener 애노테이션으로 간단하게 이벤트를 발송할 수 있다.
// 아래는 동기식 이벤트 발송이다.
@Slf4j
@Component
public class AnnotationDrivenEventListener {

    @EventListener
    public void handleContext(final ContextRefreshedEvent event) {
        log.info("[annotation-based-event] : sync event start !!");
        new MyWorkerProcess().doSomething();
        log.info("[annotation-based-event] : sync event end !!");
    }
}

// @EnableAsync 및 @Async 를 이용하면 비동기 이벤트 송수신이 가능하다.
@Slf4j
@Component
@EnableAsync
public class AnnotationDrivenAsyncEventListener {

    @Async
    @EventListener
    public void handleContext(final ContextRefreshedEvent event) {
        log.info("[annotation-based-event] : async event start !!");
        // 해당 작업은 비동기로 메인 스레드가 아닌 별도의 task 스레드로 병렬 실행된다.
         new MyWorkerProcess().doSomething();
        log.info("[annotation-based-event] : async event end !!");
    }
}

// ContextRefreshEvent 는 스프링 자체 이벤트이고, SpringApplicationBuilder 객체에서 별도로 listener() 를 등록해주어야 한다.
// DemoApplication.java 에 있는 내용.
final ConfigurableApplicationContext context = new SpringApplicationBuilder(DemoApplication.class)
                    .listeners(
                            // 스프링 자제 이벤트 리스너 등록
                            new ContextStartedListener(),
                            new ContextRefreshedListener(),
                            new ContextClosedListener())
                    .run(args);
```

## Transaction Bound Events
공부해야하는데...