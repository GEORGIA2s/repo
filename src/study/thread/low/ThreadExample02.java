package study.thread.low;

class SimpleThread extends Thread {
    public void run() {
        for(int i = 1; i <= 5; i++) {
            System.out.println(Thread.currentThread().getId() + " [Simple Thread Value] = " + i);
        }
    }
}

class SimpleRunnable implements Runnable {
    @Override
    public void run() {
        for (int i = 0; i <= 5; i++) {
            System.out.println(Thread.currentThread().getId() + " [Simple Runnable Value] = " + i);
        }
    }
}

class Counter {
    private int count = 0;

    public synchronized void increment() {
        count++;
    }

    public int getCount() {
        return count;
    }
}

class Increment extends Thread {
    private Counter counter;

    public Increment(Counter counter) {
        this.counter = counter;
    }

    public void run() {
        for (int i = 0; i < 10000; i++) {
            counter.increment();
        }
    }
}
public class ThreadExample02{
    public static void main(String[] args) throws InterruptedException {
        SimpleThread simpleThread_1 = new SimpleThread();
        SimpleThread simpleThread_2 = new SimpleThread();

        Thread simpleRunnable_1 = new Thread(new SimpleRunnable());
        Thread simpleRunnable_2 = new Thread(new SimpleRunnable());

        simpleThread_1.start();
        simpleThread_2.start();

        simpleRunnable_1.start();
        simpleRunnable_2.start();

        Counter counter = new Counter();
        Increment incrementThread_1 = new Increment(counter);
        Increment incrementThread_2 = new Increment(counter);

        incrementThread_1.start();
        incrementThread_2.start();

        incrementThread_1.join();
        incrementThread_2.join();

        System.out.println("Counter : " + counter.getCount());
    }
}
