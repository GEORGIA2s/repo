package study.generic.middle;

import java.util.HashMap;
import java.util.Map;

/**
 * 외부에서 의존성을 가져오고, 주입하는 컨테이너
 * registerDependency 메서드로 맵에 부여할 의존성을 저장하고
 * getDependency 메서드로 꺼내올 수 있다.
 */
class DependencyContainer {
    private Map<Class<?>, Object> container = new HashMap<>();
    // 저장할 데이터의 클래스 타입을 키값으로 하고, 해당 클래스의 객체를 (Object) 밸류값으로 받는다.

    public <T> void registerDependency(Class<T> clazz, T instance) {
        container.put(clazz, instance);
//        container.put(String.class,"HELLO!!");
//        container.put(Integer.class,42);
    }

    public <T> T getDependency(Class<T> clazz){
        return clazz.cast(container.get(clazz));
        // clazz.cast() 는 반환할 객체의 타입을 지정한다 cast() 메서드를 통해 캐스팅이 이루어진다.
    }
}

// 활용할 의존성 클래스의 인터페이스 여기서는 getMessage()를 정의했다
interface MessageService {
    String getMessage();
}

// 인터페이스에서 생성한 getMessage()를 오버라이딩 하여 구현한 모습
class WelcomeMessageService implements MessageService{

    @Override
    public String getMessage() {
        return "WELCOME!!";
    }
}

// 의존성을 주입받을 클래스의 객체를 생성하고 생성자를 정의했다.
// 이 생성자는 MessageService를 파라미터로 받아 의존성(Dependency)가 주입되고 있다.
// Application 클래스는 어떤 종류의 MessageService 구현체를 사용할지 알 필요가 없으며, 언제든지 다른 구현체로 교체 가능하다.
// run() 메서드로 해당 클래스가 가지고 있는 메서드를 실행.
class Application {
    private MessageService msgService;

    public Application(MessageService msgService){
        this.msgService = msgService;
    }

    public void run() {
        System.out.println(msgService.getMessage());
    }
}

// dependency을 주입할 container의 객체를 생성하고
// dependency이 주입될 class의 객체를 생성했는데

// Interface 객체에 실제 생성하는건 interface를 구현한 class의 객체가 생성된다. (ex List list = new ArrayList)
// => 인터페이스를 통해 다양한 구현을 쉽게 교체할 수 있게 하는 객체 지향 프로그래밍의 기본 원칙 중 하나인
// => 프로그래밍을 인터페이스에 맞춰서 구현체에는 의존(Dependency)하지 않는다.

// container 에 inteface의 class와 구현한 class의 객체의 dependency을 저장 후

// Application 클래스의 객체를 생성하며 동시에
// container가 저장한 dependency를 getDependency 메서드로 가져온다 가져오는건 interface.class
// 마지막으로 Application의 run() 메서드를 통해서
// MessageService의 getMessage()가 실행되는 모습이다
public class DependencyExample {
    public static void main(String[] args) {
        DependencyContainer container = new DependencyContainer();

        MessageService msgService = new WelcomeMessageService();
        container.registerDependency(MessageService.class, msgService);

        Application app = new Application(container.getDependency(MessageService.class));
        app.run();
    }

}

/*
의존성이란 클래스A가 클래스B를 사용한다면
클래스 A는 클래스B에 의존하고 있다 라고 칭한다

만약, 클래스 B에 변경이 생기면 클래스 A는 그 영향을 받는다

의존성은 코드간의 관계를 정의하고, 시스템의 구조를 형성하는데 중요한 역할을 한다.
너무 강한 의존성이 있으면 코드의 유지보수와 확장이 어려워지므로
의존성을 최대한 느슨하게 유지하고, 객체간의 상호 작용을 유연하게 설계해야 한다.

의존성 주입은 이러한 의존성을 외부에서 주입하는 방법으로,
객체 간의 결합을 낮추어 유연하고 테스트 가능한 코드를 작성하는데 도움이 된다.
 */