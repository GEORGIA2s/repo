package study.testing;

import study.utils.Functional;

public class test01 extends Functional {
    public static void main(String[] args) {
        String str = "10";
        System.out.println(stringToInt(str).getClass().getSimpleName());
        System.out.println(stringToInt(str));

        String squared = "100";
        System.out.println(squared(stringToInt(squared)).getClass().getSimpleName());
        System.out.println(squared(stringToInt(squared)));

        String max = "200";
        System.out.println(stringToSquaredInt(max).getClass().getSimpleName());
        System.out.println(stringToSquaredInt(max));


    }
}
