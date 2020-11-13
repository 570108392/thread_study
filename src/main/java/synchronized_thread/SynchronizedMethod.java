package synchronized_thread;

import java.util.concurrent.CountDownLatch;

/**
 * @createUser: 张鹏
 * @createTime: 2020/11/12
 * @descripton:
 *
 * synchronized_thread.SynchronizedMethod 同步方法
 *      非静态方法：默认锁为对象this
 *      静态方法：默认锁为对象SynchronizedMethod.class
 **/
public class SynchronizedMethod implements Runnable{

    private int num = 100;

    @Override
    public synchronized void  run() {
        while (num > 0){
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(Thread.currentThread().getName() +"售出票："+ num--);
        }
    }

    public static void main(String[] args) {
        SynchronizedMethod synchronizedMethod = new SynchronizedMethod();
        new Thread(synchronizedMethod,"窗口一").start();
        new Thread(synchronizedMethod,"窗口二").start();
        new Thread(synchronizedMethod,"窗口三").start();
    }
}
