package study.generic.low;

import study.utils.Print;

import java.util.HashMap;
import java.util.Map;

class GenericMap<K, V> {
    private Map<K, V> map;

    public GenericMap() {
        this.map = new HashMap<>();
    }

    public void put(K key, V value){
        map.put(key,value);
    }

    public V get(K key){
        return map.get(key);
    }

    public void printMap(){
        for(Map.Entry<K,V> e : map.entrySet()){
            System.out.println("KEY : "+e.getKey() + " : Value : " + e.getValue());
        }
    }
}

public class GenericMapExample extends Print {
    public static void main(String[] args) {
        GenericMap<String,Integer> map = new GenericMap<>();
        map.put("Hello",1);
        map.put("Hi",10);

        System.out.println(map.get("Hi"));

        GenericMap<String,String> m = new GenericMap<>();
        m.put("Hello","Hi");


        System.out.println(m.get("Hello"));
        var();
        m.printMap();
        var2();
        map.printMap();
        var();
    }
}
