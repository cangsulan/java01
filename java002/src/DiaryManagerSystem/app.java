package DiaryManagerSystem;

import java.io.IOException;
import java.util.Scanner;

public class app {
    public static void main(String[] args) throws IOException {
        Student student = new Student();
        System.out.println("请输入您的姓名：");
        Scanner in = new Scanner(System.in);
        String name = in.nextLine();
        student.setName(name);
        System.out.println("请输入您的学生id：");
        String id = in.nextLine();
        student.setId(id);
        //创建一个日记的数组
        DiaryManager diaryManager = new DiaryManager();
        menu(diaryManager);
        System.out.println("输入对应的数字来选择功能");
        int select = 0;
        select = in.nextInt();
        switch (select) {
            case 1:
                diaryManager.addDiary(student);
                break;
            case 2:
                showDiary(diaryManager);
                break;
            case 3:
                searchDiary(diaryManager);
                break;
            default:

                break;
        }
    }

    public static void searchDiary(DiaryManager diaryManager) {
        System.out.println("您可以通过以下方式来查询日记：");
        System.out.println("1.通过日记编号");
        System.out.println("2.通过日记名称");
        System.out.println("3.退出");
        int select = 0;
        Scanner in = new Scanner(System.in);
        do {
            select=in.nextInt();
            switch (select) {
                case 1:
                    break;
                case 2:
                    break;
                case 3:

                    break;
                default:
                    break;
            }

        } while (select>3||select<1);
    }
    public static void searchDiaryByNum(DiaryManager diaryManager) throws IOException {
        System.out.println("请输入要待查找的日记编号；");
        Scanner in=new Scanner(System.in);
        int n=in.nextInt();
        boolean succeed=false;
        for (Diary diary : DiaryManager.list) {
            if(diary.num==n){
                System.out.println("查找成功！");
                diaryManager.showDiaryBynum(n);
                succeed=true;
                break;
            }
        }
        if(!succeed){
            System.out.println("查找失败！！");
        }
    }
    public static void searchDiaryByName(DiaryManager diaryManager) throws IOException {
        System.out.println("请输入要待查找的日记名称；");
        Scanner in=new Scanner(System.in);
        String name=in.nextLine();
        boolean succeed=false;
        for (Diary diary : DiaryManager.list) {
            if(name.equals(diary.getName())){
                System.out.println("查找成功！");
                diaryManager.showDiaryBynum(diary.getNum());
                succeed=true;
                break;
            }
        }
        if(!succeed){
            System.out.println("查找失败！！");
        }
    }
    public static void showDiary(DiaryManager diaryManager) throws IOException {
        System.out.println("请输入要查看的日记编号：");
        Scanner in=new Scanner(System.in);
        int n=in.nextInt();
        diaryManager.showDiaryBynum(n);
    }
    public static void menu(DiaryManager diaryManager) {
        System.out.println("这是一个日记管理系统");
        //排序展示日记 的 代码
        diaryManager.showDiarylist();
        System.out.println("1.添加日记");
        System.out.println("2.查看日记");
        System.out.println("3.查询日记");
    }
}
