package leetcode;

public class leetcode_209 {
    public int minSubArrayLen(int target, int[] nums) {
        int re=-1;
        int left=0;
        int right=0;
        int sum=nums[0];
        int len=-1;
        for(;right<nums.length;){
            if(sum>=target){
                len=right-left+1;
            }else{
                len=-1;
            }
            if(len!=-1){
                if(re>=0){
                    re=re>len?len:re;
                }else{
                    re=len;
                }
                left++;
                sum-=nums[left-1];
            }else{
                right++;
                if(right<nums.length){
                    sum+=nums[right];
                }
            }
        }
        if(re==-1){
            re=0;
        }
        return re;
    }
}
