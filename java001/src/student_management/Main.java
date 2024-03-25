package student_management;

import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        ArrayList<Student> list = new ArrayList<>();
        loop:while(true){
            showMenu();
            String select;
            Scanner sc =new Scanner(System.in);
            select = sc.next();
            switch (select) {
                case "1":addMember(list);
                    break;
                case "2":deleteMember(list);
                    break;
                case "3":changeMember(list);
                    break;
                case "4":
                    System.out.println("请输入要查询的学生的ID: ");
                    String id=sc.next();
                    int index=findMember(list,id);
                    if(index==-1){
                        System.out.println("没有找到该学生: ");
                    }else{
                        showStudent(list,index);
                    }
                    break;
                case "5" :
                    System.out.println("正在退出系统。。。");
                    break loop;
                case "6":
                    for(int i=0;i<list.size();i++){
                        showStudent(list,i);
                    }
                default:
                    System.out.println("暂无此选项，请重新输入选择： ");
            }
        }
    }
    public static void showStudent(ArrayList<Student> list,int index){
        System.out.print("Id： "+list.get(index).getId()+"\t");
        System.out.print("姓名： "+list.get(index).getName()+"\t");
        System.out.print("年龄： "+list.get(index).getAge()+"\t");
        System.out.print("家庭住址： "+list.get(index).getAddress()+"\n");
    }
    public static void addMember(ArrayList<Student> list){
        Student stu=new Student();
        Scanner sc=new Scanner(System.in);

        String id;
        String name;
        int age;
        String address;
        System.out.println("请输入要添加学生的ID: ");
        id =sc.next();
        int index=findMember(list,id);
        if(index!=-1){
            System.out.println("已有该ID的学生，添加失败");
            return;
        }
        stu.setId(id);
        System.out.println("请输入要添加学生的姓名: ");
        name=sc.next();
        stu.setName(name);
        System.out.println("请输入要添加学生的年龄: ");
        age=sc.nextInt();
        stu.setAge(age);
        System.out.println("请输入要添加学生的家庭地址: ");
        address=sc.next();
        stu.setAddress(address);
        list.add(stu);
    }
    public static void deleteMember(ArrayList<Student> list){
        if(list.isEmpty()){
            System.out.println("当前无学生信息，请添加后再删除");
            return;
        }
        System.out.println("请输入要删除掉的学生的ID: ");
        Scanner sc=new Scanner(System.in);
        String id=sc.next();
        int index=findMember(list,id);
        if(index==-1){
            System.out.println("没有该ID的学生，删除失败");
        }else{
            list.remove(index);
        }

    }
    public static void changeMember(ArrayList<Student> list){
        if(list.isEmpty()){
            System.out.println("当前无学生信息，请添加后再修改");
            return;
        }
        System.out.println("请输入要修改的学生的ID: ");
        Scanner sc=new Scanner(System.in);
        String id=sc.next();
        int index=findMember(list,id);
        if(index==-1){
            System.out.println("没有该ID的学生，修改失败");
            return;
        }else{
            String newId;
            String name;
            int age;
            String address;
            System.out.println("请输入该学生新的ID: ");
            newId =sc.next();
            int index2=findMember(list,newId);
            if(index2!=-1){
                System.out.println("已有该ID的学生");
                return;
            }
            list.get(index).setId(newId);
            System.out.println("请输入该学生新的姓名: ");
            name=sc.next();
            list.get(index).setName(name);
            System.out.println("请输入该学生新的年龄: ");
            age=sc.nextInt();
            list.get(index).setAge(age);
            System.out.println("请输入该学生新的家庭地址: ");
            address=sc.next();
            list.get(index).setAddress(address);
        }
    }
    public static int findMember(ArrayList<Student> list,String id){
        if(list.isEmpty()){
            System.out.println("当前无学生信息，请添加后再查询");
            return -1;
        }
        //返回查询到的学生在集合中的index
        for(int i=0;i<list.size();i++){
            if(id.equals(list.get(i).getId())){
                return i;
            }
        }
        return -1;//-1表示没有找到该学生
    }
    //显示控制台下的主页面：
    public static void showMenu(){
        System.out.println("------------------欢迎来到黑马学生管理系统------------------");
        System.out.println("1. 添加学生");
        System.out.println("2. 删除学生");
        System.out.println("3. 修改学生");
        System.out.println("4. 查询学生");
        System.out.println("5. 退出系统");
        System.out.println("6. 显示已有学生信息");
        System.out.println("请输入您的选择: ");
    }
}
