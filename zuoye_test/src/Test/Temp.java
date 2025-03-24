package Test;

import java.util.Arrays;
import java.util.Scanner;

public class Temp {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int[] nums=new int[6];
        for(int i=0;i<nums.length;i++){
            nums[i]=sc.nextInt();
        }
        double sum=sc.nextDouble();
        double result=0;
        Arrays.sort(nums);
        System.out.print("nums为:");
        for (int i : nums) {
            System.out.print(" "+i);
        }
        System.out.println();
        //计算熵：
        for(int i=0;i<nums.length;i++){
            if(nums[i]==0){
                continue;
            }
            double temp=nums[i]*1.00000/sum;
            System.out.println(temp);
            temp=temp*(Math.log(temp)/Math.log(2));
            System.out.println(temp);
            result-=temp;
        }
        System.out.print("熵为:");
        System.out.println(result);
        //计算纯度：
        double max=nums[5];
        //System.out.println("max为:"+max);
        System.out.print("纯度为:");
        System.out.println((max / sum));
    }
}
