package study.level.middle;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

enum Gender {
    MALE, FEMALE
}

// 제네릭 클래스 정의
class Person<T> {
    private String name;
    private T attribute;

    public Person(String name, T attribute) {
        this.name = name;
        this.attribute = attribute;
    }

    public String getName() {
        return name;
    }

    public T getAttribute() {
        return attribute;
    }
}
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.LOCAL_VARIABLE, ElementType.FIELD})
@interface CustomAnnotation {
    String value() default "";
}
public class PersonExample {
    @CustomAnnotation(value = "Custom Value")
    static String annotatedField;

    public static void main(String[] args) throws NoSuchFieldException {
        Gender gender = Gender.MALE;

        Person<String> person1 = new Person<>("홍길동", "도사");
        Person<Integer> person2 = new Person<>("심청이", 25);

        System.out.println("Gender: " + gender);
        System.out.println("Person 1: " + person1.getName() + ", Attribute: " + person1.getAttribute());
        System.out.println("Person 2: " + person2.getName() + ", Attribute: " + person2.getAttribute());

        CustomAnnotation customAnnotation = PersonExample.class.getDeclaredField("annotatedField")
                .getAnnotation(CustomAnnotation.class);

        System.out.println("Annotation Value: " + customAnnotation.value());
    }
}
