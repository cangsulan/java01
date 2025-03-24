package leetcode;

import java.util.HashMap;

public class leetcode_76 {
    public String minWindow(String s, String t) {
        char[] ss = s.toCharArray();
        char[] tt = t.toCharArray();
        HashMap<Character, Integer> map = new HashMap<Character, Integer>();
        int type = 0;
        //用哈希表统计出 字符串t中的字符情况
        for (int i = 0; i < tt.length; i++) {
            if (map.getOrDefault(tt[i], 0) <= 0) {
                type++;
            }
            map.put(tt[i], map.getOrDefault(tt[i], 0) + 1);
        }
        //然后开始用双指针组成滑动窗口 来遍历操作 字符串s
        int rel = 0;
        int rer = ss.length - 1;
        int left = 0;
        int right = 0;
        boolean finded=false;
        for (; right < ss.length; right++) {
            int temp = map.getOrDefault(ss[right], 0);
            if (map.containsKey(ss[right])) {
                map.put(ss[right], temp - 1);
                if (temp -1 == 0) {
                    type--;
                }
            }
            if(type<=0){
                finded=true;
            }
            while (type <= 0 && left <= right) {
                if (right - left < rer - rel) {
                    rer = right;
                    rel = left;
                }
                if (map.containsKey(ss[left])) {
                    int temp2 = map.getOrDefault(ss[left], 0);
                    map.put(ss[left], temp2 + 1);
                    if (temp2 + 1 > 0) {
                        type++;
                    }
                }
                left++;
            }
        }
        if(!finded){
            return "";
        }
        return s.substring(rel, rer + 1);
    }
}
