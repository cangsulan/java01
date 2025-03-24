package leetcode;

public class leetcode_844 {
    public boolean backspaceCompare(String s, String t) {
        //转为char字符数组
        char[] ss= s.toCharArray();
        char[] tt = t.toCharArray();
        return op(ss).equals(op(tt));
    }
    public String op(char[] ss){
        int slow=0, fast=0;
        for(;fast<ss.length;fast++){
            if(ss[fast]!='#'){
                ss[slow]=ss[fast];
                slow++;
            }else if(slow>0){
                slow--;
            }
        }
        if(slow<0){
            slow=0;
        }
        return new String(ss).substring(0,slow);
    }
}
