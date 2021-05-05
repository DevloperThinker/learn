package JUC.threadpool;

import java.util.concurrent.*;

public class T00_Future {

    public static void main(String[] args) throws ExecutionException, Exception {

        ExecutorService service = Executors.newFixedThreadPool(5);

        Future<Integer> future =service.submit(()->{
                TimeUnit.MILLISECONDS.sleep(600);
                return 1;
        });
        System.out.println(future.get());
        System.out.println(future.isDone());
    }
}
