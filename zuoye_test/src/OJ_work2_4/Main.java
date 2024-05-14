package OJ_work2_4;

import java.util.Arrays;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        int n;
        Scanner sc = new Scanner(System.in);
        n = sc.nextInt();
        Employee[] employees = new Employee[n];

        for (int i = 0; i < n; i++) {
            switch (sc.nextInt()) {
                case 0:
                    SalaridEmployee salaridEmployee = new SalaridEmployee(sc.next(), sc.next(), sc.next());
                    salaridEmployee.setWeeklySalary(sc.nextDouble());
                    employees[i] = salaridEmployee;
                    break;
                case 1:
                    HourlyEmployee hourlyEmployee = new HourlyEmployee(sc.next(), sc.next(), sc.next());
                    hourlyEmployee.setWage(sc.nextDouble());
                    hourlyEmployee.setHours(sc.nextDouble());
                    employees[i] = hourlyEmployee;
                    break;
                case 2:
                    CommisionEmployee commisionEmployee = new CommisionEmployee(sc.next(), sc.next(), sc.next());
                    commisionEmployee.setGrossSales(sc.nextDouble());
                    commisionEmployee.setCommissionRate(sc.nextDouble());
                    employees[i] = commisionEmployee;
                    break;
                case 3:
                    basePlusCommisionEmployee basePlusCommisionemployee = new basePlusCommisionEmployee(sc.next(), sc.next(), sc.next());
                    basePlusCommisionemployee.setGrossSales(sc.nextDouble());
                    basePlusCommisionemployee.setCommissionRate(sc.nextDouble());
                    basePlusCommisionemployee.setBaseSalary(sc.nextDouble());
                    employees[i] = basePlusCommisionemployee;
                    break;
                default:
                    break;
            }
        }
        Arrays.sort(employees);
        int m = sc.nextInt();
        for (int i = 0; i < m; i++) {
            int select=sc.nextInt();
            if(select==0){
                //根据 first Name 来查询：
                String name = sc.next();
                for (Employee employee : employees) {
                    if(employee.getFirstName().equals(name)){
                        System.out.println(employee);
                    }
                }
            } else if (select==1){
                //根据身份证号码查询：
                String numbers = sc.next();
                for (Employee employee : employees) {
                    if(employee.getSocialSecurityNumber().equals(numbers)){
                        System.out.println(employee);
                    }
                }
            }
        }
    }
}
