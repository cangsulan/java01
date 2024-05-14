package OJ_work2_4;

public class basePlusCommisionEmployee extends CommisionEmployee{
    double baseSalary;//月基本工资

    public basePlusCommisionEmployee(String firstName, String lastName, String socialSecurityNumber) {
        super(firstName, lastName, socialSecurityNumber);
    }

    public double getBaseSalary() {
        return baseSalary;
    }

    public void setBaseSalary(double baseSalary) {
        this.baseSalary = baseSalary;
    }

    @Override
    double earning() {
        return grossSales*commissionRate+baseSalary;
    }
}
