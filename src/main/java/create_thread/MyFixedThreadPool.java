package create_thread;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 使用executors 创建线程池
 * 1、newCachedThreadPool创建一个可缓存线程池，如果线程池长度超过处理需要，可灵活回收空闲线程，若无可回收，则新建线程。
 * 2、newFixedThreadPool 创建一个定长线程池，可控制线程最大并发数，超出的线程会在队列中等待。
 * 3、newScheduledThreadPool 创建一个定长线程池，支持定时及周期性任务执行。
 * 4、newSingleThreadExecutor 创建一个单线程化的线程池，它只会用唯一的工作线程来执行任务，保证所有任务按照指定顺序(FIFO, LIFO, 优先级)执行。
 */
@Slf4j
public class MyFixedThreadPool {

    /**
     * 创建一个定长线程池，可控制线程最大并发数，超出的线程会在队列中等待
     * Executors.newFixedThreadPool(2);在线程池中保持二个线程可以同时执行，
     * 但是注意，并不是说线程池中永远都是这二个线程，只是说可以同时存在的线程数，当某个线程执行结束后，会有新的线程进来
     * @param args
     */
    public static void main(String[] args) {
        ExecutorService executorService = Executors.newFixedThreadPool(5);
        for (int i = 0; i< 100; i++){
            executorService.execute(new MyRunable());
        }
    }

    static class MyRunable implements Runnable {

        @Override
        public void run() {
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(Thread.currentThread().getName()+"线程运行了,线程地址为"+this.toString());
        }
    }

}
