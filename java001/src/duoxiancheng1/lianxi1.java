package duoxiancheng1;

public class lianxi1 {
    public static void main(String[] args) {
        sellTicket1 st = new sellTicket1();
        Thread t1 = new Thread(st);
        Thread t2 = new Thread(st);
        t1.setName("窗口1");
        t2.setName("窗口2");
        t1.start();
        t2.start();
    }
}
