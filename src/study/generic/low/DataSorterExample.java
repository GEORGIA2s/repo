package study.generic.low;


import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

class DataSorter <T extends Comparable<T>> {
    List<T> list;

    public DataSorter() {
        this.list = new ArrayList<>();
    }

    public void add(T t) {
        list.add(t);
    }

    public void sortData() {
        Collections.sort(list);
    }

    public List<T> getList() {
        return list;
    }

}
public class DataSorterExample {
    public static void main(String[] args) {
        DataSorter<Integer> list = new DataSorter<>();
        list.add(42);
        list.add(18);
        list.add(28);


        list.sortData();
        List<Integer> sortList = list.getList();
        System.out.println("SORT LIST : "+sortList);

        DataSorter<String> strList = new DataSorter<>();
        strList.add("Apple");
        strList.add("Banana");
        strList.add("Grape");
        strList.add("Orange");

        strList.sortData();

        List<String> sortList_STR = strList.getList();
        System.out.println("SORT STR_LIST : " + sortList_STR);


    }
}
