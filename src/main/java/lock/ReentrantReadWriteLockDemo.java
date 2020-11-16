package lock;

import sun.awt.SunHints;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * @createUser: 张鹏
 * @createTime: 2020/11/16
 * @descripton: 读写锁 读写互斥 多线程时 当执行读时不能并发执行写操作
 **/
public class ReentrantReadWriteLockDemo {

    private Map<String,String> map = new HashMap<>();
    private ReentrantReadWriteLock lock = new ReentrantReadWriteLock();

    //创建读锁
    private ReentrantReadWriteLock.ReadLock readLock = lock.readLock();
    //创建写锁
    private ReentrantReadWriteLock.WriteLock writeLock = lock.writeLock();

    private void get(String key){
        try {
            readLock.lock();
            Thread.sleep(1000 * new Random().nextInt(5));
            System.out.println(Thread.currentThread().getName()+"线程添加读锁");
            map.get(key);
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            readLock.unlock();
            System.out.println(Thread.currentThread().getName()+"线程解开读锁");
        }
    }

    private void add(String key, String value){
        try {
            writeLock.lock();
            Thread.sleep(1000 * new Random().nextInt(5));
            System.out.println(Thread.currentThread().getName()+"线程添加写锁");
            map.put(key,value);
        }catch (Exception e){
          e.printStackTrace();
        }finally {
            writeLock.unlock();
            System.out.println(Thread.currentThread().getName()+"线程解开写锁");
        }
    }

    public static void main(String[] args) {
        ReentrantReadWriteLockDemo demo = new ReentrantReadWriteLockDemo();
        for (int i = 0; i < 10; i++){
            int finalI = i;
            new Thread(() ->demo.add("key"+ finalI,"value"+finalI)).start();
        }
        for (int i = 0; i < 10; i++){
            int finalI = i;
            new Thread(() ->demo.get("key"+ finalI)).start();
        }
    }
}
