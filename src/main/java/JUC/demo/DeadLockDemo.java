package JUC.demo;

import java.util.concurrent.TimeUnit;

public class DeadLockDemo {

    public static void main(String[] args) {
        dead_lock();
    }

    private static void dead_lock(){
        final Object resource1 = "R1";
        final Object resource2 ="R2";

        new Thread(()->{
            synchronized (resource1){
                System.out.println("Thread1: locked resource1");
            }

            try {
                TimeUnit.SECONDS.sleep(5);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            synchronized (resource2){
                System.out.println("Thread1: locked resource2");
            }

        },"t1").start();

        new Thread(()->{
            synchronized (resource2){
                System.out.println("Thread2: locked resource2");
            }

            try {
                TimeUnit.SECONDS.sleep(5);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            synchronized (resource1){
                System.out.println("Thread2: locked resource1");
            }

        },"t2").start();
    }
}
