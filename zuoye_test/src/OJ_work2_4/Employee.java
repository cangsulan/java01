package OJ_work2_4;

public abstract class Employee implements Comparable<Employee>{
    String firstName;
    String lastName;
    String socialSecurityNumber;

    abstract double earning();

    public Employee() {

    }
    public Employee(String firstName, String lastName, String socialSecurityNumber) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.socialSecurityNumber = socialSecurityNumber;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getSocialSecurityNumber() {
        return socialSecurityNumber;
    }

    @Override
    public String toString() {
        return "firstName:"+getFirstName()+"; "
                +"lastName:"+getLastName()+"; "
                +"socialSecurityNumber:"+getSocialSecurityNumber()+"; "
                +"earning:"+String.format("%.2f",earning());
    }

    @Override
    public int compareTo(Employee o) {
        double re = this.earning() - o.earning();
        if(re>0) {
            return 1;
        }else if(re < 0){
            return -1;
        }else {
            return 0;
        }
    }
}
