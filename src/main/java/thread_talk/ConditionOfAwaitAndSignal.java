package thread_talk;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @createUser: 张鹏
 * @createTime: 2020/11/12
 * @descripton: 线程通讯 condition (jdk1.5之后出现)
 * wait()、notify()和notifyAll()是基于synchronized
 * Condition是基于Lock的。
 * Condition是在java 1.5中才出现的，它用来替代传统的Object的wait()、notify()实现线程间的协作，相比使用Object的wait()、notify()，
 *      使用Condition的await()、signal()这种方式实现线程间协作更加安全和高效。阻塞队列实际上是使用了Condition来模拟线程间协作。
 *
 * Condition是个接口，基本的方法就是await()和signal()方法；
 * Condition依赖于Lock接口，生成一个Condition的基本代码是lock.newCondition()
 *  调用Condition的await()和signal()方法，都必须在lock保护之内，就是说必须在lock.lock()和lock.unlock之间才可以使用
 * 　　Conditon中的await()对应Object的wait()；
 *
 * 　　Condition中的signal()对应Object的notify()；
 *
 * 　　Condition中的signalAll()对应Object的notifyAll()。
 **/
public class ConditionOfAwaitAndSignal {

    private Integer num = 100;
    //默认 false 独占锁
    private static ReentrantLock lock = new ReentrantLock();
    private static Condition condition = lock.newCondition();
    /**
     * 奇数执行该方法
     */
    private void odd(){
        try {
            lock.lock();
            while (num > 0){
                if (num % 2 == 1){
                    System.out.println(Thread.currentThread().getName()+"奇数："+ num--);
                    //唤醒偶数线程
                    condition.signal();
                }else{
                    //执行完 等待线程
                    try {
                        condition.await();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }finally {
            lock.unlock();
        }
    }

    /**
     * 偶数执行该方法
     */
    private void even(){
        try {
            lock.lock();
            while (num > 0){
                if (num % 2 == 1){
                    //唤醒偶数线程
                    try {
                        condition.await();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }else{
                    System.out.println(Thread.currentThread().getName()+"奇数："+ num--);
                    //唤醒偶数线程
                    condition.signal();
                }
            }
        }finally {
            lock.unlock();
        }
    }

    public static void main(String[] args) {
        ConditionOfAwaitAndSignal nodifyAndWait = new ConditionOfAwaitAndSignal();
        new Thread(()->nodifyAndWait.odd()).start();
        new Thread(()->nodifyAndWait.even()).start();

    }
}
