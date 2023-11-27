package study.reflection.middle;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

class SampleClass {
    private String privateField;

    public SampleClass() {
        this.privateField = "Hello, Reflection!";
    }

    private void privateMethod() {
        System.out.println("Private Method invoked");
    }
}

public class MiddleReflectionExample02 {

    public static void main(String[] args) throws Exception {
        // 클래스 동적으로 로딩
        Class<?> clazz = Class.forName("SampleClass");

        // 객체 생성
        Object instance = clazz.getDeclaredConstructor().newInstance();

        // 필드에 동적으로 접근
        Field privateField = clazz.getDeclaredField("privateField");
        privateField.setAccessible(true);
        String value = (String) privateField.get(instance);
        System.out.println("Private Field Value: " + value);

        // 메서드에 동적으로 접근
        Method privateMethod = clazz.getDeclaredMethod("privateMethod");
        privateMethod.setAccessible(true);
        privateMethod.invoke(instance);

        // 클래스의 모든 필드와 메서드 출력
        System.out.println("All Fields:");
        for (Field field : clazz.getDeclaredFields()) {
            System.out.println("Field: " + field.getName());
        }

        System.out.println("All Methods:");
        for (Method method : clazz.getDeclaredMethods()) {
            System.out.println("Method: " + method.getName());
        }
    }
}