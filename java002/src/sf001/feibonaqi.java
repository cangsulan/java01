package sf001;

public class feibonaqi {
    public static int fn(int n){
        //递归实现斐波那契数列：
        if(n==0){
            return 0;
        }else if (n==1){
            return 1;
        }else {
            return fn(n-1)+fn(n-2);
        }
    }
    public static void main(String[] args) {
        System.out.println(fn(8));
    }
}
