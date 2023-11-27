package study.reflection.low;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

class TempClass_01 {
    private String privateField;

    public TempClass_01() {
        this.privateField = "Initial Value";
    }

    public void printPrivateField() {
        System.out.println("Private Field: " + privateField);
    }

    private void setPrivateFieldValue(String newValue) {
        this.privateField = newValue;
    }
}

public class ReflectionExample_01 {
    public static void main(String[] args) {
        // 클래스 이름 출력
        Class<TempClass_01> clazz = TempClass_01.class;
        System.out.println("Class Name: " + clazz.getName());

        try {
            // 인스턴스 생성
            TempClass_01 instance = clazz.newInstance();

            // private 필드에 동적으로 값 설정
            Field privateField = clazz.getDeclaredField("privateField");
            privateField.setAccessible(true);
            privateField.set(instance, "Updated Value");

            // private 메서드 호출
            Method privateMethod = clazz.getDeclaredMethod("setPrivateFieldValue", String.class);
            privateMethod.setAccessible(true);
            privateMethod.invoke(instance, "Another Value");

            // 메서드 호출을 통한 private 필드 값 출력
            Method printMethod = clazz.getMethod("printPrivateField");
            printMethod.invoke(instance);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
