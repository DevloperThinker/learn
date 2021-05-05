package JUC.demo;

import sun.misc.Unsafe;

public class UnsafeDemo {

   static  class M{
       public int i;

       private  M(){
            int i = 0;
       }
   }

    public static void main(String[] args) throws InstantiationException {
        Unsafe unsafe = Unsafe.getUnsafe();
        M m = (M)unsafe.allocateInstance(M.class);
        m.i = 9;
        System.out.println(m.i);
    }
}
