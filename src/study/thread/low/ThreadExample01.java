package study.thread.low;

class NumberThread extends Thread {
    public void run() {
        for (int i = 0; i <5; i++) {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            System.out.println("Thread 1: " + i);
        }
    }
}

class LetterThread extends Thread {
    public void run() {
        for (char ch = 'A'; ch <= 'E'; ch++) {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            System.out.println("Thread 2 : " + ch);
        }
    }
}
public class ThreadExample01 {
    public static void main(String[] args) throws InterruptedException {
        NumberThread nTh = new NumberThread();
        LetterThread lTh = new LetterThread();

        nTh.start();
        lTh.start();

        nTh.join();
        lTh.join();

    }
}
