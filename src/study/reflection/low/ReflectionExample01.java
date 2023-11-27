package study.reflection.low;

import java.lang.reflect.Method;

class SimpleClass {
    public void sayHello() {
        System.out.println("Hello, Reflection!");
    }
}

public class ReflectionExample01 {
    public static void main(String[] args) {
        // 클래스 이름 출력
        Class<?> clazz = SimpleClass.class;
        System.out.println("Class Name: " + clazz.getName());

        try {
            // 인스턴스 생성
            Object instance = clazz.newInstance();

            // 메서드 호출
            Method method = clazz.getMethod("sayHello");
            method.invoke(instance);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
