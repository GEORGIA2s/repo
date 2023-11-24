class SharedResource02{
    private boolean flag = false;

    public synchronized void printEven(int number) throws InterruptedException {
        while(!flag){
            wait();
        }
        System.out.println("Even number : " + number);
        flag = false;
        notify();
    }

    public synchronized void printOdd(int number) throws InterruptedException {
        while(flag){
            wait();
        }
        System.out.println("Odd number : " + number);
        flag = true;
        notify();
    }
}
class EvenThread extends Thread{
    private SharedResource02 sharedResource;

    public EvenThread(SharedResource02 sharedResource){
        this.sharedResource = sharedResource;
    }

    public void run() {
        for (int i = 2; i <=10 ; i+=2) {
            try {
                sharedResource.printEven(i);
            }catch (InterruptedException e){
                e.printStackTrace();
            }
        }
    }
}
class OddThread extends Thread {
    private SharedResource02 sharedResource;

    public OddThread(SharedResource02 sharedResource) {
        this.sharedResource = sharedResource;
    }

    public void run() {
        for (int i = 1; i <= 9; i += 2) {
            try {
                sharedResource.printOdd(i);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}

public class ThreadStudy02 {
    public static void main(String[] args) {
        SharedResource02 sharedResource = new SharedResource02();

        EvenThread thread01 = new EvenThread(sharedResource);
        OddThread thread02 = new OddThread(sharedResource);

        thread02.start();
        thread01.start();
    }
}
