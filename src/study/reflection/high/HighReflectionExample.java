package study.reflection.high;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.reflect.Field;
import java.lang.reflect.Method;

@Retention(RetentionPolicy.RUNTIME)
@interface MyAnnotation {
    String value() default "";
}

class MyBaseClass {
    @MyAnnotation("Base Field")
    private String baseField;

    @MyAnnotation("Base Method")
    private void baseMethod() {
        System.out.println("Base Method Invoked");
    }
}

class MyDerivedClass extends MyBaseClass {
    @MyAnnotation("Derived Field")
    private int derivedField;

    @MyAnnotation("Derived Method")
    private void derivedMethod() {
        System.out.println("Derived Method Invoked");
    }
}

public class HighReflectionExample {

    public static void main(String[] args) throws Exception {
        inspectClass(MyDerivedClass.class);
    }

    private static void inspectClass(Class<?> clazz) throws Exception {
        // 필드 및 메서드 정보 수집
        System.out.println("Class: " + clazz.getSimpleName());

        for (Field field : clazz.getDeclaredFields()) {
            MyAnnotation annotation = field.getAnnotation(MyAnnotation.class);
            if (annotation != null) {
                System.out.println("Field: " + field.getName() + " - Annotation: " + annotation.value());
            }
        }

        for (Method method : clazz.getDeclaredMethods()) {
            MyAnnotation annotation = method.getAnnotation(MyAnnotation.class);
            if (annotation != null) {
                System.out.println("Method: " + method.getName() + " - Annotation: " + annotation.value());
            }
        }

        // 상속 관계 탐색
        Class<?> superClass = clazz.getSuperclass();
        if (superClass != null && superClass != Object.class) {
            System.out.println("Superclass:");
            inspectClass(superClass);
        }
    }
}
