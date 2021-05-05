package JUC.demo;

import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;

public class SemaphoreDemo {
    public static void main(String[] args) {
        Semaphore semaphore = new Semaphore(2);

        new Thread(()->{
            try {
                semaphore.acquire();
                System.out.println("T1 running....");
                TimeUnit.SECONDS.sleep(1);
                System.out.println("T1 running...");
                semaphore.release();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        ).start();

        new Thread(()->{
            try {
                semaphore.acquire();
                System.out.println("T2 running....");
                TimeUnit.SECONDS.sleep(1);
                System.out.println("T2 running...");
                semaphore.release();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        ).start();
    }
}
