package OJ_work2_4;

public class HourlyEmployee extends Employee{
    double wage;//每个小时的工钱
    double hours;//月工作小时数

    public HourlyEmployee(double wage, double hours) {
        this.wage = wage;
        this.hours = hours;
    }

    public HourlyEmployee(String firstName, String lastName, String socialSecurityNumber) {
        super(firstName, lastName, socialSecurityNumber);
    }

    public double getWage() {
        return wage;
    }

    public void setWage(double wage) {
        this.wage = wage;
    }

    public double getHours() {
        return hours;
    }

    public void setHours(double hours) {
        this.hours = hours;
    }

    @Override
    double earning() {
        return wage*hours;
    }
}
