package duoxiancheng1;

public class dxc001 {
    public static void main(String[] args) {
        //多线程的第一种方式：
//        MyThread1 t1 = new MyThread1();
//        MyThread1 t2 = new MyThread1();
//        t1.setName("线程1");
//        t2.setName("线程2");
//        t1.start();
//        t2.start();
        //第二种方法：
        MyRun1 mr = new MyRun1();
        Thread t1 = new Thread(mr);
        Thread t2 = new Thread(mr);
        t1.setName("线程1");
        t2.setName("线程2");
        t1.start();
        t2.start();

    }
}
