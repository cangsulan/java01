package leetcode;

public class leetcode_367 {
    public boolean isPerfectSquare(int num) {
        long re=binarySearch(num);
        return (re==-1)?false:true;
    }
    //用二分查找来确定其开方值
    public long binarySearch(int num){
        long mid;
        long left=0;
        long right=num;
        while(left<=right){
            mid=(left+right)>>>1;
            if(mid*mid<num){
                left=mid+1;
            }else if(mid*mid>num){
                right=mid-1;
            }else{
                return mid;
            }
        }
        return -1;
    }
}
