package Firefly;

import FunLibrary.FunLib;

import java.util.Arrays;

public class firefly implements Comparable{
    static int Dims = Constant.funDims;

    int number;
    boolean isProcessed = false;

    double x[];
    double fitness;
    double light_intensity;
    double attractiveness;
    firefly seed;
    firefly pbest;

    public firefly(){
        Dims = Constant.funDims;
        this.x = new double[Dims];
        if (Constant.codeNum == 1 || Constant.codeNum == 2 || Constant.codeNum == 7 || Constant.codeNum == 8) {
            for(int i = 0; i < Dims; i++){
                this.x[i] = Math.random(); //f1 and f2 正弦函数
            }
        }
        else if (Constant.codeNum == 4) {
            this.x[0] = Math.random() * 15 + (-5);
            this.x[1] = Math.random() * 15;
        }
        else {
            for(int i = 0; i < Dims; i++){
                this.x[i] = Math.random() * Constant.maxRange[i] * 2 - Constant.maxRange[i];
            }
        }
        fitnessfun();
    }


    public firefly(double[] x){
        Dims = Constant.funDims;
        this.x = x;
        fitnessfun();
    }

    /**
     * 计算粒子适应值函数
     * @return 粒子适应值
     */
    public double fitnessfun(){
        fitness = FunLib.funLib(x, Constant.codeNum);
        return fitness;
    }

    /**
     * 相对亮度函数,粒子的亮度取决于粒子本身函数值大小和距离.两个粒子的相对亮度可以不同
     */
    public double light_intensityfun(double r){
        light_intensity = fitness * Math.pow(Math.E, -Constant.gamma * Math.pow(r, 2));
        return light_intensity;
    }

    /**
     * 吸引度函数,取决于两个粒子间的距离,两个粒子的相互吸引度是相等的
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