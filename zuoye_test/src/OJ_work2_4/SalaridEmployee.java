package OJ_work2_4;

public class SalaridEmployee extends Employee{
    double weeklySalary;

    public SalaridEmployee(double weeklySalary) {
        this.weeklySalary = weeklySalary;
    }

    public SalaridEmployee(String firstName, String lastName, String socialSecurityNumber) {
        super(firstName, lastName, socialSecurityNumber);
    }

    public double getWeeklySalary() {
        return weeklySalary;
    }

    public void setWeeklySalary(double weeklySalary) {
        this.weeklySalary = weeklySalary;
    }

    @Override
    double earning() {
        return weeklySalary*4;
    }

}
