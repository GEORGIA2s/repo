package study.thread.middle;

import sun.nio.ch.ThreadPool;

import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

class Task implements Runnable {
    private String taskName;

    public Task(String taskName) {
        this.taskName = taskName;
    }


    @Override
    public void run() {
        System.out.println("Task : " + taskName + " is running on Thread " + Thread.currentThread().getId());
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}

public class CustomThreadPoolExample {
    public static void main(String[] args) {
        int corePoolSize = 2;
        int maxPoolSize = 5;
        long keepAliveTime = 5000;

        ThreadPoolExecutor executor = (ThreadPoolExecutor) Executors.newFixedThreadPool(corePoolSize);

        executor.setCorePoolSize(corePoolSize);
        executor.setMaximumPoolSize(maxPoolSize);
        executor.setKeepAliveTime(keepAliveTime, TimeUnit.MILLISECONDS);

        for(int i = 0; i <= 10; i++){
            Task task = new Task(Integer.toString(i));
            executor.execute(task);
        }


        executor.shutdown();
    }
}
