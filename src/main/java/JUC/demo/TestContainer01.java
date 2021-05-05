package JUC.demo;

import java.util.LinkedList;
import java.util.concurrent.atomic.AtomicInteger;

public class TestContainer01<T> {

    private LinkedList<T> list = new LinkedList<>();
    private int MAX = 10;
    private int count = 0;
    AtomicInteger atomicInteger = new AtomicInteger(0);
    private synchronized void put(T t){
        while(list.size() == MAX){
            try {
                this.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        list.add(t);
        ++count;
//        atomicInteger.incrementAndGet();
        this.notifyAll();
    }

    private synchronized T get(){
        T  t = null;
        while(list.size() == 0){
            try {
                this.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        t = list.removeFirst();
        count--;
//        atomicInteger.getAndDecrement();
        this.notifyAll();
        return t;
    }

    public static void main(String[] args) {
        TestContainer01 c = new TestContainer01();

        //启动消费者
        for (int i = 0; i < 10; i++) {
            new Thread(()->{
                for (int j = 0; j < 5; j++) {
                    System.out.println(c.get());
                }
            },"c"+i).start();
        }
        //启动生产者
        for (int i = 0; i < 2; i++) {
            new Thread(()->{
                for (int j = 0; j < 25; j++) {
                    c.put(Thread.currentThread().getName()+" "+j);
                }
            }).start();
        }
    }


}
