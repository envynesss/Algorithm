package PSO;

import FunLibrary.FunLib;

import java.util.Arrays;

class Particle implements Comparable{
    static int Dims;
    double[] x;
    double[] v;
    double fitness;
    Particle seed;
    Particle pBest;

    public Particle(){
        Dims = Constant.funDims;
        x = new double[Dims];
        v = new double[Dims];
        if (Constant.codeNum == 1 || Constant.codeNum == 2 || Constant.codeNum == 7 || Constant.codeNum == 8) {
            for(int i = 0; i < Constant.funDims; i++){
                this.x[i] = Math.random(); //f1 and f2 正弦函数
            }
        }
        else if (Constant.codeNum == 4) {
            this.x[0] = Math.random() * 15 + (-5);
            this.x[1] = Math.random() * 15;
        }
        else {
            for(int i = 0; i < Constant.funDims; i++){
                this.x[i] = Math.random() * (Constant.maxRange[i] - Constant.minRange[i])  + Constant.minRange[i];
            }
        }
        for (int i = 0; i < Constant.funDims; i++) {
            v[i] = Constant.v;
        }
        fitnessfun();
    }

    public Particle(double[] x){
        Dims = Constant.funDims;
        this.x = x;
        v = new double[Dims];
        for (int i = 0; i < Constant.funDims; i++) {
            v[i] = Constant.v;
        }
        fitnessfun();
    }

    public double fitnessfun(){
        fitness = FunLib.funLib(x, Constant.codeNum);
        return fitness;
    }

    @Override
    public int compareTo(Object obj){
        Particle p = (Particle) obj;
        return new Double(p.fitnessfun()).compareTo(this.fitnessfun());
    }

    @Override
    public String toString(){
        return Arrays.toString(x) + "=" + this.fitnessfun();
    }
}
