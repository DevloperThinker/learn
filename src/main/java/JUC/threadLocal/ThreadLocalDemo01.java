package JUC.threadLocal;

public class ThreadLocalDemo01 {

    static ThreadLocal<String> threadLocal = new ThreadLocal<>();

    static void print(String str){
        System.out.println(str+" :"+threadLocal.get());
        threadLocal.remove();
    }

    public static void main(String[] args) {
        new Thread(()->{
            threadLocal.set("t1");
            print("thread1");
            System.out.println("after remove:"+threadLocal.get());
        },"t1").start();


        new Thread(()->{
            threadLocal.set("t2");
            print("thread2");
            System.out.println("after remove:"+threadLocal.get());
        },"t2").start();
    }
}
