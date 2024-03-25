package DiaryManagerSystem;


import java.util.Scanner;

public class Diary {
    String content;//日记内容文件的路径
    int rating;
    int ratingNumber;
    int views;
    Student stu;
    int num;//编号
    String name;//日记的名称

    public int getRatingNumber() {
        return ratingNumber;
    }

    public void setRatingNumber(int ratingNumber) {
        this.ratingNumber = ratingNumber;
    }

    public Diary(String content, int rating, int ratingNumber, int views, Student stu, int num, String name) {
        this.content = content;
        this.rating = rating;
        this.ratingNumber = ratingNumber;
        this.views = views;
        this.stu = stu;
        this.num = num;
        this.name = name;
    }


    //接下来要设置一些方法来对日记进行以下操作：
    //打评分
    //日记内容的检索
    public void DiaryMenu() {
        System.out.println("您在查看该日记的同时，还可进行以下操作：");
        System.out.println("1.进行评分");
        System.out.println("2.内容检索");
        System.out.println("3.退出该日记");
        int select = 0;
        Scanner in = new Scanner(System.in);
        do {
            select=in.nextInt();
            switch (select) {
                case 1:
                    makeRating();
                    break;
                case 2:
                    contentSearch();
                    break;
                case 3:

                    break;
                default:
                    break;
            }

        } while (select>3||select<1);

    }

    public void makeRating() {
        if (this.rating != -1) {
            System.out.println("您正在为这篇游学日记打评分，当前该日记评分为：" + this.rating);
        } else {
            System.out.println("您正在为这篇游学日记打评分，当前该日记暂无评分。。");
        }
        System.out.println("请输入一个0-10的整数来进行评分：");
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        if (this.rating == -1) {
            this.rating = n;
            this.ratingNumber++;
        } else {
            rating = (rating * ratingNumber + n) / (ratingNumber + 1);
            ratingNumber++;
        }
        System.out.println("评分成功，您评分后 该日记评分变为：" + this.rating);
    }

    public void contentSearch() {

    }

    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


    public Diary() {
    }

    public String getContent() {
        return content;
    }


    public void setContent(String content) {
        this.content = content;
    }


    public int getRating() {
        return rating;
    }


    public void setRating(int rating) {
        this.rating = rating;
    }


    public int getViews() {
        return views;
    }


    public void setViews(int views) {
        this.views = views;
    }


    public Student getStu() {
        return stu;
    }


    public void setStu(Student stu) {
        this.stu = stu;
    }

    public String toString() {
        return "Diary{content = " + content + ", rating = " + rating + ", views = " + views + ", stu = " + stu + "}";
    }
}