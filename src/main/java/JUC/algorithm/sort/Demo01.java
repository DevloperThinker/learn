package JUC.algorithm.sort;

public class Demo01 {
    public static void main(String[] args) {
        int[] arr = {9,5,6,1,2,3,5,7,8,9,5,4,1,0,20};

        /**
         * 选择排序，找到一个最小的
         */
        for (int i = 0; i < arr.length; i++) {
            int minIndex = i;
            for (int j = i+1; j < arr.length; j++) {
                minIndex = arr[j] < arr[minIndex]? j:minIndex;
            }
            swep(arr,i,minIndex);
        }

        System.out.println("Selection sort result:");
        print(arr);

        /**
         * 冒泡排序，相邻两个元素比较，然后互换
         */
        for (int i = arr.length-1;i > 0; i--) {
            for (int j = 0; j < i; j++) {
                if(arr[j] > arr[j+1] ) swep(arr,j,j+1);
            }
        }

        System.out.println("Bubble sort result:");
        print(arr);

        /**
         * 插入排序，取出一个元素，从后往前扫描
         */

        for (int i = 1; i < arr.length ; i++) {
            for (int j = i; j > 0; j--) {
                if(arr[j] < arr[j-1]) swep(arr,j,j-1);
            }
        }

        System.out.println("Insertion sort result:");
        print(arr);

    }


    /**
     * 两个数组元素互换
     * @param arr
     * @param i
     * @param j
     */
    static void swep(int[] arr,int i,int j){
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

    /**
     * 打印
     * @param arr
     */
    static void print(int[] arr){
        for (int k = 0; k < arr.length; k++) {
            System.out.print(arr[k]+ " ");
        }
        System.out.println("");
    }
}
