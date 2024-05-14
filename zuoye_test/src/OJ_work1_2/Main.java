package OJ_work1_2;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        StudentManager sm=new StudentManager();
        Scanner cin =new Scanner(System.in);
        int n=cin.nextInt();
        for (int i = 0; i < n; i++) {
            int operations=cin.nextInt();
            switch (operations){
                case 1:
                    String id=cin.next();
                    String name=cin.next();
                    int math=cin.nextInt();
                    int english=cin.nextInt();
                    int java=cin.nextInt();
                    Student stu=new Student(id,name,math,english,java);
                    sm.addStudent(stu);
                    break;
                case 2:
                    sm.deleteStudent(cin.next());
                    break;
                case 3:
                    sm.changeStudent(cin.next(), cin.nextInt(), cin.nextInt(), cin.nextInt());
                    break;
                case 4:
                    sm.printStudent(cin.next());
                    break;
                default:
                    break;
            }
        }
    }
}