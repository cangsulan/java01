package leetcode;

public class leetcode_904 {
    //题意： 找至多包含两种元素的最长子串，返回其长度
    public int totalFruit(int[] fruits) {
        int left=0;
        int right=0;
        int f1=-1,f2=-2;//水果的种类
        int size1=0,size2=0;//两种水果对应的已经采集到的数量
        f1=fruits[0];
        size1++;
        for(right=1;right<fruits.length;right++){
            if(fruits[right]!=f1){
                f2=fruits[right];
                size2++;
                right++;
                break;
            }else{
                size1++;
            }
        }
        int re=size1+size2;
        for(;right<fruits.length;right++){
            if(fruits[right]!=f1 && fruits[right]!=f2){
                int newlen=size1+size2;
                re=newlen>re?newlen:re;
                while(size1!=0 && size2!=0){
                    if(fruits[left]==f1){
                        size1--;
                    }else{
                        size2--;
                    }
                    left++;
                }
                if(size1==0){
                    f1=fruits[right];
                    size1++;
                }else if(size2==0){
                    f2=fruits[right];
                    size2++;
                }
            }else{
                if(fruits[right]==f1){
                    size1++;
                }else{
                    size2++;
                }
            }
        }
        int newlen=size1+size2;
        return newlen>re?newlen:re;
    }
}
