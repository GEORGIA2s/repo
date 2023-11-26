package study.generic.middle;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

class DataProcessor<T> {
    private List<T> list = new ArrayList<>();

    public void addData(T data){
        list.add(data);
    }
    public void processData(Consumer<T> dataConsumer){
        for(T data : list){
            dataConsumer.accept(data);
        }
    }
}
public class DataProcessorExample {
    public static void main(String[] args) {
        DataProcessor<String> stringDataProcessor = new DataProcessor<>();

        DataProcessor<Integer> integerDataProcessor = new DataProcessor<>();

        stringDataProcessor.addData("ONE");
        stringDataProcessor.addData("TWO");
        stringDataProcessor.addData("THREE");

        integerDataProcessor.addData(1);
        integerDataProcessor.addData(2);
        integerDataProcessor.addData(3);

        Consumer<String> stringConsumer = s -> System.out.println("String Consumer : " + s);
        Consumer<Integer> integerConsumer = i -> System.out.println("Integer Consumer : " + i);

        stringDataProcessor.processData(stringConsumer);
        integerDataProcessor.processData(integerConsumer);
    }
}
