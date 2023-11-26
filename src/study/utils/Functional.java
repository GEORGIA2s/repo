package study.utils;

import java.util.function.Function;
import java.util.function.IntUnaryOperator;
import java.util.function.ToIntFunction;

public class Functional {
    public static Integer stringToInt(String str) {
        ToIntFunction<String> f = Integer::parseInt;
        return f.applyAsInt(str);
    }

    public static Integer squared(int i){
        IntUnaryOperator f = x -> x * x;
        return f.applyAsInt(i);
    }

    public static Integer stringToSquaredInt(String str) {
        IntUnaryOperator f = x -> x * x;
        return f.applyAsInt(squared(stringToInt(str)));
    }

}
