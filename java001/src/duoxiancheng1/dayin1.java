package duoxiancheng1;

public class dayin1 implements Runnable {
    private static int num = 1;

    @Override
    public void run() {
        while (true) {
            synchronized (dayin1.class) {
                if (num <= 200) {
                    if (num % 2 != 0) {
                        System.out.println(Thread.currentThread().getName() + " 输出了" + num);
                    }
                    num++;
                } else {
                    break;
                }

            }
        }

    }
}
