import java.util.Map;

public class HotelManagement extends Utils {
    static Map<Integer, String> map = Utils.map;

    public static void main(String[] args) {
        new HotelManagement().manage();
    }

    private void manage() {
        printOpen();
        while (true) {
            printMenu();
            int select = Integer.parseInt(sc.nextLine());
            switch (select) {
                case 1:
                    checkIn();
                    break;
                case 2:
                    checkOut();
                    break;
                case 3:
                    roomStatus();
                    break;
                case 4:
                    printExit();
                    return;
                default:
                    System.out.println("잘못된 메뉴 선택 입니다.");
            }
        }
    }

    public void checkIn() {
        int key = inputKey();
        String value = inputValue();

        boolean b = containsKey(key);

        if (b)
            System.out.println(key + "방에는 이미 사람이 있습니다.");
        else {
            map.put(key, value);
            System.out.println("체크인 되었습니다.");
        }
    }

    public void checkOut() {
        int key = inputKey();
        boolean b = containsKey(key);

        if (b) {
            map.remove(key);
            System.out.println("체크아웃 되었습니다.");
        } else {
            System.out.println(key + "방에는 체크인한 사람이 없습니다.");
        }
    }

    public void roomStatus() {
        for(Map.Entry<Integer,String> m : map.entrySet()) {
            System.out.println("방번호 : "+m.getKey() +", 투숙객 : "+m.getValue());
        }
    }

}
