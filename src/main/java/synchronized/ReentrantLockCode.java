import java.util.Random;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @createUser: 张鹏
 * @createTime: 2020/11/12
 * @descripton: 使用ReentrantLock 可重入锁
 *
 **/
public class ReentrantLockCode implements  Runnable{

    private Integer num = 100;
    // true  使用公平锁 人人机会均等 false 独占锁(谁先抢到就谁先用 一直等到他用完)
    ReentrantLock lock = new ReentrantLock(false);
    @Override
    public  void  run() {
            try {
                lock.lock();
                while (num > 0){
                    try {
                        Thread.sleep(100 * new Random().nextInt(5));
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    System.out.println(Thread.currentThread().getName() +"售出票："+ num--);
                }
            }finally {
                lock.unlock();
            }

    }

    public static void main(String[] args) {
        SynchronizedCode synchronizedMethod = new SynchronizedCode();
        new Thread(synchronizedMethod,"窗口一").start();
        new Thread(synchronizedMethod,"窗口二").start();
        new Thread(synchronizedMethod,"窗口三").start();
    }
}
