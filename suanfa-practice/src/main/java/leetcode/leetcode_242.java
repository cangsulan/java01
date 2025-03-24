package leetcode;

import java.util.HashMap;
import java.util.Map;

public class leetcode_242 {
    public boolean isAnagram(String s, String t) {
        char[] ss=s.toCharArray();
        char[] tt=t.toCharArray();
        //统计ss的字符情况
        HashMap<Character,Integer> map=new HashMap<>();
        for(int i=0;i<ss.length;i++){
            if(!map.containsKey(ss[i])){
                map.put(ss[i],1);
            }else{
                map.put(ss[i],map.get(ss[i])+1);
            }
        }
        //
        for(int i=0;i<tt.length;i++){
            if(!map.containsKey(tt[i]) || map.get(tt[i])==0){
                return false;
            }else{
                map.put(tt[i],map.get(tt[i])-1);
            }
        }
        for (Map.Entry<Character, Integer> entry : map.entrySet()) {
            if(entry.getValue()>0){
                return false;
            }
        }
        return true;
    }
}
