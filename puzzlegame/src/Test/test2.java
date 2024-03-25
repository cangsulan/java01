package Test;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class test2 {
    public static void main(String[] args) {
        JFrame jFrame=new JFrame();
        jFrame.setSize(400,300);
        jFrame.setTitle("事件演示");
        jFrame.setAlwaysOnTop(true);
        jFrame.setLocationRelativeTo(null);
        jFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        jFrame.setLayout(null);

        //创建一个按钮对象
        JButton jtb=new JButton("点我呀");
        jtb.setBounds(0,0,100,50);
        //给按钮绑定鼠标事件
        jtb.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                System.out.println("单击");
            }

            @Override
            public void mousePressed(MouseEvent e) {
                System.out.println("按下不松");
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                System.out.println("松开");
            }

            @Override
            public void mouseEntered(MouseEvent e) {
                System.out.println("划入");
            }

            @Override
            public void mouseExited(MouseEvent e) {
                System.out.println("划出");
            }
        });


        //给按钮添加动作监听
        jtb.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("哒咩，不要点我哟！！！");
            }
        });

        jFrame.getContentPane().add(jtb);
        jFrame.setVisible(true);
    }
}
