package duoxiancheng2;

public class lianxi4 {
    public static void main(String[] args) {
        hongbao hb = new hongbao();
        Thread t1 = new Thread(hb);
        Thread t2 = new Thread(hb);
        Thread t3 = new Thread(hb);
        Thread t4 = new Thread(hb);
        Thread t5 = new Thread(hb);
        t1.setName("线程1");
        t2.setName("线程2");
        t3.setName("线程3");
        t4.setName("线程4");
        t5.setName("线程5");
        t1.start();
        t2.start();
        t3.start();
        t4.start();
        t5.start();
    }
}
