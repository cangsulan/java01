package Test;

public class Temp2 {
    public static void main(String[] args) {
        double num=676.0/693;
        num-=(Math.log(num)/Math.log(2))*num;
        System.out.println(num);
    }
}
