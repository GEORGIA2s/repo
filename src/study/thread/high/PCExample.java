package study.thread.high;

import java.util.LinkedList;
import java.util.Queue;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

class SharedResource_2 {
    private Queue<Integer> buffer = new LinkedList<>();
    private int capacity = 5;

    private Lock lock = new ReentrantLock();
    // 재 진입이 가능한 락(LOCK)을 제공하는 클래스
    // lock 객체를 생성하여 쓰레드 간의 상호배제를 달성하는 사용한다.

    private Condition notFull = lock.newCondition();
    // 버퍼가 가득 찼을때 해당 쓰레드가 대기하는 조건.
    private Condition notEmpty = lock.newCondition();
    // 버퍼가 비었을 때 해당 쓰레드가 대기하는 조건.

    public void produce(int item) throws InterruptedException {
        lock.lock();
        // 임계적인 영역에 진입하기 위해 락을 획득함
        // 이 부분에서 다른 쓰레드를 동일한 락을 획득하려고 대기상태가 됨.
        try {
            while (buffer.size() == capacity) {
                notFull.wait();
            }

            buffer.add(item);
            System.out.println("Produced : " + item);

            notEmpty.signal();
            // 조건을 기다리고 있는 하나의 쓰레드를 깨움.
            // 버퍼가 비어있지 않다고 다른 쓰레드에게 알림.
        } finally {
            lock.unlock();
            // 임계적인 영역을 벗어날 때 락을 해제함.
            // 다른 쓰레드에게 락을 획득할 기회를 주게 됨.
        }
    }

    public int consume() throws InterruptedException {
        lock.lock();
        try {
            while (buffer.isEmpty()) {
                notEmpty.await();
                // 현재 쓰레드를 notEmpty 조건에 대기시킴
                // 버퍼가 비어있으면 대기시키고 signal()로 호출되면 깨어남
            }

            int item = buffer.poll();
            System.out.println("Consumed : " + item);

            notFull.signal();
            // 조건을 기다리고 있는 하나의 쓰레드한테 알림.
            return item;
        } finally {
            lock.unlock();
        }
    }
}

class Producer implements Runnable {
    private SharedResource_2 sr;

    public Producer(SharedResource_2 sr) {
        this.sr = sr;
    }

    @Override
    public void run() {
        for (int i = 1; i <= 10; i++) {
            try {
                sr.produce(i);
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }
}

class Consumer implements Runnable {
    private SharedResource_2 sr;

    public Consumer(SharedResource_2 sr) {
        this.sr = sr;
    }


    @Override
    public void run() {
        for (int i = 1; i <= 10; i++) {
            try {
                sr.consume();
                Thread.sleep(200);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }
}

public class PCExample {
    public static void main(String[] args) {
        SharedResource_2 sr = new SharedResource_2();

        Thread pTh = new Thread(new Producer(sr));
        Thread cTh = new Thread(new Consumer(sr));

        pTh.start();
        cTh.start();
    }
}
