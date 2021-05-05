package JUC.demo;

/**
 * 饿汉模式
 */
public class SingletonDemo01 {

    private static final  SingletonDemo01 instance = new SingletonDemo01();

    public static SingletonDemo01 getInstance(){
        return instance;
    }

    public static void main(String[] args) {
//        SingletonDemo01 instance = SingletonDemo01.getInstance();
//        System.out.println(instance);
        for(int i = 0;i <10;i++){
            new Thread(()->{
                System.out.println(SingletonDemo03.getInstance().hashCode());
            }).start();
        }

    }
}
