package JUC.demo;

import java.util.ArrayList;
import java.util.List;

public class T04_WaitNotifyDemo {

    List list = new ArrayList<>();

    public void add(Object c) {
        list.add(c);
    }

    public int size() {
        return list.size();
    }

    public static void main(String[] args) {
        T04_WaitNotifyDemo c = new T04_WaitNotifyDemo();

        Object lock = new Object();

        new Thread(() -> {
            synchronized (lock) {
                System.out.println("T2 启动");
                if(c.size() != 5){
                    try {
                        lock.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                System.out.println("T2 结束");
                lock.notify();
            }

        }, "t2").start();

        new Thread(() -> {
            synchronized (lock) {
                System.out.println("T1启动");
                for (int i = 0; i < 10; i++) {
                    c.add(new Object());
                    System.out.println("Add " + i);
                    if (c.size() == 5) {
                        lock.notify();
                        try {
                            lock.wait();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                }

            }

        }, "T1").start();

    }
}
