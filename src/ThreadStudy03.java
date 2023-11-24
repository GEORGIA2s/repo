import java.util.LinkedList;
import java.util.Queue;

class SharedResource03 {
    private Queue<Integer> queue = new LinkedList<>();
    private final int CAPACITY = 5;

    public synchronized void produce() throws InterruptedException {
        while (queue.size() == CAPACITY) {
            wait();
        }

        int item = (int) (Math.random() * 100);
        queue.add(item);
        System.out.println("Produced : " + item);

        notify();
    }

    public synchronized void consume() throws InterruptedException {
        while (queue.isEmpty()) {
            wait();
        }

        int item = queue.poll();
        System.out.println("Consumed : " + item);

        notify();
    }

}

class ProducerThread extends Thread {
    private SharedResource03 sharedResource;

    public ProducerThread(SharedResource03 sharedResource) {
        this.sharedResource = sharedResource;
    }

    public void run() {
        for (int i = 0; i < 5; i++) {
            try {
                sharedResource.produce();
                Thread.sleep((long) (Math.random() * 1000));
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }
}

class ConsumerThread extends Thread {
    private SharedResource03 sharedResource;

    public ConsumerThread(SharedResource03 sharedResource) {
        this.sharedResource = sharedResource;
    }

    public void run() {
        for (int i = 0; i < 5; i++) {
            try {
                sharedResource.consume();
                Thread.sleep((long) (Math.random() * 1000));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}


public class ThreadStudy03 {

    public static void main(String[] args) {
        SharedResource03 sharedResource = new SharedResource03();

        ProducerThread producerThread = new ProducerThread(sharedResource);
        ConsumerThread consumerThread = new ConsumerThread(sharedResource);

        producerThread.start();
        consumerThread.start();
    }
}
