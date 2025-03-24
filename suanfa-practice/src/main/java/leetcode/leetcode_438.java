package leetcode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class leetcode_438 {
    public List<Integer> findAnagrams(String s, String p) {
        List<Integer> re=new ArrayList<>();
        int len=p.length();
        char[] pp=p.toCharArray();
        Arrays.sort(pp);
        for(int i=0;i+len<=s.length();i++){
            char[] ss=s.substring(i,i+len).toCharArray();
            Arrays.sort(ss);
            if(Arrays.equals(pp,ss)){
                re.add(i);
            }
        }
        return re;
    }
}
