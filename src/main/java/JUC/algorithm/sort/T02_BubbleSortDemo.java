package JUC.algorithm.sort;

/**
 * 冒泡排序
 * 比较相邻的元素。如果第一个比第二个大，就交换它们两个；
 * 对每一对相邻元素作同样的工作，从开始第一对到结尾的最后一对，这样在最后的元素应该会是最大的数；
 * 针对所有的元素重复以上的步骤，除了最后一个；
 * 重复步骤1~3，直到排序完成。
 *
 *  时间复杂度：O（n2）
 *  空间复杂度：O (1)
 *  基本不用
 */
public class T02_BubbleSortDemo {

    public static void main(String[] args) {
        int[] arr = {9,5,6,1,2,3,5,7,8,9,5,4,1,0,20};
        sort(arr);
        print(arr);
    }

    static void sort(int[] a){
        for (int i = a.length -1 ; i > 0 ; i--) {
            for (int j = 0; j < i ; j++) {
                //相邻两个元素做比较后进行交换数据
                if(a[j] > a[j+1]){
                    swap(a,j,j+1);
                }
            }
        }

    }


    /**
     * 数组两个位置互换
     * @param arr
     * @param i
     * @param j
     */
    static void swap(int[] arr,int i,int j){
        int temp = arr[i];
        arr[i]=arr[j];
        arr[j]=temp;
    }

    /**
     * 打印
     * @param arr
     */
    static void print(int[] arr){
        for (int i = 0; i < arr.length; i++) {
            System.out.print(arr[i]+" ");
        }
    }
}
