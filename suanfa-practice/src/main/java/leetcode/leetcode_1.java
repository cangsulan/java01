package leetcode;

import java.util.Arrays;

public class leetcode_1 {
    public int[] twoSum(int[] num, int target) {
        //先排序，从大范围开始，不断往内缩小
        int[] nums=new int[num.length];
        nums=Arrays.copyOf(num,num.length);
        Arrays.sort(nums);
        int left=0;
        int right=nums.length-1;
        while(left<right){
            int sum=nums[left]+nums[right];
            if(sum>target){
                right--;
            }else if(sum<target){
                left++;
            }else{
                //找到了
                int[] re={-1,-1};
                for(int i=0;i<num.length;i++){
                    if(num[i]==nums[left] && re[0]==-1){
                        re[0]=i;
                    }else if(num[i]==nums[right]&& re[1]==-1){
                        re[1]=i;
                    }
                }
                return re;
            }
        }
        return null;
    }
}
