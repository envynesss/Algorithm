package shudu;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class test{
    public static void main(String[] args){
        double[] x0 = {0.25};// 200.0
        /*double[] x1 = {-2.805118,3.131312};// 199.999999999989
        double[] x2 = {-3.779310,-3.283186};// 199.99999999999622
        double[] x3 = {3.584428,-1.848126};// 199.9999999999911
*/

        fun(x0);
    }

    public static void fun(double[] x) {
        double fitness = 0;
        fitness = Math.pow(Math.sin(5*Math.PI*(Math.pow(x[0],0.75)-0.05)),6);
        System.out.println(fitness);
    }

    public static void fun2(double[] x) {

    }

}

class point {
    double[] x;
    double fitness;
}

