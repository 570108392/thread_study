package create_thread;

import ch.qos.logback.core.util.TimeUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;

import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * 使用executors 创建线程池
 * 1、newCachedThreadPool创建一个可缓存线程池，如果线程池长度超过处理需要，可灵活回收空闲线程，若无可回收，则新建线程。
 * 2、newFixedThreadPool 创建一个定长线程池，可控制线程最大并发数，超出的线程会在队列中等待。
 * 3、newScheduledThreadPool 创建一个定长线程池，支持定时及周期性任务执行。
 * 4、newSingleThreadExecutor 创建一个单线程化的线程池，它只会用唯一的工作线程来执行任务，保证所有任务按照指定顺序(FIFO, LIFO, 优先级)执行。
 */
@Slf4j
public class MyScheduledThreadPool {

    public static void main(String[] args) {
        //创建一个定长的线程且支持定时及其周期性执行任务的线程
        ScheduledExecutorService executorService = Executors.newScheduledThreadPool(5);
        for (int i = 0; i< 100; i++){
            executorService.schedule(new MyRunable(),new Random().nextInt(9), TimeUnit.SECONDS);
        }

        System.out.println("zhi xing jie shu le ");
    }

    /**
     * newScheduledThreadPool共计有三个方法：
     *     schedule(Runnable,delay,unit) ，这个方法是说系统启动后，需要等待多久执行，delay是等待时间。只执行一次，没有周期性。
     *     scheduleAtFixedRate(commod,initialDelay,period,unit)，这个是以period为固定周期时间，按照一定频率来重复执行任务，
     *     initialDelay是说系统启动后，需要等待多久才开始执行。例如：如果设置了period为5秒，线程启动之后执行了大于5秒，线程结束之后，
     *     立即启动线程的下一次，如果线程启动之后只执行了3秒就结束了那执行下一次，需要等待2秒再执行。这个是优先保证任务执行的频率，
     *     scheduleWithFixedDelay(commod,initialDelay,delay,unit)，这个是以delay为固定延迟时间，按照一定的等待时间来执行任务，
     *     initialDelay意义与上面的相同。例如：设置了delay为5秒，线程启动之后不管执行了多久，结束之后都需要等待5秒，才能执行下一次。
     *     这个是优先保证任务执行的间隔。
     */
    static class MyRunable implements Runnable {


        @Override
        public void run() {
            System.out.println(Thread.currentThread().getName()+"线程运行了,线程地址为"+this.toString());
        }
    }

}
