package study.thread.middle;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

class Task_2 implements Runnable {
    private int taskId;

    public Task_2(int taskId) {
        this.taskId = taskId;
    }

    @Override
    public void run() {
        System.out.println("Task " + taskId + " is running on thread " + Thread.currentThread().getId());
    }
}
public class ThreadPoolExample {
    public static void main(String[] args) {
        int numOfThreads = 3;
        ExecutorService executor = Executors.newFixedThreadPool(numOfThreads);

        for(int i = 0; i <= 5; i++) {
            Task_2 task = new Task_2(i);
            executor.execute(task);
        }

        executor.shutdown();
    }
}


/*
    newFixedThreadPool(int nThreads):
    => 주어진 고정된 개수의 쓰레드 풀을 생성한다.
    => nThreads 는 동시에 실행 가능한 최대 쓰레드 수를 나타낸다.

    ExecutorService executor = Executors.newFixedThreadPool(5);
    : 5개의 쓰레드 생성

    newCachedThreadPool():
    => 필요에 따라 쓰레드를 동적으로 생성하고
    => 사용하지 않는 쓰레드는 제거하는 쓰레드 풀을 생성한다.
    
    ExecutorService executor = Executors.newCachedThreadPool();
    : 동적으로 생성, 대기 시간 후 쓰레드 삭제

    newSingleThreadExecutor():
    => 하나의 쓰레드만을 사용하는 쓰레드 풀을 생성한다.
    
    ExecutorService executor = Executors.newSingleThreadExecutor();
    : 하나의 쓰레드만 생성

    newScheduledThreadPool(int corePoolSize):
    => 주어진 코어 쓰레드 수를 갖는 쓰레드 풀을 생성하며,
    => 작업을 예약하고 주기적으로 실행 할 수 있는 기능을 제공한다.

    ScheduledExecutorService executor = Executors.newScheduledThreadPool(3);
    : 쓰레드 풀을 지정한 숫자만큼 생성하지만
    : 생성된 쓰레드 풀의 개수가 CPU 코어 수 보다 크다면
    : 모든 코어가 동시에 사용되지 않을 수 있다.
    : 반대로 명시된 숫자가 CPU 코어 수 보다 작다면
    : 일부 코어는 활용되지 않을 수 있다.

    submit(Callable<T> task):
    => 비동기적으로 실행할 작업을 제출한다.
    => 작업의 결과를 나타내는 Future를 반환한다.

    Future<String> future = executor.submit(() -> "Hello, World!");
    : future에 Hello World 값이 담기게 된다.
    : future.get() 메서드를 활용해 값을 꺼낼 수 있다.

    invokeAll(Collection<? extends Callable<T>> tasks):
    => 주어진 모든 작업을 비동기적으로 실행하고, 모든 작업이 완료될 떄 까지
    => 기다린 후 결과를 담은 List<Future<T>>를 반환한다.

    List<Callable<String> tasks =
    Array.asList(() -> "Task 1", () -> "Task 2", () -> "Task 3");
    List<Future<String> futures = executor.invokeAll(tasks);
    :Task 1~3의 값이 담긴 List<Future<String>> 을 반환한다.

    invokeAny(Collection<? extends Callable<T>> tasks):
    => 주어진 작업 중 하나라도 성공적으로 완료될 때까지 기다리고, 그 결과를 반환한다.

    List<Callable<String>> tasks =
    Array.asList(() -> "Task 1", () -> "Task 2", () -> "Task 3");
    String result = executor.invokeAny(tasks);
    :Task 1~3 중 먼저 완료 된 값이 result에 담긴다.

    shutdown():
    => 현재 진행 중인 작업을 완료하고 쓰레드 풀을 종료한다.

    executor.shutdown();

    schedule<Callable<V> callable, long delay, TimeUnit unit):
    => 주어진 시간이 지난 후에 작업을 실행하고 결과를 나타내는
    => ScheduledFuture를 반환한다.

    ScheduledFuture<String> future =
    scheduledExecutor.schedule(() -> "Delayed Task", 5, TimeUnit.SECONDS);
    : 5초 후 String 값 "Delayed Task" 를 담은 Future를 반환한다.
 */