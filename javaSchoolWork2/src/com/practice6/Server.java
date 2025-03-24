package com.practice6;
//Java作业二，第 6 题 （选做）
//6.选做：编写一个使用TCP协议进行网络通信的程序，双方建立连接后，各自将一个本地文件传送到对方并各自保存到本地。
//该文件为服务端的代码
import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
    private static final int port = 12345;
    private static final String sendFileName = "C:\\Users\\30241\\Desktop\\test6\\服务端发送和接收\\服务端要发送的文件.txt"; // 本地要发送的文件
    private static final String receiveSavePath = "C:\\Users\\30241\\Desktop\\test6\\服务端发送和接收\\服务端接收到的文件.txt"; // 接收到的文件保存路径

    public static void main(String[] args) throws IOException, InterruptedException {
        ServerSocket serverSocket = new ServerSocket(port);
        System.out.println("服务端准备就绪！");
        Socket socket = serverSocket.accept();
        System.out.println("成功连接到了客户端");
        // 接收客户端发送的文件
        receiveFile(socket, receiveSavePath);
        socket.close();

        // 发送文件到客户端
        Socket socket2 = serverSocket.accept();
        sendFile(socket2, sendFileName);

        System.out.println("服务端 接收和传输文件完成！");
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
        in.close();
        System.out.println("服务端接收到了文件，保存在：" + filePath);
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
        fileIn.close();
        System.out.println("服务端发送了文件：" + filePath);
    }
}
