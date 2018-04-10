package Firefly;

import FunLibrary.FunLib;

import java.util.Arrays;

class firefly implements Comparable{
    double x[];
    int Dims = Constant.funDims;
    double fitness;
    double light_intensity;
    double attractiveness;
    firefly seed;
    firefly pbest;

    public firefly(){
        this.x = new double[Dims];
        for(int i=0;i<Dims;i++){
            //this.x[i] = Math.random()*Constant.maxRange*2-Constant.maxRange;
            this.x[i] = Math.random(); //f3 and f5 正弦函数
        }
        fitnessfun();
    }

    public firefly(double[] x){
        this.x = x;
        fitnessfun();
    }

    public double fitnessfun(){
        fitness = FunLib.funLib(x,Constant.codeNum);
        return fitness;
    }

    /*
    相对亮度函数,粒子的亮度取决于粒子本身函数值大小和距离.两个粒子的相对亮度可以不同
     */
    public double light_intensityfun(double r){
        light_intensity=fitness*Math.pow(Math.E,-Constant.gamma*Math.pow(r, 2));
        return light_intensity;
    }

    /*
    吸引度函数,取决于两个粒子间的距离,两个粒子的相互吸引度是相等的
     */
    public double attractivenessfun(double r){
        attractiveness=Constant.beta0*Math.pow(Math.E,-Constant.gamma*Math.pow(r, 2));
        return attractiveness;
    }

    @Override
    public String toString() {
        return Arrays.toString(x);
    }

    @Override
    public int compareTo(Object obj){
        firefly ff = (firefly)obj;
        return new Double(ff.fitnessfun()).compareTo(this.fitnessfun());
    }

}