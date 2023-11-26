package study.calender;

import study.utils.Print;
import study.utils.ScanUtil;

import java.time.LocalDateTime;
import java.util.Calendar;

public class LocalDateTimeExample {
    public static void main(String[] args) {
        int inputMonth = ScanUtil.nextInt("1 - 12 MONTH");

        LocalDateTime currentDateTime = LocalDateTime.now();

        LocalDateTime targetDateTime = currentDateTime.withMonth(inputMonth);

        LocalDateTime firstDayOfMonth = targetDateTime.withDayOfMonth(1);

        System.out.println("Using LocalDateTime : ");
        printDateTime(firstDayOfMonth);
    }

    private static void printDateTime(LocalDateTime currentDateTime) {
        int year = currentDateTime.getYear();
        int month = currentDateTime.getMonthValue();
        System.out.printf("YEAR : %d, MONTH : %d", year, month);
        System.out.println();
        System.out.println(Print.FONT_RED+"  일"+Print.RESET+"  월  화   수   목   금  "+Print.FONT_BLUE+"토"+Print.RESET);

        int firstDayOfWeek = currentDateTime.getDayOfWeek().getValue();
        LocalDateTime currentDay = currentDateTime.minusDays(firstDayOfWeek);

        for (int week = 0; week < 6; week++) {
            for (int dayOfWeek = 0; dayOfWeek < 7; dayOfWeek++) {
                if (currentDay.getMonthValue() == month) {
                    if (dayOfWeek == 6) {
                        System.out.printf(Print.FONT_BLUE + "%4d" + Print.RESET, currentDay.getDayOfMonth());
                    } else if (dayOfWeek == Calendar.SUNDAY - 1) {
                        System.out.printf(Print.FONT_RED + "%4d" + Print.RESET, currentDay.getDayOfMonth());
                    } else {
                        System.out.printf("%4d", currentDay.getDayOfMonth());
                    }
                }else {
                    System.out.print("    ");
                }
                currentDay = currentDay.plusDays(1);
            }
            System.out.println();
        }
    }
}
