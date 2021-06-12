package JUC.binarySearch;

/**
 * 二分搜索
 */
public class BinarySearchDemo {

    public static void main(String[] args) {
        int[] nums={1,2,3,4,5,7,7,10};
        int target = 7;
        System.out.println(binanySearch(nums,target));
        System.out.println(left_bound(nums,target));
        System.out.println(right_bound(nums,target));
    }


    /**
     * 普通二分搜索
     * @param nums
     * @param target
     */
    public static int binanySearch(int[] nums,int target){
        int left = 0;
        int right = nums.length - 1;
        while(left <= right){
            int mid = left + (right-left)/2;
            if(nums[mid] == target){
                return mid;
            }else if(nums[mid] < target){
                left = mid + 1;
            }else if(nums[mid] > target){
                right = mid - 1;
            }
        }
        return -1;
    }

    /**
     * 最左二分搜索
     * @param nums
     * @param target
     * @return
     */
    public static int left_bound(int[] nums,int target){
        int left = 0;
        int right = nums.length - 1;
        while(left <= right){
            int mid = left + (right-left)/2;
            if(nums[mid] == target){
                right = mid - 1;
            }else if(nums[mid] < target){
                left = mid + 1;
            }else if(nums[mid] > target){
                right = mid - 1;
            }
        }

        if(left >= nums.length || nums[left] != target){
           return -1;
        }
        return left;
    }



    /**
     * 最左二分搜索
     * @param nums
     * @param target
     * @return
     */
    public static int right_bound(int[] nums,int target){
        int left = 0;
        int right = nums.length - 1;
        while(left <= right){
            int mid = left + (right-left)/2;
            if(nums[mid] == target){
                left = mid + 1;
            }else if(nums[mid] < target){
                left = mid + 1;
            }else if(nums[mid] > target){
                right = mid - 1;
            }
        }

        if(right < 0 || nums[right] != target){
            return -1;
        }
        return right;
    }

}
