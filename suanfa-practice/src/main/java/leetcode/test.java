package leetcode;

import java.util.Arrays;

public class test
{
    public static void main(String[] args){
        int[] nums={1,3,5,7,9,3,5,6};
        Arrays.sort(nums);
        int target=6;
        int left =0;
        int right=nums.length-1;
        while(left<=right){
            int mid=(left+right)>>1;
            if(nums[mid]==target){
                System.out.println(mid);
                break;
            }else if(nums[mid]<target){
                left=mid+1;
            }else{
                right=mid-1;
            }
        }
        System.out.println(-1);
    }
}