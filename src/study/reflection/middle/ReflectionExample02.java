package study.reflection.middle;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

class AdvancedClass {
    private String privateField;

    public AdvancedClass() {
        this.privateField = "Initial Value";
    }

    public void printPrivateField() {
        System.out.println("Private Field: " + privateField);
    }

    private void setPrivateFieldValue(String newValue) {
        this.privateField = newValue;
    }
}

public class ReflectionExample02 {
    public static void main(String[] args) {
        Class<?> clazz = AdvancedClass.class;

        try {
            // 인스턴스 생성
            AdvancedClass instance = (AdvancedClass) clazz.newInstance();

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
