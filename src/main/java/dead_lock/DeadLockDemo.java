package dead_lock;


/**
 * @createUser: 张鹏
 * @createTime: 2020/11/12
 * @descripton: 死锁演示
 * 产生死锁的条件：
 *          1：互斥条件：即某一段时间内某一资源仅为一个进程所占有，其他进程只能等待
 *          2：不可剥夺条件：即某一进程在未使用完前，不能被其他进程前行夺走，只能由该进程主动来释放
 *          3：请求与保持条件：即某一进程已占用了一个资源，但在资源内又提出了新的资源请求，而该新的
 *              资源请求又被其他进程占有，导致此请求阻塞，进而该进程阻塞，但该进程依旧不主动释放资源
 *          4：循环等待条件：即资源A》资源B》资源C》资源A  不会存在外部干涉
 **/
public class DeadLockDemo implements Runnable{

    private Integer flag;
    private static  Object object1 = new Object(); //尤其注意这里需要是静态的（即共享公共锁）
    private static Object object2 = new Object();

    public DeadLockDemo(Integer flag) {
        this.flag = flag;
    }

    @Override
    public  void run() {

        if (flag == 1){
            myThread();
        }else{
            myThread2();
        }
    }

    private void myThread(){
            synchronized(object2){
                try {
                    Thread.sleep(1000 );
                    System.out.println("获取锁2成功，正在获取锁1");
                    myThread2();
                    System.out.println("获取锁2成功，获取锁1成功");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
    }
    private void myThread2(){
        synchronized(object1){
            try {
                Thread.sleep(1000 );
                System.out.println("获取锁1成功，正在获取锁2");
                myThread();
                System.out.println("获取锁1成功，获取锁2成功");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {
        DeadLockDemo deadLockDemo1 = new DeadLockDemo(1);
        DeadLockDemo deadLockDemo2 = new DeadLockDemo(2);
        new Thread(deadLockDemo1).start();
        new Thread(deadLockDemo2).start();
    }


}
