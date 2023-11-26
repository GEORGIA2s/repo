package study.thread.middle;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

class SumTask_2 implements Callable<Integer> {

    private int start;
    private int end;

    public SumTask_2(int start, int end) {
        this.start = start;
        this.end = end;
    }

    @Override
    public Integer call() throws Exception {
        int sum = 0;
        for (int i = start; i <= end; i++) {
            sum += i;
        }
        return sum;
    }
}

public class CallableFutureExample {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        int taskCount = 5;
        int start = 1;
        int end = 100;

        ExecutorService executor = Executors.newFixedThreadPool(taskCount);

        List<Future<Integer>> futures = new ArrayList<>();

        for (int i = 0; i < taskCount; i++) {
            int taskStart = start + i * (end - start + 1) / taskCount;
            int taskEnd = (i == taskCount - 1) ? end : start + (i + 1) * (end - start + 1) / taskCount - 1;

            SumTask_2 sumTask = new SumTask_2(taskStart, taskEnd);
            Future<Integer> future = executor.submit(sumTask);
            futures.add(future);
        }

        int totalSum = 0;
        for (Future<Integer> future : futures) {
            int result = future.get();
            System.out.println("Task Result: " + result);
            totalSum += result;
        }

        System.out.println("Total Sum: " + totalSum);

        executor.shutdown();
    }
}