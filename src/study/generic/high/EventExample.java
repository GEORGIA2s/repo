package study.generic.high;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Consumer;

/*
Event Class는 이벤트를 나타내는 기본 클래스
 */
class Event {
    private String name;

    public Event(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}

/*
subscribers 맵은 이벤트 유형을 Class를 키 값으로 하고
해당 이벤트에 대한 구독자의 리스트를 값으로 갖는다

subscribe 메서드는 특정 이벤트 유형에 대한 구독자를 등록하고,

publish 메서드는 주어진 이벤트를 발행하고, 해당 이벤트에 대한 구독자에게
이벤트를 전달한다

 */
class EventBus {
    private Map<Class<?>, List<Consumer<?>>> subscribers = new HashMap<>();

    public <T> void subscribe(Class<T> eventType, Consumer<T> consumer) {
        // Consumer 어떠한 무언가(인자)를 받아와서 사용하는데 쓰이는 클래스이다.
        // 이 Consumer Class를 이용하면 프로그램에서 일련의 작업을 정의하고
        // 이 작업을 나중에 어떤 값에 적용 시킬 수 있다.

        subscribers.computeIfAbsent(eventType, k -> new ArrayList<>()).add(consumer);
        /* computeIfAbsent = 이 메서드는 주어진 키에 해당하는 값이 존재하지 않을 때,
         새로운 값을 계산하고 맵에 추가한다.

        subscribers 맵에서 eventType에 해당하는 리스트를 가져오는데,
        만약 해당 키가 없다면 새로운 ArrayList를 생성한다.
         */

    }

    public <T> void publish(T event) {
        Class<?> eventType = event.getClass();
        List<Consumer<?>> eventConsumers = subscribers.get(eventType);
        /*
        <?> 와일드 카드를 사용한  이유는 어떤 타입의 이벤트도 처리 할 수 있게 끔 설계하기 위함.
         */

        if (eventConsumers != null) {
            eventConsumers.forEach(consumer -> ((Consumer<T>) consumer).accept(event));
            // 이전에 미리 정의한 작업을 특정한 값에 적용하는 모습.
            // 해당 작업(cousumer)를 가져와서 원래 타입(T)으로 받아들일 수 있도록 형변환을 시킨다.
            // 그 후 event를 넘겨주어 메서드를 실행한다.
        }
    }

}

/*
    실제로 이벤트를 처리하는 핸들러 클래스
    handelEventA는 EventA 이벤트를 처리하고
    B도 A와 동일하게 처리한다.
 */
class ExampleEventHandler {
    public void handleEventA(EventA eventA) {
        System.out.println("EventA Handled" + eventA.getName());
    }

    public void handleEventB(EventB eventB) {
        System.out.println("EventB Handled" + eventB.getName());
    }
}

// Event를 상속하고 구체적이게 구현하는 EventA,B 클래스
class EventA extends Event {
    public EventA(String name) {
        super(name);
    }
}

class EventB extends Event {
    public EventB(String name) {
        super(name);
    }
}

/*
    EventExample 클래스는 이벤트 버스의 객체를 생성하고
    EventA 및 EventB에 대한 이벤트를 처리하도록 한다.
    
    EventA 와 EevntB를 생성하고
    publish 메서드로 각 이벤트를 발행한다
    각 이벤트에 대해 등록된 핸들러가 호출되어 프로그램이 실행된다.
 */

public class EventExample {
    public static void main(String[] args) {
        EventBus eventBus = new EventBus();

        ExampleEventHandler eventHandler = new ExampleEventHandler();
        eventBus.subscribe(EventA.class, eventHandler::handleEventA);
        eventBus.subscribe(EventB.class, eventHandler::handleEventB);

        EventA eventA = new EventA("Event !!! A !!!!");
        EventB eventB = new EventB("Event !!! B !!!!");

        eventBus.publish(eventA);
        eventBus.publish(eventB);
    }
}

/*
일급 객체로서의 함수:

Java에서는 메서드를 일급 객체로 다룰 수 있습니다.
즉, 메서드를 변수에 할당하고 인자로 전달할 수 있습니다.
Consumer는 함수형 인터페이스로서 이러한 일급 객체의 역할을 합니다.
이를 통해 함수(메서드)를 값처럼 다룰 수 있어서 유연성이 증가합니다.

콜백 및 이벤트 핸들링:
이벤트 핸들링이나 콜백 등에서 Consumer를 사용하면
특정 이벤트가 발생했을 때 실행되어야 하는 동작을 쉽게 정의할 수 있습니다.
예를 들어, 특정 이벤트에 대한 리스너를 등록하고,
그 리스너가 처리해야 할 동작을 Consumer로 정의할 수 있습니다.

인터페이스 구현과 익명 클래스 대체:
Consumer는 단일 메서드를 가진 함수형 인터페이스로,
익명 클래스나 람다 표현식을 통해 간단하게 구현할 수 있습니다.
이를 통해 코드의 가독성을 높이고, 작은 크기의 동작을 캡슐화할 수 있습니다.

제네릭 타입 활용:
Consumer는 제네릭 타입을 사용하므로, 다양한 타입의 값을 받아들일 수 있습니다.
이는 코드를 보다 유연하게 만들어주고, 재사용성을 높여줍니다.
간단히 말하면, Consumer를 사용하면 함수를 변수처럼 다루고,
이벤트 핸들링을 간편하게 구현할 수 있어서 코드의 가독성과 유지보수성을 향상시킬 수 있습니다.
 */