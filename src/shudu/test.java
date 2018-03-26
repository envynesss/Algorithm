package shudu;

import java.util.Scanner;

public class test{
    public static void main(String[] arrgs){
        double[] x = {0.9};
        double fitness = 0;
        double a=Math.exp(((-2)*Math.log(2))*(Math.pow((x[0]-0.1)/0.8, 2)));
        double temp = Math.sin(5*Math.PI*x[0]);
        fitness = a*Math.pow(temp, 6);
        System.out.println(fitness);
    }
    public void test(){
        System.out.println();
    }
}
