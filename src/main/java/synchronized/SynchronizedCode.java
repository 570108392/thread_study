import java.util.Random;
import java.util.concurrent.locks.Lock;

/**
 * @createUser: 张鹏
 * @createTime: 2020/11/12
 * @descripton:
 *
 * SynchronizedMethod 同步代码块测试
 **/
public class SynchronizedCode implements Runnable{

    private int num = 100;
    private Object object = new Object();

    @Override
    public  void  run() {

        synchronized(object){
            while (num > 0){
                try {
                    Thread.sleep(100 * new Random().nextInt(5));
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println(Thread.currentThread().getName() +"售出票："+ num--);
            }
        }
    }

    public static void main(String[] args) {
        SynchronizedCode synchronizedMethod = new SynchronizedCode();
        new Thread(synchronizedMethod,"窗口一").start();
        new Thread(synchronizedMethod,"窗口二").start();
        new Thread(synchronizedMethod,"窗口三").start();
    }
}
