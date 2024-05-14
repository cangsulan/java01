package OJ_work1_2;

class Student {
    String id;
    String name;
    int MathGrade;
    int EnglishGrade;
    int JavaGrade;

    public Student() {
    }
    public Student(String id, String name, int mathGrade, int englishGrade, int javaGrade) {
        this.id = id;
        this.name = name;
        MathGrade = mathGrade;
        EnglishGrade = englishGrade;
        JavaGrade = javaGrade;
    }
    @Override
    public int hashCode() {
        return this.MathGrade+this.JavaGrade+this.EnglishGrade+this.name.hashCode()+this.id.hashCode();
    }
    @Override
    public boolean equals(Object obj) {
        boolean re=true;
        Student stu=(Student)obj;
        if(!this.id.equals(stu.id)){
            re=false;
        }
        if(!this.name.equals(stu.name)){
            re=false;
        }
        if(!(this.MathGrade ==stu.MathGrade)){
            re=false;
        }
        if(!(this.EnglishGrade ==stu.EnglishGrade)){
            re=false;
        }
        if(!(this.JavaGrade ==stu.JavaGrade)){
            re=false;
        }
        return re;
    }
}