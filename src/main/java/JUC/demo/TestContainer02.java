package JUC.demo;

import java.util.LinkedList;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class TestContainer02<T> {

    private LinkedList<T> list = new LinkedList<>();
    private int MAX = 10;
    private int count = 0;
    ReentrantLock lock = new ReentrantLock();
    Condition consumer = lock.newCondition();
    Condition producer = lock.newCondition();

    private void put(T t) {
        try {
            lock.lock();
            while (list.size() == MAX) {
                try {
                    producer.await();//生产者线程zus
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            list.add(t);
            ++count;
            consumer.signalAll();//通知消费者进行消费
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

    private T get() {
        T t = null;
        try {
            lock.lock();
            while (list.size() == 0) {
                consumer.await();
            }
            t = list.removeFirst();
            count--;
            producer.signalAll(); //通天生产者进行生产
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
        return t;
    }

    public static void main(String[] args) {
        TestContainer02 c = new TestContainer02();
        //启动消费者
        for (int i = 0; i < 10; i++) {
            new Thread(() -> {
                for (int j = 0; j < 5; j++) {
                    System.out.println(c.get());
                }
            }, "c" + i).start();
        }
        //启动生产者
        for (int i = 0; i < 2; i++) {
            new Thread(() -> {
                for (int j = 0; j < 25; j++) {
                    c.put(Thread.currentThread().getName() + " " + j);
                }
            }).start();
        }
    }
}
