package com.practice6_2;
//Java作业二，第 6 题 （选做）
//6.选做：编写一个使用TCP协议进行网络通信的程序，双方建立连接后，各自将一个本地文件传送到对方并各自保存到本地。
//该文件为客户端的代码
import java.io.*;
import java.net.Socket;

public class Client {
    private static final String host = "localhost";
    private static final int port = 12345;
    private static final String sendFileName = "C:\\Users\\30241\\Desktop\\test6\\客户端发送和接收\\客户端要发的文件.txt"; // 本地要发送的文件
    private static final String receiveSavePath = "C:\\Users\\30241\\Desktop\\test6\\客户端发送和接收\\客户端接收到的文件.txt";  // 接收到的文件保存路径

    public static void main(String[] args) throws IOException, InterruptedException {
        Socket socket = new Socket(host, port);
        System.out.println("已连接到服务端！");

        // 发送文件到服务器
        sendFile(socket, sendFileName);

        socket.close();
        // 接收服务器发送的文件
        Socket socket2 = new Socket(host, port);
        receiveFile(socket2, receiveSavePath);

        System.out.println("客户端 文件传输和接收完毕！");

    }

    private static void receiveFile(Socket socket, String filePath) throws IOException {
        InputStream in = socket.getInputStream();
        FileOutputStream fileOut = new FileOutputStream(filePath);
        byte[] buffer = new byte[4096];
        int bytesRead;
        while ((bytesRead = in.read(buffer)) != -1) {
            fileOut.write(buffer, 0, bytesRead);
        }
        fileOut.close();
        System.out.println("客户端接收到了文件，保存在：" + filePath);

    }

    private static void sendFile(Socket socket, String filePath) throws IOException {
        FileInputStream fileIn = new FileInputStream(filePath);
        OutputStream out = socket.getOutputStream();
        byte[] buffer = new byte[4096];
        int bytesRead;
        while ((bytesRead = fileIn.read(buffer)) != -1) {
            out.write(buffer, 0, bytesRead);
        }
        out.flush();
        out.close();
        System.out.println("客户端发送了文件：" + filePath);

    }
}
