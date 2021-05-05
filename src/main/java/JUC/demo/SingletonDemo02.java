package JUC.demo;

import java.util.concurrent.TimeUnit;

/**
 * 懒汉模式(非线程安全)
 */
public class SingletonDemo02 {

    private static volatile SingletonDemo02 instance =null;

    public static SingletonDemo02 getInstance(){
        if(instance == null){
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            instance = new SingletonDemo02();
        }

        return instance;
    }

    public static void main(String[] args) {
//        SingletonDemo02 instance = SingletonDemo02.getInstance();
//        System.out.println(instance);

        for (int i = 0; i <10 ; i++) {
            new Thread(()->{
                System.out.println(SingletonDemo02.getInstance().hashCode());
            }).start();
        }
    }
}
