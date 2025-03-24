package leetcode;

import java.util.HashMap;

public class leetcode_904_jiefa2 {
    //题意： 找至多包含两种元素的最长子串，返回其长度
    public int totalFruit(int[] fruits) {
        int left=0;
        int right=0;
        int type=0;//用来统计水果的种类
        //用哈希表来统计 每种水果的数量
        int re=0;
        HashMap<Integer,Integer> map=new HashMap<>();
        for(;right<fruits.length;right++){
            while(type>2){
                map.put(fruits[left],map.get(fruits[left])-1);
                if(map.get(fruits[left])==0){
                    type--;
                }
                left++;
            }
            if(map.containsKey(fruits[right]) && map.get(fruits[right])!=0){
                map.put(fruits[right],map.get(fruits[right])+1);
            }else{
                map.put(fruits[right],1);
                type++;
            }
            if(type<=2){
                re=((right-left+1)>re)?(right-left+1):re;
            }
        }
        return re;
    }
}
