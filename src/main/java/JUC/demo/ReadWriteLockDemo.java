package JUC.demo;

import java.util.Random;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class ReadWriteLockDemo {

    static Lock lock = new ReentrantLock();

    static int value;

    static ReadWriteLock readWriteLock = new ReentrantReadWriteLock();
    static Lock readLock = readWriteLock.readLock();
    static Lock writeLock = readWriteLock.writeLock();

    public static void  read(Lock lock){
        lock.lock();
        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }finally {
            lock.unlock();
        }
        System.out.println("read over");
    }

    public  static void write(Lock lock,int v){
        lock.lock();

        try {
            TimeUnit.SECONDS.sleep(2);
            value = v;
        } catch (InterruptedException e) {
            e.printStackTrace();
        }finally {
            lock.unlock();
        }
        System.out.println("write over");
    }

    public static void main(String[] args) {
//        Runnable readR = ()-> read(lock);
//        Runnable readW =()-> write(lock,new Random().nextInt());

        Runnable readR = ()-> read(readLock);
        Runnable readW =()-> write(writeLock,new Random().nextInt());

        for (int i = 0; i < 18; i++) {
            new Thread(readR).start();
        }
        for (int i = 0; i < 2; i++) {
            new Thread(readW).start();
        }
    }
}
