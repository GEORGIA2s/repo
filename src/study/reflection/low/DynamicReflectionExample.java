package study.reflection.low;

import java.lang.reflect.Method;
import java.util.Scanner;

public class DynamicReflectionExample {
    public static void main(String[] args) {
        try {
            Scanner scanner = new Scanner(System.in);

            // 사용자로부터 클래스 이름 입력 받기
            System.out.println("Enter the fully qualified class name:");
            String className = scanner.nextLine();

            // 입력받은 클래스 이름으로 동적으로 클래스 로드
            Class<?> loadedClass = Class.forName(className);

            // 해당 클래스의 메서드 목록 출력
            Method[] methods = loadedClass.getDeclaredMethods();
            System.out.println("Methods in the loaded class:");
            for (Method method : methods) {
                System.out.println(method.getName());
            }

            // 사용자로부터 실행할 메서드 이름 입력 받기
            System.out.println("Enter the method name to execute:");
            String methodName = scanner.nextLine();

            // 입력받은 메서드 이름으로 메서드 동적으로 가져오기
            Method methodToExecute = loadedClass.getDeclaredMethod(methodName);

            // 인스턴스 생성 (기본 생성자 호출)
            Object instance = loadedClass.getDeclaredConstructor().newInstance();

            // 메서드 실행
            methodToExecute.setAccessible(true);
            methodToExecute.invoke(instance);

        } catch (ClassNotFoundException e) {
            System.err.println("Class not found: " + e.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}