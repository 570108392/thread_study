package create_thread;

import java.util.concurrent.Callable;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import java.util.concurrent.FutureTask;

public class MyCallable implements Callable<String> {
    @Override
    public String call() throws Exception {
        for (int i =0; i< 100;i++){
            System.out.println(Thread.currentThread().getName()+"我开始运行了");
        }
        return Thread.currentThread().getName()+"我运行结束了";

    }

    public static void main(String[] args) throws Exception {
        FutureTask<String> futureTask = new FutureTask<>(new MyCallable());
        new Thread(futureTask).start();


        for (int i =0; i< 100;i++){
            System.out.println(Thread.currentThread().getName()+"主线程运行了");
        }

        System.out.println(futureTask.get());
    }
}
