package study.calender;

import study.utils.Print;
import study.utils.ScanUtil;

import java.util.Calendar;
import java.util.Date;

public class CalendarExample {
    public static void main(String[] args) {
        int inputMonth = ScanUtil.nextInt("1 - 12 MONTH");

        Date currentDate = new Date();

        Calendar calendar = Calendar.getInstance();
        calendar.setTime(currentDate);
        calendar.set(Calendar.MONTH, inputMonth - 1);
        calendar.set(Calendar.DAY_OF_MONTH, 1);

        System.out.println("Using Date and Calendar : ");
        printCalendar(calendar);
    }

    private static void printCalendar(Calendar calendar) {
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH)+1;

        System.out.printf("YEAR : %d, MONTH : %d", year, month);
        System.out.println();
        System.out.println("  일  월  화   수   목   금  토");
        
        int firstDayOfWeek = calendar.get(Calendar.DAY_OF_WEEK);
        calendar.add(Calendar.DAY_OF_MONTH, Calendar.SUNDAY - firstDayOfWeek);

        for(int week = 0; week < 6; week++) {
            for(int dayOfWeek = 0; dayOfWeek <7; dayOfWeek ++){
                if(calendar.get(Calendar.MONTH)+1 == month) {
                    if (dayOfWeek == 6) {
                        System.out.printf(Print.FONT_BLUE + "%4d" + Print.RESET, calendar.get(Calendar.DAY_OF_MONTH));
                    } else if (dayOfWeek == Calendar.SUNDAY - 1) {
                        System.out.printf(Print.FONT_RED + "%4d" + Print.RESET, calendar.get(Calendar.DAY_OF_MONTH));
                    } else {
                        System.out.printf("%4d", calendar.get(Calendar.DAY_OF_MONTH));
                    }
                }else{
                    System.out.print("    ");
                }


                calendar.add(Calendar.DAY_OF_MONTH, 1);
            }
            System.out.println();
        }
    }
}
