package duoxiancheng1;

public class sellTicket1 implements Runnable {
    private static int ticket = 100;

    @Override
    public void run() {
        while (true) {
            synchronized (sellTicket1.class) {
                if (ticket >= 10) {
                    ticket--;
                    System.out.println(Thread.currentThread().getName() + "已经卖掉了1张票，还剩下" + ticket + "张票哦！！");
                    try {
                        Thread.sleep(30);
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                }else {
                    break;
                }
            }
        }
    }
}
