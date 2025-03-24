package leetcode;

import java.util.ArrayList;
import java.util.List;

public class leetcode_49 {
    public List<List<String>> groupAnagrams(String[] strs) {
        int[] m=new int[strs.length];
        List<List<String>> res=new ArrayList<>();
        for(int i=0;i<strs.length;i++){
            if(m[i]==1){
                continue;
            }
            List<String> list=new ArrayList<>();
            list.add(strs[i]);
            m[i] = 1;
            for(int j=i+1;j<strs.length;j++) {
                if (isLike(strs[i], strs[j])) {
                    m[j] = 1;
                    list.add(strs[j]);
                }
            }
            res.add(list);
        }
        return res;
    }
    //判断两个字符串是否是 字母异位词
    public boolean isLike(String s,String t){
        //维护一个数组来记录每个字母的出现频率
        int[] m=new int[26];
        for(int i=0;i<s.length();i++){
            m[s.charAt(i)-'a']++;
        }
        for(int i=0;i<t.length();i++){
            m[t.charAt(i)-'a']--;
        }
        for(int i=0;i<m.length;i++){
            if(m[i]!=0){
                return false;
            }
        }
        return true;
    }
}
