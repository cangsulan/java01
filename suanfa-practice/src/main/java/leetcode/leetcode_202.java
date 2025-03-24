package leetcode;

import java.util.HashSet;

public class leetcode_202 {
    public boolean isHappy(int n) {
        HashSet<Integer> set = new HashSet<>();
        while(true){
            n=op(n);
            if(n==1){
                return true;
            }
            if(set.contains(n)){
                return false;
            }else{
                set.add(n);
            }
        }
    }
    public int op(int n){
        int sum = 0;
        while(n!=0){
            sum+=(n%10)*(n%10);
            n=n/10;
        }
        return sum;
    }
}
