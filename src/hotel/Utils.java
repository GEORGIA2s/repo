package hotel;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Utils {

    static Map<Integer,String> map = new HashMap<>();
    static Scanner sc = new Scanner(System.in);
    
    public boolean containsKey(int key) {
        return map.containsKey(key);
    }

    public int inputKey(){
        System.out.println("어느 방에 체크인 하시겠습니까?");
        System.out.print("방번호 입력 => ");
        return Integer.parseInt(sc.nextLine());
		// nextLine()으로 받은 이유는 개행문자 제거
    }

    public String inputValue(){
        System.out.println("누구를 체크인 하시겠습니까?");
        System.out.print("이름 입력 => ");
        return sc.nextLine();
    }

    public void printMenu() {
        printAsterisk();
        System.out.println("어떤 업무를 하시겠습니까?");
        System.out.println("1.체크인  2.체크아웃 3.객실상태 4.모든객실 5.업무종료");
        printAsterisk();
    }

    public void printOpen() {
        printAsterisk();
        System.out.println("호텔 문을 열었습니다.");
        printAsterisk();
    }

    public void printAsterisk() {
        System.out.println("**************************");
    }

    public void printExit() {
        printAsterisk();
        System.out.println("호텔 문을 닫았습니다.");
        printAsterisk();
    }
}
