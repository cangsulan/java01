package OJ_work2_8;

import java.io.*;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws IOException {
        Scanner sc = new Scanner(System.in);

        //文件操作：
        FileOutputStream file = new FileOutputStream("test.txt");
        file.write(sc.nextLine().getBytes());
        file.close();
        String mode = sc.nextLine();
        switch (mode) {
            case "r":
                break;
            case "r+":
                //打开文件 不会 清空原有内容
                RandomAccessFile raf = new RandomAccessFile("test.txt", "rw");
                raf.seek(0);
                raf.write(sc.nextLine().getBytes());
                raf.close();
                break;
            case "w":
            case "w+":
                //打开文件 会 清空原有内容
                FileWriter fw = new FileWriter(new File("test.txt"));
                BufferedWriter bw = new BufferedWriter(fw);
                bw.write(sc.nextLine());
                bw.close();
                break;
            case "a":
            case "a+":
                //追加内容
                FileWriter fw2 = new FileWriter(new File("test.txt"),true);
                BufferedWriter bw2 = new BufferedWriter(fw2);
                bw2.write(sc.nextLine());
                bw2.close();

                break;
            case "x":
            case "x+":
                //如果文件存在则操作失败，如果文件不存在 则新建一个文件 并开始写入
                break;
            default:
                break;
        }
        //输出文件内容：
        BufferedReader br = new BufferedReader(new FileReader("test.txt"));
        String line;
        while((line=br.readLine())!=null){
            System.out.println(line);
        }
        br.close();
    }
}
