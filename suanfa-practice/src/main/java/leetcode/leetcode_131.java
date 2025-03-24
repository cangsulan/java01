package leetcode;

import java.util.ArrayList;
import java.util.List;

/**
 * @author 30241
 * @version 1.0
 * @description: leetcode131：分割回文串
 * @date 2025/2/26 下午7:07
 */
public class leetcode_131 {

    public List<List<String>> result;

    public List<String> list;

    public List<List<String>> partition(String s) {
        // 回溯法暴力搜索，感觉有点麻烦，又要判断回文，又要字符串操作
        result=new ArrayList<>();
        list=new ArrayList<>();

        backTrack(s,0);
        return result;
    }

    public void backTrack(String s,int startIndex){
        //终止
        if(startIndex == s.length()){
            result.add(new ArrayList<>(list));
            System.out.println("result 添加了："+list);
            return;
        }
        //递归
        for(int i=startIndex+1;i<s.length();i++){
            //开始切割
            String subStr=s.substring(startIndex,i);
            if(isRightString(subStr)){
                list.add(subStr);
                System.out.println("list 添加了："+subStr);
            }else{
                continue;
            }
            backTrack(s,i);
            list.remove(list.size()-1);
        }
    }

    public boolean isRightString(String str) {
        char[] cList = str.toCharArray();
        int left = 0;
        int right = str.length() - 1;
        while (left <= right) {
            if (cList[left] != cList[right]) {
                return false;
            }
            left++;
            right--;
        }
        return true;
    }
}
