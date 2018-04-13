package shudu;


public class test{
    public static void main(String[] args){

        double[] x1 = {2,1};
        double[] x2 = {3,3};
        System.out.println(distance(x1, x2));
    }

    public static double distance(double[] x1, double[] x2){
        double distance = 0;
        for(int i=0;i<2;i++){
            distance = distance + Math.pow(x1[i]-x2[i],2);
        }
        return Math.sqrt(distance);
    }

    public static void fun(double[] x) {
        double fitness = 0;
        fitness = Math.pow(Math.sin(5*Math.PI*(Math.pow(x[0],0.75)-0.05)),6);
        System.out.println(fitness);
    }
}


