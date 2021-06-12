package JUC.algorithm.sort;

public class Demo02 {

    /**
     * 选择排序，思想：选择一个最小值（第一个节点），然后和所有节点做比较，若arr[i+1] < arr[i],则进行数据互换
     *
     */

    public static void selectionSort(int[] arr){

        for (int i = 0; i < arr.length; i++) {
            int minIndex = i;
            for (int j = i+1; j < arr.length; j++) {
               minIndex = arr[j] < arr[minIndex] ? j:minIndex;
            }
            sweep(arr,i,minIndex);
        }
        System.out.println("选择排序");
        print(arr);
    }


    /**
     * 冒泡排序，思想：取两个进行比较，若arr[i] > arr[i+1]，则数据互换
     * @param arr
     */
    public static void bubbleSort(int[] arr){

        for (int i = arr.length-1; i > 0 ; i--) {
            for (int j = 0; j < i; j++) {
                if(arr[j] > arr[j+1]){
                    sweep(arr,j,j+1);
                }
            }
        }
        System.out.println("冒泡排序结果：");
        print(arr);
    }


    /**
     * 插入排序，思想：插入一个位置，然后和之前的值进行比较，若小于前面的值则互换
     * @param arr
     */
    public static void insertionSort(int[] arr){
        for (int i = 1; i < arr.length; i++) {
            for (int j = i; j > 0; j--) {
                if(arr[j] < arr[j-1]){
                    sweep(arr,j,j-1);
                }
            }
        }
        System.out.println("插入排序结果：");
        print(arr);
    }


    public static void sweep(int[] arr,int i,int j){
        int temp = arr[i];
        arr[i]=arr[j];
        arr[j] = temp;
    }

    public static void print(int[] arr){
        for (int i = 0; i < arr.length; i++) {
            System.out.print(arr[i]+" ");
        }
        System.out.println("");
    }


    public static void main(String[] args) {
        int[] arr ={8,5,0,1,4,9,3,2,7,6};
        print(arr);
//        selectionSort(arr);
//        bubbleSort(arr);
        insertionSort(arr);
    }

}
