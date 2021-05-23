package JUC.algorithm.sort;

/**
 * 插入排序
 * 从第一个元素开始，该元素可以认为已经被排序；
 * 取出下一个元素，在已经排序的元素序列中从后向前扫描；
 * 如果该元素（已排序）大于新元素，将该元素移到下一位置；
 * 重复步骤3，直到找到已排序的元素小于或者等于新元素的位置；
 * 将新元素插入到该位置后；
 * 重复步骤2~5。
 *
 *  时间复杂度：O（n2）
 *  空间复杂度：O (1)
 *  样本小且基本有序的时候效率比较高
 *
 */
public class T03_InsertionSortDemo {

    public static void main(String[] args) {
        int[] arr = {9,5,6,1,2,3,5,7,8,9,5,4,1,0,20};
        sort(arr);
        print(arr);

    }

    static void sort(int[] a){
        for (int i = 1; i < a.length; i++) {
            //取出下一个元素，从后向前扫描
            for (int j = i; j > 0; j--) {
                if(a[j] < a[j-1]) swap(a,j,j-1);
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
