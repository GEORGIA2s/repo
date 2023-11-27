package study.reflection.middle;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.reflect.Field;
import java.lang.reflect.Method;

@Retention(RetentionPolicy.RUNTIME)
@interface MyFieldInfo {
    String value() default "";
}

class ReflectExampleClass {
    @MyFieldInfo("This is a sample field.")
    private String sampleField;

    public ReflectExampleClass() {
        this.sampleField = "Default Value";
    }

    public void printSampleField() {
        System.out.println("Sample Field: " + sampleField);
    }
}

public class MiddleReflectionExample01 {
    public static void main(String[] args) {
        Class<ReflectExampleClass> clazz = ReflectExampleClass.class;

        // 필드 정보 수집
        Field[] fields = clazz.getDeclaredFields();
        for (Field field : fields) {
            // 어노테이션 정보 확인
            if (field.isAnnotationPresent(MyFieldInfo.class)) {
                MyFieldInfo fieldInfo = field.getAnnotation(MyFieldInfo.class);
                System.out.println("Field: " + field.getName() + ", Description: " + fieldInfo.value());
            }
        }

        try {
            // 인스턴스 동적 생성
            ReflectExampleClass instance = clazz.getDeclaredConstructor().newInstance();

            // 메서드 정보 수집
            Method[] methods = clazz.getDeclaredMethods();
            for (Method method : methods) {
                // 메서드 이름이 'print'로 시작하는 메서드 호출
                if (method.getName().startsWith("print")) {
                    method.setAccessible(true);
                    method.invoke(instance);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
