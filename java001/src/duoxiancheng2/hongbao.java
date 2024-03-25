package duoxiancheng2;

import java.util.Random;

public class hongbao implements Runnable {
    private static double bao = 100;
    static int count = 3;
    //最小的红包金额：
    static final double MIN = 0.01;
//    static Random r1 = new Random();
//    static double q1 = r1.nextDouble(bao);
//    static double q2 = r1.nextDouble(bao - q1);
//    static double q3 = bao - q1 - q2;

    @Override
    public void run() {
        synchronized (hongbao.class) {
            if (count==0) {
                System.out.println(Thread.currentThread().getName()+" 没有抢到红包。。");
            }else{
                count--;
                if(count==0){
                    System.out.println(Thread.currentThread().getName()+" 抢到了 "+bao+" 元！");
                }else{
                    Random r2 = new Random();
                    double jin= r2.nextDouble(MIN,bao-count*MIN);
                    System.out.println(Thread.currentThread().getName()+" 抢到了 "+jin+" 元！");
                    bao-=jin;
                }
            }
        }
    }
}
