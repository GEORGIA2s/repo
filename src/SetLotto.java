import java.util.*;

public class SetLotto {

    public static final int LOTTO_MAX = 45;
    public static final int LOTTO_SIZE = 6;
    static Scanner sc = new Scanner(System.in);
    static Random ran = new Random();
    //ThreadLocalRandom ran = new ThreadLocalRandom.current();

    public static void main(String[] args) {
        while (true) {
            printHome();
            int select = sc.nextInt();

            switch (select) {
                case 1:
                    printLottoBuy();
                    int money = sc.nextInt();
                    System.out.println("받은 금액은 : " + money + "이고 거스름돈은 : " + lottoIssuance(money) + " 입니다.");
                    break;
                case 2:
                    printExit();
                    return;
                default:
                    System.out.println("올바르지 않은 선택 입니다.");
                    break;
            }
        }
    }

    public static int lottoIssuance(int money) {
        int lottoTickets = money / 1000;
        getLottoBundle(lottoTickets);
        money = money % 1000;
        return money;
    }

    public static void getLottoBundle(int lottoTickets){
        int count = 1;
        StringBuilder sb = new StringBuilder();

        System.out.println("행운의 로또 번호는 아래와 같습니다");
        for (int i = 0; i < lottoTickets; i++) {
            Set<Integer> lottoTicket = new HashSet<>();
            while (lottoTicket.size() < LOTTO_SIZE) {
                int number = ran.nextInt(LOTTO_MAX) + 1;
                if (!lottoTicket.contains(number)) {
                    lottoTicket.add(number);
                }
            }

            printLotto(sb,count++,lottoTicket);
        }
    }

    public static void printLotto(StringBuilder sb, int count, Set<Integer> lottoTicket) {
        sb.setLength(0);
        sb.append("로또 번호 ").append(count).append(" : ");
        for(Integer lotto : lottoTicket){
            sb.append(lotto+", ");
        }

        if(sb.length() > 0) {
            sb.delete(sb.length() - 2, sb.length());
        }
        System.out.print(sb.toString());
        System.out.println();
    }
    public static void printVar() {
        System.out.println("================================================================");
    }

    public static void printHome() {
        printVar();
        System.out.println("Lotto 프로그램");
        System.out.println("---------------------------");
        System.out.println("1. Lotto 구입");
        System.out.println("2. 프로그램 종료");
        printVar();
    }

    public static void printLottoBuy() {
        System.out.println("Lotto 구입 시작");
        System.out.println("(1000원에 로또번호 하나입니다.)");
        System.out.print("금액 입력 => ");
    }

    public static void printExit(){
        System.out.println("감사합니다.");
    }
}
