package JUC.algorithm.sort;

/**
 * 插入排序
 * 首先在未排序序列中找到最小（大）元素，存放到排序序列的起始位置，
 * 然后，再从剩余未排序元素中继续寻找最小（大）元素，
 * 然后放到已排序序列的末尾。以此类推，直到所有元素均排序完毕
 *
 * 时间复杂度：O（n2）
 * 空间复杂度：O (1)
 * 基本不用
 */
public class T01_SelectionSortDemo {

    public static void main(String[] args) {
        //数组初始化，不计入算法时间
        int[] arr = {5,3,6,8,1,7,9,4,2};

        //arr.length -1边界值处理
        for (int i = 0; i < arr.length -1; i++) {
            //默认最小元素为第一位
            int minPosi = i;

            //遍历找到排序序列中最小元素位置
            for (int j = i+1; j < arr.length; j++) {
               minPosi = arr[j] < arr[minPosi] ? j:minPosi;
            }

            //最小元素位置与起始位置交互
            swap(arr,i,minPosi);

            //打印，不计入算法时间
            print(arr);
            System.out.println();
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
