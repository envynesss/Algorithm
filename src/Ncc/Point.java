package Ncc;

import java.util.Random;

class Point implements Comparable{
    int[] x;
    double fitness;
    double lightIntensity;
    double attractiveness;
    Point seed;
    Point pBest;

    public Point(){
        x = new int[NC.funDims];
        x[0] = new Random().nextInt(NC.maxRange[0] - NC.minRange[0]) + NC.minRange[0];
        x[1] = new Random().nextInt(NC.maxRange[1] - NC.minRange[1]) + NC.minRange[1];
        fitnessfun();
    }

    public Point(int[] x){
        this.x = x;
        fitnessfun();
    }

    /**
     * 计算粒子适应值函数.
     */
    public double fitnessfun(){
        fitness = NccValue.nccMatrix[x[0]][x[1]];
        return fitness;
    }

    /**
     * 相对亮度函数,粒子的亮度取决于粒子本身函数值大小和距离.两个粒子的相对亮度可以不同.
     */
    public double getLightIntensity() {
        lightIntensity = fitnessfun();
        return lightIntensity;
    }

    /**
     * 吸引度函数,取决于两个粒子间的距离,两个粒子的相互吸引度是相等的.
     */
    public double getAttractiveness(double r){
        //attractiveness = NC.beta0 * Math.pow(Math.E, -NC.gamma * Math.pow(r, 2));
        attractiveness = NC.beta0 * Math.random() * 0.5 + 0.5;
        return attractiveness;
    }

    @Override
    public String toString() {
        String str = "(" + x[0] + ", "+ x[1] + ", " + fitness + ")";
        return str;
    }

    @Override
    public int compareTo(Object obj){
        Point point = (Point)obj;
        return new Double(point.fitnessfun()).compareTo(this.fitnessfun());
    }
}