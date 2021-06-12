package JUC.slidingwindow;

import java.util.HashMap;

/**
 * 滑动窗口算法
 */
public class SlidingWindowDemo {

    public static void main(String[] args) {
        //在字符串S中找出包含T所有字母的最小子串
//        String s = "ADOBECODEBANC";
//        String t = "CBA";
//        String s = "lhibsbrpxssyuibsdicrucaegadzsqpfyzisdahasbamlbgolkigpimvipucfljflszquhsokscwwimhvupkcbflqbpymzfzrfplmhbycduylhhgbxseyxrrygrnfwmybelpnmoppkndrvlvu";
//        String t = "ebsdslcacpib";
        String s = "eidbaoooo";
        String t="ab";
        System.out.println(slidingWindow(s, t));
//        System.out.println(minWindow(s,t));

    }

    public static String slidingWindow(String s, String t) {
        HashMap<Character, Integer> need = new HashMap<>();
        HashMap<Character, Integer> window = new HashMap<>();
        for (char c : t.toCharArray()) {
            need.put(c, need.getOrDefault(c, 0) + 1);
        }

        int left = 0;
        int right = 0;
        int valid = 0;//表示窗口中满足need条件的字符个数，当valid等于need.size，说明窗口已满足条件，已经完全覆盖了字符串t
        /**
         * 思路：如果一个字符进入窗口，应该增加window计数器；如果一个字符移出窗口，应该减少window计数器
         * 当valid满足need时应该开始收缩窗口，在收缩窗口的时候更新最终结果
         */
        //记录最小字符串的起始长度和最大长度
        int start = 0;
        int len = Integer.MAX_VALUE;
        while (right < s.length()) {
            //即将移入window窗口的字符
            char c = s.charAt(right);
            right++; //右移窗口
            //判断该字符串是否为need需要的
            if (need.containsKey(c)) {
                //移入窗口中
//                int tmp = window.getOrDefault(c, 0);
//                window.put(c, ++tmp);
                window.put(c,window.getOrDefault(c,0)+1);
                if (window.get(c) == need.get(c)) {
                    valid++;
                }
            }
            //开始收缩窗口
            while(valid == need.size()){
                //更新最小子串
                if(right - left < len){
                    start = left;
                    len = right - left;
                }
                //d为即将移出window窗口的字符
                char d = s.charAt(left);
                //左移窗口
                left ++;

                //更新窗口数据
                if(need.containsKey(d)){
                    if(window.get(d) == need.get(d)){
                        valid --;
                    }
                    window.put(d,window.getOrDefault(d,0)-1);
                }
            }
        }
        return len == Integer.MAX_VALUE?"":s.substring(start,start+len);
    }

//    public static String minWindow(String s ,String t){
//        HashMap<Character, Integer> need = new HashMap<>();
//        HashMap<Character, Integer> window = new HashMap<>();
//        for (char c : t.toCharArray()) {
//            need.put(c, need.getOrDefault(c, 0) + 1);
//        }
//
//        int left = 0;
//        int right = -1;
//        int valid = 0;//表示窗口中满足need条件的字符个数，当valid等于need.size，说明窗口已满足条件，已经完全覆盖了字符串t
//        int len = s.length() + 1;
//        String result = null;
//        while(left < s.length()){
//            if(right + 1 < s.length() && valid < t.length()){
//                right++;
//                if(need.containsKey(s.charAt(right))){
//                    if(need.get(s.charAt(right)) > 0){
//                        valid ++;
//                    }
//                    need.put(s.charAt(right),need.getOrDefault(s.charAt(right),0)-1);
//                }
//            }else{
//                if(need.containsKey(s.charAt(left))){
//                    if (need.get(s.charAt(left)) == 0){
//                        valid--;
//                    }
//                    need.put(s.charAt(left),need.getOrDefault(s.charAt(left),0)+1);
//                }
//                left ++;
//            }
//            if(valid == t.length()){
//                if(right - left + 1 < len){
//                    len = right -left +1;
//                    result = s.substring(left,right+1);
//                }
//            }
//
//        }
//        return result;
//    }

//    public static String minWindow(String s, String t) {
//        HashMap<Character, Integer> need = new HashMap<>();
//        HashMap<Character, Integer> window = new HashMap<>();
//        for (int i = 0; i < t.length(); i++) {
//            need.put(t.charAt(i), need.getOrDefault(t.charAt(i), 0) + 1);
//        }
//
//        int left = 0;
//        int right = 0;
//        int valid = 0;//表示窗口中满足need条件的字符个数，当valid等于need.size，说明窗口已满足条件，已经完全覆盖了字符串t
//        /**
//         * 思路：如果一个字符进入窗口，应该增加window计数器；如果一个字符移出窗口，应该减少window计数器
//         * 当valid满足need时应该开始收缩窗口，在收缩窗口的时候更新最终结果
//         */
//        //记录最小字符串的起始长度和最大长度
//        int start = 0;
//        int len = Integer.MAX_VALUE;
//        while (right < s.length()) {
//            //即将移入window窗口的字符
//            char c = s.charAt(right);
//            right ++; //右移窗口
//            //判断该字符串是否为need需要的
//            if(need.containsKey(c)){
//                //移入窗口中
//                int tmp = window.getOrDefault(c,0);
//                window.put(c,++tmp);
//                if(window.get(c) == need.get(c)){
//                    valid ++;
//                }
//            }
//            //开始收缩窗口
//            while(valid == need.size()){
//                //更新最小子串
//                if(right - left < len){
//                    start = left;
//                    len = right - left;
//                }
//                //d为即将移出window窗口的字符
//                char d = s.charAt(left);
//                //左移窗口
//                left ++;
//
//                //更新窗口数据
//                if(need.containsKey(d)){
//                    if(window.get(d) == need.get(d)){
//                        valid --;
//                        int temp = window.get(d);
//                        window.put(d,--temp);
//                    }
//                }
//
//            }
//        }
//        return len == Integer.MAX_VALUE?"":s.substring(start,start+len);
//    }
//
//    public static String minWindow(String s, String t) {
//        HashMap<Character, Integer> need = new HashMap<>();
//        HashMap<Character, Integer> window = new HashMap<>();
//        int t_len = t.length();
//        for (int i = 0; i < t_len; i++) {
//            need.put(t.charAt(i), need.getOrDefault(t.charAt(i), 0) + 1);
//        }
//        int left = 0, right = 0;
//        int valid = 0;
//        // 记录最小覆盖子串的起始索引及⻓度
//        int start = 0, len = Integer.MAX_VALUE;
//        while (right < s.length()) {
//            // c 是将移入窗口的字符
//            char c = s.charAt(right); // 右移窗口
//            right++;
//            // 进行窗口内数据的一系列更新
//            if (need.containsKey(c)) {
//                int tmp = window.getOrDefault(c, 0);
//                window.put(c, ++tmp);
//                if (window.get(c) == need.get(c))
//                    valid++;
//            }
//            // 判断左侧窗口是否要收缩
//            while (valid == need.size()) {
//                // 在这里更新最小覆盖子串
//                // len用于记录当前最佳的长度
//                if (right - left < len) {
//                    start = left;
//                    len = right - left;
//                }
//                // d 是将移出窗口的字符
//                char d = s.charAt(left);
//                // 左移窗口
//                left++;
//                // 进行窗口内数据的一系列更新
//                if (need.containsKey(d)) {
//                    if (window.get(d) == need.get(d))
//                        valid--;
//                    int tmp = window.get(d);
//                    window.put(d, --tmp);
//                }
//            }
//        }
//        // 返回最小覆盖子串
//        return len == Integer.MAX_VALUE ? "" : s.substring(start, start + len);
//
//        }
}