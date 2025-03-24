package leetcode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class leetcode_18 {
    public List<List<Integer>> fourSum(int[] nums, int target) {
        //先排序，按照“三数之和”的双指针解法，多加一层循环，尝试解决
        List<List<Integer>> re = new ArrayList<>();
        if(nums.length<4){
            return re;
        }
        Arrays.sort(nums);
        for(int a=0;a<nums.length;a++){
            if( nums[a] > target){
                break;
            }
            if(a>0 && nums[a]==nums[a-1]){
                continue;
            }
            for(int b=a+1;b<nums.length;b++){
                if(b > a+1 && nums[b]==nums[b-1]){
                    continue;
                }
                int c=b+1;
                int d=nums.length-1;
                while(c<d){
                    int sum=nums[a]+nums[b]+nums[c]+nums[d];
                    if(sum>target){
                        d--;
                    }else if(sum<target){
                        c++;
                    }else{
                        re.add(Arrays.asList(new Integer[]{nums[a], nums[b], nums[c], nums[d]}));
                        c++;
                        d--;
                        while(c<=d && nums[c]==nums[c-1]){
                            c++;
                        }
                        while(c<=d && nums[d]==nums[d+1]){
                            d--;
                        }
                    }
                }
            }
        }

        return re;
    }
}
