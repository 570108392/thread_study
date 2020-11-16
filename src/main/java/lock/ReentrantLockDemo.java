package lock;


import java.util.concurrent.locks.ReentrantLock;

/**
 * @createUser: 张鹏
 * @createTime: 2020/11/16
 * @descripton: ReentrantLock 重入锁
 **/
public class ReentrantLockDemo {
    private ReentrantLock lock = new ReentrantLock();
    private void myThread(){

        for (int i = 0; i < 10; i++){
            lock.lock();
            System.out.println(Thread.currentThread().getName()+"线程加锁数："+ (i+1));
        }
        for (int i = 0; i < 10; i++){
            lock.unlock();
            System.out.println(Thread.currentThread().getName()+"线程解锁数："+ (i+1));
        }
        System.out.println(Thread.currentThread().getName()+"线程运行完成");
    }

    public static void main(String[] args) {
        ReentrantLockDemo demo = new ReentrantLockDemo();
        demo.myThread();
    }
}
