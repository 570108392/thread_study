package thread_talk;

import java.util.Random;
import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.Semaphore;

/**
 * @createUser: 张鹏
 * @createTime: 2020/11/13
 * @descripton:
 **/
public class CyclicBarrierDemo implements Runnable {
    private CyclicBarrier cyclicBarrier = new CyclicBarrier(5);
    private  Integer num = 5;
    @Override
    public void run() {
        while (1 == 1){
            if (num > 0){ //这里处理有问题 没有考虑到并发问题 需要枷锁
                try {
                    num--;
                    System.out.println(Thread.currentThread().getName() +"开始准备就绪");
                    cyclicBarrier.await();
                    System.out.println(Thread.currentThread().getName() +"售出票：时间"+ System.currentTimeMillis());

                } catch (Exception e) {
                    e.printStackTrace();
                }
            }else {
                return;
            }
        }
    }

    public static void main(String[] args) {
        CyclicBarrierDemo demo = new CyclicBarrierDemo();
        new Thread(demo,"线程一").start();
        new Thread(demo,"线程二").start();
        new Thread(demo,"线程三").start();
        new Thread(demo,"线程四").start();
        new Thread(demo,"线程五").start();
    }
}
