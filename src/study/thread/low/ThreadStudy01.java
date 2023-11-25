package study.thread.low;

class SharedResource01 {
    private int count = 0;

    public synchronized void increment(){
        count++;
    }

    public int getCount(){
        return count;
    }
}

class IncrementThread extends Thread {
    private SharedResource01 sharedResource;

    public IncrementThread(SharedResource01 sharedResource){
        this.sharedResource = sharedResource;
    }

    public void run(){
        for (int i = 0; i <10000 ; i++) {
            sharedResource.increment();
        }
    }
}

public class ThreadStudy01 {
    public static void main(String[] args) {
        SharedResource01 sharedResource = new SharedResource01();

        IncrementThread thread1 = new IncrementThread(sharedResource);
        IncrementThread thread2 = new IncrementThread(sharedResource);

        thread1.start();
        thread2.start();
        try {
            thread1.join();
            thread2.join();
        }catch(InterruptedException e){
            e.printStackTrace();
        }

        System.out.println("Final Counter Value : " + sharedResource.getCount());
    }
}
