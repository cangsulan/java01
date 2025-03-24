package com.practice7;

import java.io.File;

//Java作业二， 第 7 题
//7.编写一个列出某目录下所有子目录及文件的java程序（参照dos命令dir /s的显示结果）。
public class Test {
    public static void main(String[] args) {
        String dirName="C:\\Users\\30241\\Desktop\\大二下\\计网";
        File dir=new File(dirName);
        if(!dir.exists()||!dir.isDirectory()){
            System.out.println("文件夹名称有误！");
            return;
        }
        System.out.println("文件夹："+dir.getName());
        listAllContents(dir,1);
    }

    public static void listAllContents(File dir,int level){
        File[] files=dir.listFiles();
        if(files!=null&&files.length!=0) {
            for(File file:files) {
                if(file.isDirectory()) {
                    System.out.print("|");
                    for (int i = 0; i < level; i++) {
                        System.out.print("     ");
                    }
                    System.out.println("|__文件夹："+file.getName());
                    listAllContents(file,level+1);
                }else{
                    System.out.print("|");
                    for (int i = 0; i < level; i++) {
                        System.out.print("     ");
                    }
                    System.out.println("|__文件："+file.getName());
                }
            }
        }
    }
}
