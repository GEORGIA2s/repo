package study.generic.low;


class Pair<T, U> {
    private T first;
    private U second;

    public Pair(T first, U second) {
        this.first = first;
        this.second = second;
    }

    public T getFirst() {
        return first;
    }

    public U getSecond() {
        return second;
    }

    public static <K,V> boolean compare(Pair<K,V> p1, Pair<K,V> p2){
        return p1.first.equals(p2.getFirst()) && p1.second.equals(p2.getSecond());
    }
}

public class PairExample {
    public static void main(String[] args) {
        Pair<Integer, String> pair1 = new Pair<>(1,"One");
        Pair<Integer, String> pair2 = new Pair<>(2,"Two");
        Pair<Integer, String> pair3 = new Pair<>(1,"One");

        boolean result = Pair.compare(pair1,pair2);

        System.out.println("RESULT : "+result);

        boolean result_2 = Pair.compare(pair1,pair3);

        System.out.println("RESULT : "+result_2);
    }
}
