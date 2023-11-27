package study.reflection.middle;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

class TempClass {
    private String privateField;
    public int publicField;

    public TempClass() {
    }

    public void publicMethod() {
        System.out.println("Public method called");
    }

    private void privateMethod() {
        System.out.println("Private method called");
    }
}

public class ReflectionExample03 {
    public static void main(String[] args) {
        Class<?> clazz = TempClass.class;

        // 클래스 이름 출력
        System.out.println("Class Name: " + clazz.getName());

        // 필드 목록 출력
        System.out.println("\nFields:");
        Field[] fields = clazz.getDeclaredFields();
        for (Field field : fields) {
            System.out.println(field.getName() + " - " + field.getType());
        }

        // 메서드 목록 출력
        System.out.println("\nMethods:");
        Method[] methods = clazz.getDeclaredMethods();
        for (Method method : methods) {
            System.out.println(method.getName() + " - " + method.getReturnType());
        }
    }
}
