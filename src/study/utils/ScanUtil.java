package study.utils;

import java.util.Scanner;

public class ScanUtil extends Print{
    public static Scanner sc = new Scanner(System.in);
    public static String nextLine() {
        return nextLine();
    }

    public static int nextInt(String print) {
        System.out.println(print);
        return nextInt();
    }
    private static int nextInt() {
        while (true) {
            try {
                int result = Integer.parseInt(sc.nextLine());
                return result;
            } catch (NumberFormatException e) {
                System.out.println(FONT_RED+"유효한 정수를 입력하세요."+RESET);
            }
        }
    }
}
