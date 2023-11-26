package study.thread.low;

class DaemonTask implements Runnable {

    @Override
    public void run() {
        while(true) {
            System.out.println("데몬 쓰레드 동작중");

            try{
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
public class DeamonThreadExample {
    public static void main(String[] args) {
        System.out.println("메인 쓰레드 시작");

        Thread daemonThread = new Thread(new DaemonTask());

        daemonThread.setDaemon(true);

        daemonThread.start();

        try{
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        System.out.println("메인 쓰레드 종료");
    }
}
