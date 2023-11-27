package study.level.low;

import java.util.ArrayList;
import java.util.List;

// 제네릭 클래스 정의
class DataContainer<T> {
    private List<T> dataList = new ArrayList<>();

    // 데이터 추가 메서드
    public void addData(T data) {
        dataList.add(data);
    }

    // 데이터 출력 메서드
    public void printData() {
        for (T data : dataList) {
            System.out.println(data);
        }
    }

    // 어노테이션을 활용한 메서드 예제
    @Deprecated
    public void deprecatedMethod() {
        System.out.println("이 메서드는 더 이상 권장되지 않습니다.");
    }

    // 제네릭과 varargs를 활용한 메서드
    public void processMultipleData(T... data) {
        for (T item : data) {
            System.out.println("Processing data: " + item);
        }
    }
}

// 어노테이션을 활용한 인터페이스
interface MyService {
    @SuppressWarnings("unchecked")
    void performAction();
}

// 구현 클래스
class MyServiceImpl implements MyService {
    @Override
    public void performAction() {
        System.out.println("Action performed!");
    }
}

public class DataContainerExample {
    public static void main(String[] args) {
        // 제네릭을 활용한 데이터 컨테이너 생성
        DataContainer<String> stringContainer = new DataContainer<>();
        stringContainer.addData("Hello");
        stringContainer.addData("World");
        stringContainer.printData();

        // 어노테이션을 활용한 메서드 사용
        stringContainer.deprecatedMethod();

        // 제네릭과 varargs를 활용한 메서드 사용
        stringContainer.processMultipleData("One", "Two", "Three");

        // 어노테이션을 활용한 인터페이스 구현
        MyService myService = new MyServiceImpl();
        myService.performAction();
    }
}
