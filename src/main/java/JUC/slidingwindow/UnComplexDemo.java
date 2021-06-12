package JUC.slidingwindow;

import javax.validation.constraints.Max;
import java.util.HashMap;

/**
 * 最长无重复字符串
 */
public class UnComplexDemo {

    public static void main(String[] args) {
        String s ="abcabcbb";
        System.out.println(findAngrams(s));
    }

    public static int findAngrams(String s){
        HashMap<Character, Integer> window = new HashMap<>();
        int left = 0,right = 0;
        int res = 0;
        while(right < s.length()){
            char c = s.charAt(right);
            right ++;
            window.put(c,window.getOrDefault(c,0)+1);
            while(window.get(c) > 1){//缩小左侧窗口
                char d = s.charAt(left);
                left ++;
                window.put(d,window.getOrDefault(d,0)-1);
            }
            res = Math.max(res,right - left);
        }
        return res;
    }
}
