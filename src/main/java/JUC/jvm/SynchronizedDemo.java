package JUC.jvm;

import java.util.concurrent.TimeUnit;

public class SynchronizedDemo implements Runnable{

    private String lockA;
    private String lockB;

    public SynchronizedDemo(String lockA,String lockB){
        this.lockA = lockA;
        this.lockB = lockB;
    }


    @Override
    public void run() {
        synchronized (lockA){
            System.out.println(Thread.currentThread().getName() + "\t 自己持有" + lockA + "\t 尝试获取：" + lockB);

            try {
                TimeUnit.SECONDS.sleep(2);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            synchronized (lockB){
                System.out.println(Thread.currentThread().getName() + "\t 自己持有" + lockB + "\t 尝试获取：" + lockA);
            }
        }
    }

    public static void main(String[] args) {
        String lockA ="lockA";
        String lockB = "lockB";
        new Thread(new SynchronizedDemo("lockA","lockB"),"t1").start();
        new Thread(new SynchronizedDemo("lockB","lockA"),"t1").start();
    }
}
