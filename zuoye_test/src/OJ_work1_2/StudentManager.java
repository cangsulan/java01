package OJ_work1_2;

import java.util.HashSet;
import java.util.Iterator;

class StudentManager {
    HashSet<Student> manager;

    public StudentManager() {
        this.manager=new HashSet<>();
    }
    public void addStudent(Student stu){
        boolean suc=this.manager.add(stu);
        if(!suc){
            System.out.println("Students already exist");
        }else {
            System.out.println("Add success");
        }
    }
    public void deleteStudent(String id){
        if(this.manager.size()==0){
            System.out.println("Students do not exist");
            return;
        }
        Iterator<Student> iterator = this.manager.iterator();
        while(iterator.hasNext()){
            Student student =iterator.next();
            if(student.id.equals(id)){
                iterator.remove();
                System.out.println("Delete success");
                return;
            }
        }
        System.out.println("Students do not exist");
    }
    public void changeStudent(String id,int i1,int i2,int i3){
        if(this.manager.size()==0){
            System.out.println("Students do not exist");
            return;
        }
        Iterator<Student> iterator = this.manager.iterator();
        while(iterator.hasNext()){
            Student student =iterator.next();
            if(student.id.equals(id)){
                student.MathGrade=i1;
                student.EnglishGrade=i2;
                student.JavaGrade=i3;
                System.out.println("Update success");
                return;
            }
        }
        System.out.println("Students do not exist");
    }
    public void printStudent(String id){
        if(this.manager.size()==0){
            System.out.println("Students do not exist");
            return;
        }
        for (Student student : this.manager) {
            if (student.id.equals(id)) {
                System.out.println("Student ID:"+student.id);
                System.out.println("Name:"+student.name);
                double math=student.MathGrade;
                double english=student.EnglishGrade;
                double java=student.JavaGrade;
                double average = (math+english+java)/3;
                System.out.printf("Average Score:%.1f\n",average);
                return;
            }
        }
        System.out.println("Students do not exist");
    }
}