package JUC.threadpool;


import com.sun.jmx.snmp.tasks.Task;

import java.util.concurrent.*;

public class T01_ThreadPoolExecutor {

    public static void main(String[] args) {
        ThreadPoolExecutor tpe = new ThreadPoolExecutor(
                2,
                4,
                60,
                TimeUnit.SECONDS,
                new ArrayBlockingQueue<>(4),
                Executors.defaultThreadFactory(),
                new ThreadPoolExecutor.AbortPolicy()
        );

        for (int i = 0; i < 8; i++) {
//            tpe.execute(new Task(i));
        }

        System.out.println(tpe.getQueue());
//        tpe.execute(new Task(100));

        System.out.println(tpe.getQueue());
        tpe.shutdown();
    }
}
