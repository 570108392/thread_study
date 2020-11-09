package create_thread;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class MyThread extends Thread {
    @Override
    public void run() {
        for (int i =0; i< 1000;i++){
            System.out.println(Thread.currentThread().getName()+"我开始运行了");
        }
    }

    public static void main(String[] args) {
        new MyThread().start();

        for (int i =0; i< 1000;i++){
            System.out.println(Thread.currentThread().getName()+"主线程运行了");
        }

    }
}
