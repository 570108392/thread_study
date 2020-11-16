package Local_thread;


/**
 * @createUser: 张鹏
 * @createTime: 2020/11/16
 * @descripton:
 **/
public class LocalThreadDemo {

    /**
     * 1.public T get() { }
     * 用来设置当前线程中变量的副本(类似局部变量)
     *
     * 2.public void set(T value) { }
     * 方法是用来获取ThreadLocal在当前线程中保存的变量副本
     *
     * 3.public void remove() { }
     * 用来移除当前线程中变量的副本
     * 4.protected T initialValue(){ }
     * 是一个protected方法，一般是用来在使用时进行重写的，它是一个延迟加载方法
     *
     */

    private ThreadLocal<Integer> local = ThreadLocal.withInitial(() -> 10);

    private void get(){
        while (local.get() > 0){
            System.out.println(Thread.currentThread().getName()+"还有员工"+local.get());
            local.set(local.get() -1);
        }
    }

    public static void main(String[] args) {
        LocalThreadDemo demo = new LocalThreadDemo();
        new Thread(()->demo.get()).start();
        new Thread(()->demo.get()).start();
        new Thread(()->demo.get()).start();
    }
}
