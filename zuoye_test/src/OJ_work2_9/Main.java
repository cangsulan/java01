package OJ_work2_9;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String mode = sc.next();
        int n = sc.nextInt();
        for (int i = 0; i < n; i++) {
            ComplexNum c1 = new ComplexNum(sc.nextDouble(), sc.nextDouble());
            ComplexNum c2 = new ComplexNum(sc.nextDouble(), sc.nextDouble());
            if(mode.equals("add")){
                System.out.println(ComplexNum.add(c1, c2));
            } else if (mode.equals("sub")) {
                System.out.println(ComplexNum.sub(c1, c2));
            }else if (mode.equals("mul")) {
                System.out.println(ComplexNum.mul(c1, c2));
            }else if (mode.equals("div")) {
                try {
                    System.out.println(ComplexNum.div(c1, c2));
                } catch (ComplexDivException e) {
                    System.out.println("Error No : " + e.errNum);
                    System.out.println("Error Message : " + e.errMessage) ;
                }
            }
        }
    }
}
