package OJ_work2_9;

public class ComplexNum {
    //复数类：
    //应该包含 实部 和 虚部：
    double real;
    double vir;

    public ComplexNum() {
    }
    public ComplexNum(double real, double vir) {
        this.real = real;
        this.vir = vir;
    }

    @Override
    public String toString() {
        if(this.vir>=0){
            return String.format("%.1f",this.real)+"+"+String.format("%.1f",this.vir)+"i";
        }else{
            return String.format("%.1f",this.real)+String.format("%.1f",this.vir)+"i";
        }
    }

    static ComplexNum add(ComplexNum a, ComplexNum b) {
        return new ComplexNum(a.real + b.real, a.vir + b.vir);
    }
    static ComplexNum sub(ComplexNum a, ComplexNum b) {
        return new ComplexNum(a.real - b.real, a.vir - b.vir);
    }
    static ComplexNum mul(ComplexNum a, ComplexNum b) {
        return new ComplexNum(a.real * b.real - a.vir * b.vir, a.vir * b.real + a.real * b.vir);
    }
    static ComplexNum div(ComplexNum a, ComplexNum b) throws ComplexDivException {
        if(b.real==0&&b.vir==0){
            throw new ComplexDivException();
        }else{
            double real=(a.real*b.real+a.vir*b.vir)/(b.real*b.real+b.vir*b.vir);
            double vir=(a.vir*b.real-a.real* b.vir)/(b.real*b.real+b.vir*b.vir);
            return new ComplexNum(real,vir);
        }
    }
}
