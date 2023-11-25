package study.generic.low;

import study.utils.Print;

import java.util.ArrayList;
import java.util.List;

public class GenericExample extends Print {

    public static void main(String[] args) {
        List<String> stringList = new ArrayList<>();
        stringList.add("HELLO");
        stringList.add("HI");

        List<Integer> integerList = new ArrayList<>();
        integerList.add(10);
        integerList.add(100);

        printList(stringList);
        printList(integerList);


    }

    public static <T> void printList(List<T> list){
        for(T t : list) {
            System.out.println(t);
        }
    }

}
