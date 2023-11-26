package study.thread.middle;

import java.util.concurrent.*;

class SumTask implements Callable<Integer> {
    private int num;

    public SumTask(int num) {
        this.num = num;
    }

    @Override
    public Integer call() throws Exception {
        int sum = 0;
        for(int i = 0; i <= num; i++){
            sum+=i;
        }
        return sum;
    }
}
public class CallableExample {
    public static void main(String[] args) throws Exception {
        int num = 10;
        ExecutorService executor = Executors.newSingleThreadExecutor();

        SumTask task = new SumTask(num);
        Future<Integer> future = executor.submit(task);

        System.out.println("다른 작업 수행 중");

        int result = future.get();
        System.out.println("Sum of first " + num + " number is " + result);

        executor.shutdown();
    }
}
