package JUC.demo;

import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;

public class SemaphoreDemo {
    public static void main(String[] args) {
        Semaphore semaphore = new Semaphore(1);

        new Thread(()->{
            try {
                semaphore.acquire();
                System.out.println("T1 running....");
                TimeUnit.SECONDS.sleep(1);
                System.out.println("T1 end...");
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
                System.out.println("T2 end...");
                semaphore.release();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        ).start();
    }
}
