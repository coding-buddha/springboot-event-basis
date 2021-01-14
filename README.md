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

## PayloadApplicationEvent 가 발행되는 과정
* 스프링 4.2 이후부터는 ApplicationEvent 를 상속받지 않아도 이벤트 발행이 된다.
* 해당 작업이 가능한 이유는 AbstractApplicationContext 에서 publishEvent() 가 수행되는데, Application 단 Event 가 아닌 pojo 관련 이벤트 발행에 대해서는 스프링이 자동으로 아래와 같이 PayloadApplicationEvent 로 래핑한다.

```java
protected void publishEvent(Object event, @Nullable ResolvableType eventType) {
		Assert.notNull(event, "Event must not be null");

		// Decorate event as an ApplicationEvent if necessary
		ApplicationEvent applicationEvent;
		if (event instanceof ApplicationEvent) {
			applicationEvent = (ApplicationEvent) event;
		}
		else {
			applicationEvent = new PayloadApplicationEvent<>(this, event);
			if (eventType == null) {
				eventType = ((PayloadApplicationEvent<?>) applicationEvent).getResolvableType();
			}
		}

// 이하 생략...
```

## example
|<div style="width:250px">spec</div>|desc|
|-------|-------|
|http://localhost:8080/sync|동기방식으로 이벤트 호출|
|http://localhost:8080/async|비동기방식으로 이벤트 호출|
|http://localhost:8080/async-condition?condition={true-or-false}|비동기방식으로 조건부 이벤트 호출|
|http://localhost:8080/auto-wrapping-event|pojo 에 ApplicationEvent 를 상속받지 않더라도 spring 단에서 유연하게 처리한다. (내부적으로 PayloadApplicationEvent 로 wrapping 하여 이벤트가 호출됨)|

## Transaction Bound Events
* 이벤트 송신쪽에서 트랜잭션 바인딩 상태에서 이벤트 호출여부를 결정할 수 있다. (유용할 것 같은 기분)
* 해당 내용은 이후에 공부할 수 있도록 하자.

## reference 
* https://www.baeldung.com/spring-events
* https://spring.io/blog/2015/02/11/better-application-events-in-spring-framework-4-2
* https://reflectoring.io/spring-boot-application-events-explained/