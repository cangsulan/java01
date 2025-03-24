package leetcode;

import java.util.Arrays;

public class leetcode_135 {

    public int candy(int[] ratings) {
        //先每个人都发一个，然后双指针遍历，看是否要增加糖果，只要返回最终的数目即可
        if(ratings.length==1){
            return 1;
        }
        //数组至少2个元素
        //还是有必要记录每个孩子发了多少糖果，所以再来一个数组
        int[] compareArray=new int[ratings.length];
        Arrays.fill(compareArray,0);
        int sum=0;
        for(int i=1;i<ratings.length;i++){
            //比较大小
            if(ratings[i]<ratings[i-1]){//前者大
                compareArray[i]=-1;
            }else if(ratings[i]>ratings[i-1]){//后者大
                compareArray[i]=1;
            }//一样大的话，不用管
        }
        int[] countArray=new int[ratings.length];
        Arrays.fill(countArray,1);
        int deCount=0;
        //得到了比较的关系数组
        for(int i=0;i<compareArray.length;i++){
            if(compareArray[i]==-1){
                deCount++;
            }else{
                if(compareArray[i]==1){
                    countArray[i]=countArray[i-1]+1;
                }
                if(deCount!=0){
                    for(int j=1;j<=deCount;j++){
                        if(countArray[i-1-j]<=countArray[i-j]) {
                            countArray[i - 1 - j] = countArray[i - j] + 1;
                        }
                    }
                    deCount=0;
                }

            }
        }
        if(deCount!=0){
            int i=countArray.length;
            for(int j=1;j<=deCount;j++){
                if(countArray[i-1-j]<=countArray[i-j]) {
                    countArray[i - 1 - j] = countArray[i - j] + 1;
                }
            }
            deCount=0;
        }
        for (int c : compareArray) {
            System.out.print(" "+c);
        }
        System.out.println();

        for (int c : countArray) {
            System.out.print(" "+c);
        }
        System.out.println();
        for(int i=0;i<countArray.length;i++){
            sum+=countArray[i];
        }
        return sum;
    }
}
