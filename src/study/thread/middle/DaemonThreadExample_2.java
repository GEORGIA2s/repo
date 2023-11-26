package study.thread.middle;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;

public class DaemonThreadExample_2 {
    private static final int THREAD_POOL_SIZE = 1;
    // 쓰레드 풀 1개
    private static final long INITIAL_DELAY = 0;
    // 스케쥴링 된 쓰레드의 작업 시작 전 대기 시간
    private static final long PERIODIC_DELAY = 1000;
    // 주기적인 작업 간격 1초

    private static final ReentrantLock lock = new ReentrantLock();

    public static void main(String[] args) {
        ScheduledExecutorService executor =
                Executors.newScheduledThreadPool(THREAD_POOL_SIZE);

        executor.scheduleAtFixedRate(() -> {
            try{
                lock.lock();
                System.out.println("데몬 쓰레드 동작중...");

                performTask();
            } finally {
                lock.unlock();
            }

        },INITIAL_DELAY,PERIODIC_DELAY, TimeUnit.MILLISECONDS);


        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        executor.shutdown();

        System.out.println("메인 쓰레드 종료");
    }



    private static void performTask() {
        SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");
        String currentTime = dateFormat.format(new Date());

        System.out.println("현재 시간 : " + currentTime);
    }

}
