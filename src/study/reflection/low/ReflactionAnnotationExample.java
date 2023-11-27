package study.reflection.low;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.reflect.Method;

@Retention(RetentionPolicy.RUNTIME)
@interface MyAnnotation {
    // 사용자 정의 어노테이션 정의
}

class ReflectExampleClass {
    @MyAnnotation
    public void annotatedMethod() {
        System.out.println("Annotated method called");
    }

    public void nonAnnotatedMethod() {
        System.out.println("Non-annotated method called");
    }
}

public class ReflactionAnnotationExample {
    public static void main(String[] args) {
        Class<ReflectExampleClass> clazz = ReflectExampleClass.class;

        // 클래스의 메서드 목록 가져오기
        Method[] methods = clazz.getDeclaredMethods();

        // 어노테이션이 부여된 메서드 호출
        for (Method method : methods) {
            if (method.isAnnotationPresent(MyAnnotation.class)) {
                try {
                    // 인스턴스 생성
                    ReflectExampleClass instance = clazz.getDeclaredConstructor().newInstance();

                    // 어노테이션이 부여된 메서드 호출
                    method.setAccessible(true);
                    method.invoke(instance);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }
}