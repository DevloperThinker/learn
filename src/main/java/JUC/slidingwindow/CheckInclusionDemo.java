package JUC.slidingwindow;

import java.util.HashMap;

/**
 * 给定两个字符串s1和s2,写一个函数来判断s2是否包含s1的排列
 */
public class CheckInclusionDemo {

    public static void main(String[] args) {
        String s="eidbaoooo";
        String t="ab";
        System.out.println(checkInclusion(s,t));
    }

    public static boolean checkInclusion(String s,String t){
        HashMap<Character, Integer> need = new HashMap<>();
        HashMap<Character, Integer> window = new HashMap<>();
        for(char a:t.toCharArray()){
            need.put(a,need.getOrDefault(a,0)+1);
        }
        int left = 0,right =0,valid =0;

        while(right < s.length()){
            char c = s.charAt(right);
            right++;
            if(need.containsKey(c)){
                window.put(c,window.getOrDefault(c,0)+1);
                if(window.get(c) == need.get(c)){
                    valid++;
                }
            }
            while(right - left >= t.length()){
                if(valid == need.size()){
                    return true;
                }
                char d = s.charAt(left);
                left ++;
                if(need.containsKey(d)){
                    if(window.get(d) == need.get(d)){
                        valid --;
                    }
                    window.put(d,window.getOrDefault(d,0)-1);
                }
            }
        }
        return false;
    }

}
