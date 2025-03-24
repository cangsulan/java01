package leetcode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class leetcode_15 {
    public List<List<Integer>> threeSum(int[] nums) {
        Arrays.sort(nums);
        List<List<Integer>> result=new ArrayList<>();
        //使用 排序 + 双指针 的思路来写
        for(int i=0;i<nums.length-2;i++){
            if(nums[i]>0){
                break;
            }
            if(i>0 && nums[i]==nums[i-1]){//去重第一个元素
                continue;
            }
            int left = i+1;
            int right=nums.length-1;
            while(left<right){
                int sum=nums[i]+nums[left]+nums[right];
                if(sum<0){
                    left++;
                }else if(sum>0){
                    right--;
                }else{
                    //找到了,
                    List<Integer> list = new ArrayList<>();
                    list.add(nums[i]);
                    list.add(nums[left]);
                    list.add(nums[right]);
                    result.add(list);
                    left++;
                    right--;
                    while(left>i+1 && left<nums.length && nums[left]==nums[left-1]){
                        left++;//去重第2个元素
                    }
                    while(right>i && right<nums.length-1 && nums[right]==nums[right+1]){
                        right--;//去重第3个元素
                    }
                }
            }
        }
        return result;
    }
}
