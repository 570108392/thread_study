package create_thread;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.FutureTask;

@Slf4j
public class MyRunable implements Runnable {
    @Override
    public void run() {
        for (int i =0; i< 100;i++){
            System.out.println(Thread.currentThread().getName()+"我开始运行了");
        }
    }
    public static void main(String[] args) {
        new Thread(new MyRunable()).start();

        for (int i =0; i< 100;i++){
            System.out.println(Thread.currentThread().getName()+"主线程运行了");
        }

    }


}
