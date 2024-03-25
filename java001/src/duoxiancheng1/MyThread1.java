package duoxiancheng1;

public class MyThread1 extends Thread {
    @Override
    public void run() {
        //书写线程要执行的方法
        for (int i = 0; i < 100; i++) {
            System.out.println(this.getName()+"  helloWorld");
        }
    }
}
