package study.generic.high;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;

class Person {
    private String name;
    private int age;

    public Person(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    @Override
    public String toString() {
        return "Person{" +
                "name='" + name + '\'' +
                ", age=" + age +
                '}';
    }
}

class DataProcessor<T> {
    private List<T> dataList = new ArrayList<>();

    public void addData(T data){
        dataList.add(data);
    }

    public void processData(Consumer<T> dataConsumer){
        for (T data : dataList){
            dataConsumer.accept(data);
        }
    }

    public <R> List<R> mapData(Function<T, R> mapper){
        List<R> result = new ArrayList<>();
        for (T data : dataList){
            result.add(mapper.apply(data));
        }
        return result;
    }

    public List<T> filterData(Predicate<T> predicate) {
        List<T> result = new ArrayList<>();
        for(T data : dataList){
            if(predicate.test(data)){
                result.add(data);
            }
        }
        return result;
    }
}
public class PersonExample {
    public static void main(String[] args) {
        DataProcessor<Person> personDataProcessor = new DataProcessor<>();

        personDataProcessor.addData(new Person("홍길동", 25));
        personDataProcessor.addData(new Person("강감찬", 30));
        personDataProcessor.addData(new Person("이순신", 22));

        System.out.println("CONSUMER TEST");
        Consumer<Person> personPrint = p -> System.out.println("Person : " + p);

        Function<Person, String> nameExtractor = Person :: getName;

        Predicate<Person> ageFilter = p -> p.getAge() >= 25;

        personDataProcessor.processData(personPrint);

        List<String> names = personDataProcessor.mapData(nameExtractor);
        System.out.println("FUNCTION TEST");
        System.out.println("Name : "+names);

        System.out.println("PREDICATE TEST");
        List<Person> filteredPerson = personDataProcessor.filterData(ageFilter);
        System.out.println("25age ↑ :" + filteredPerson);
    }
}
