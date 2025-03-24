package leetcode;

public class leetcode_26 {
    public int removeDuplicates(int[] nums) {
        if(nums.length==0){
            return 0;
        }
        int slow=0;
        for(int fast=1;fast<nums.length;){
            if(nums[slow]!=nums[slow+1]){
                slow++;
                fast++;
            }else{
                if(nums[fast]!=nums[slow+1]){
                    nums[slow+1]=nums[fast];

                }else{
                    fast++;
                }
            }
        }
        return slow+1;
    }
}
