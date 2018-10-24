package shudu;

public class test{
    public static int i = 1;
    public static void main(String[] args) {
        double[] x = {9.424777534495764, 2.474997437489435}; // 0.39788735773607264
                                                               //0.39788735774819806
        System.out.println(fitness(x));
    }

    public static double fitness(double[] x) {
        double fitness = 0;
        fitness = Math.pow(x[1]-(5.1*Math.pow(x[0],2)/(4*Math.PI*Math.PI))+(5*x[0]/(Math.PI))-6,2)+10*(1-(1/(8*Math.PI)))*Math.cos(x[0])+10;
        return fitness;
    }
}