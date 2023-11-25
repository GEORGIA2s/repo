package study.generic.low;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Predicate;

class DataFilter {
    public static <T> List<T> filterList(List<? extends T> list, Predicate<T> predicate) {
        List<T> result = new ArrayList<>();
        for (T data : list) {
            if (predicate.test(data)) {
                result.add(data);
            }
        }
        return result;
    }
}

public class DataFilterExample {
    public static void main(String[] args) {
        List<Integer> intList = Arrays.asList(1,2,3,4,5,6,7,8,9,10);
        Predicate<Integer> evenPredicate = n -> n % 2 == 0;

        List<Integer> evenNumbers = DataFilter.filterList(intList,evenPredicate);
        System.out.println("Filtered Even List : " + evenNumbers);


        List<String> strList = Arrays.asList("apple","banana","grape","data","orange");
        Predicate<String> lengthPredicate = s -> s.length() > 5;

        List<String> longWords = DataFilter.filterList(strList,lengthPredicate);
        System.out.println("Filtered Long Words : " + longWords);
    }
}
