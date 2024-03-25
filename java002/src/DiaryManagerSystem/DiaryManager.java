package DiaryManagerSystem;

import java.io.*;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Scanner;

public class DiaryManager {
     static public ArrayList<Diary> list=new ArrayList<>();
     static public String filepath0="java002//DiaryHome//";
     static public int num=0;
     public void showDiaryBynum(int n) throws IOException {
         for (int i = 0; i < list.size(); i++) {
             if(list.get(i).num==n){
                 Diary diary=list.get(i);
                 System.out.println("已找到编号为 "+n+" 的日记：");
                 System.out.println("日记"+diary.num+" "+"作者："+diary.getStu().getName()+" 浏览量："+diary.getViews());
                 if (diary.getRating()!=-1) {
                     System.out.println("该日记的评分为："+diary.getRating());
                 }else {
                     System.out.println("该日记暂无评分。。");
                 }
                 System.out.println("该日记的内容为：");
                 File file=new File(diary.getContent());
                 FileReader fr=new FileReader(file);
                 int b;
                 while((b= fr.read())!=-1){
                     System.out.print((char) b);
                 }
                 fr.close();
                 return;
             }
         }
         System.out.println("没有找到该编号的日记！");
     }
     public void showDiarylist(){
        //根据日记创建时的编号来排序展示日记内容
         list.sort(new Comparator<Diary>() {
             @Override
             public int compare(Diary o1, Diary o2) {
                 return o1.num- o2.num;
             }
         });
         for (Diary diary : list) {
             System.out.println("日记"+diary.num+" "+"作者："+diary.getStu().getName()+" 浏览量："+diary.getViews());
         }
     }
     public void addDiary(Student student) throws IOException {
         Diary newdiary = new Diary();
         newdiary.setStu(student);
         System.out.println("请输入您日记的名称name：（注意日记名称不要太长）");
         Scanner in=new Scanner(System.in);
         newdiary.setName(in.nextLine());
         System.out.println("请输入您日记的内容：以886结尾表示输入完毕");
         BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
         String str;
         newdiary.num=num;
         String filepath=filepath0+"diary"+num+".txt";
         num++;
         File file=new File(filepath);
         FileWriter fileWriter=new FileWriter(file);
         do {
             str=br.readLine();
             fileWriter.write(str);
         }while (!str.equals("886"));
         fileWriter.close();
        newdiary.setContent(filepath);
        newdiary.setRating(-1);//评分默认为-1,，-1表示该日记暂无评分
         newdiary.setViews(0);//默认的浏览量为0
         newdiary.setRatingNumber(0);//评分人数默认为0
         list.add(newdiary);
     }
}
