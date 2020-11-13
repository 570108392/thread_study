package synchronized_thread;

import java.util.Random;

/**
 * @createUser: 张鹏
 * @createTime: 2020/11/12
 * @descripton:
 *
 * synchronized_thread.SynchronizedMethod 同步代码块测试
 **/
public class SynchronizedCode implements Runnable{

    private int num = 100;
    private static Object object = new Object();

    @Override
    public  void  run() {


        while (true){
            synchronized(object){
                if (num > 0){
                    try {
                        Thread.sleep(10 * new Random().nextInt(10));
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    System.out.println(Thread.currentThread().getName() +"售出票："+ num--);
                }else
                    return;

            }
        }
    }

    public static void main(String[] args) {
        SynchronizedCode synchronizedMethod = new SynchronizedCode();
        new Thread(synchronizedMethod,"窗口一").start();
        new Thread(synchronizedMethod,"窗口二").start();
        new Thread(synchronizedMethod,"窗口三").start();
        new Thread(synchronizedMethod,"窗口四").start();
        new Thread(synchronizedMethod,"窗口五").start();
        new Thread(synchronizedMethod,"窗口六").start();
    }
}
