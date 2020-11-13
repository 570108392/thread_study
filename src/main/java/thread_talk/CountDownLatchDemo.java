package thread_talk;

import java.util.Random;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.CyclicBarrier;

/**
 * @createUser: 张鹏
 * @createTime: 2020/11/13
 * @descripton:
 **/
public class CountDownLatchDemo {

    private Integer num = 10;
    private static CountDownLatch countDownLatch = new CountDownLatch(3);
    private void thread(){
        while (1 == 1){
            synchronized(this){
                if (num > 0){
                    try {
                        Thread.sleep(10 * new Random().nextInt(10));
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    System.out.println(Thread.currentThread().getName() +"售出票："+ num--);
                }else {
                    countDownLatch.countDown();
                    return;
                }

            }
        }
    }

    public static void main(String[] args) {
        new Thread(()->new CountDownLatchDemo().thread(),"有序线程一").start();
        new Thread(()->new CountDownLatchDemo().thread(),"有序线程二").start();
        new Thread(()->new CountDownLatchDemo().thread(),"有序线程三").start();
        try {
            countDownLatch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(Thread.currentThread().getName() +"线程开始执行");
    }
}
