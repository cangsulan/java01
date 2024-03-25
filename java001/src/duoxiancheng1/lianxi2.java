package duoxiancheng1;

public class lianxi2 {
    public static void main(String[] args) {
        dayin1 dy = new dayin1();
        Thread t1 = new Thread(dy);
        Thread t2 = new Thread(dy);
        t1.setName("线程1");
        t2.setName("线程2");
        t1.start();
        t2.start();
    }
}
