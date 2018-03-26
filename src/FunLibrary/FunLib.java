package FunLibrary;


import java.util.ArrayList;

/**
 * Functions Library for Algorithm
 */
public class FunLib {

    //f1 function related data
    public static int f1_codeNum = 1; //Function code number
    public static int f1_Dims = 2; //Function dimension
    public static double f1_species_rs = 3.5; //Function species radius
    public static double f1_maxRange = 6; //Function max Range
    public static double f1_minRange = -6; //Function min Range
    public static double[] f1_gpoint_1 = {3.58,-1.86}; //
    public static double[] f1_gpoint_2 = {3.0,2.0};
    public static double[] f1_gpoint_3 = {-2.815,3.125};
    public static double[] f1_gpoint_4 = {-3.78,-3.28};
    public static double[][] f1_optimalpoints = {f1_gpoint_1,f1_gpoint_2,f1_gpoint_3,f1_gpoint_4};


    //f2 function related data
    public static int f2_codeNum = 2; //Function code number
    public static int f2_Dims = 2; //Function dimension
    public static double f2_species_rs = 2; //Function species radius
    public static double f2_maxRange = 5; //Function max Range
    public static double f2_minRange = -5; //Function min Range
    public static double[] f2_gpoint_1 = {0,0}; //0
    public static double[][] f2_optimalpoints = {f2_gpoint_1};

    //f3 function related data
    public static int f3_codeNum = 3; //Function code number
    public static int f3_Dims = 1; //Function dimension
    public static double f3_species_rs = 0.15; //Function species radius
    public static double f3_maxRange = 1; //Function max Range
    public static double f3_minRange = 0; //Function min Range
    public static double[] f3_gpoint_1 = {0.1}; //1
    public static double[] f3_gpoint_2 = {0.3}; //1
    public static double[] f3_gpoint_3 = {0.5}; //1
    public static double[] f3_gpoint_4 = {0.7}; //1
    public static double[] f3_gpoint_5 = {0.9}; //1
    public static double[][] f3_optimalpoints = {f3_gpoint_1,f3_gpoint_2,f3_gpoint_3,f3_gpoint_4,f3_gpoint_5};

    //f4 function related data
    public static int f4_codeNum = 4; //Function code number
    public static int f4_Dims = 2; //Function dimension
    public static double f4_species_rs = 3.5; //Function species radius
    public static double f4_maxRange = 5; //Function max Range
    public static double f4_minRange = -5; //Function min Range
    public static double[] f4_gpoint_1 = {0,0};  //2
    public static double[] f4_gpoint_2 = {0,-4}; //2
    public static double[] f4_lpoint_3 = {-4,4}; //1
    public static double[] f4_lpoint_4 = {4,4}; //1
    public static double[][] f4_optimalpoints = {f4_gpoint_1,f4_gpoint_2,f4_lpoint_3,f4_lpoint_4};

    //f5 function related data
    public static int f5_codeNum = 5; //Function code number
    public static int f5_Dims = 1; //Function dimension
    public static double f5_species_rs = 0.15; //Function species radius
    public static double f5_maxRange = 1; //Function max Range
    public static double f5_minRange = 0; //Function min Range
    public static double[] f5_gpoint_1 = {0.1}; //1.0
    public static double[] f5_lpoint_2 = {0.3}; //0.9170040432046712
    public static double[] f5_lpoint_3 = {0.5}; //0.7071067811865476
    public static double[] f5_lpoint_4 = {0.7}; //0.4585020216023357
    public static double[] f5_lpoint_5 = {0.9}; //0.25
    public static double[][] f5_optimalpoints = {f5_gpoint_1,f5_lpoint_2,f5_lpoint_3,f5_lpoint_4,f5_lpoint_5};


    /**
     *  Function library function
     * @param x Function coordinates
     * @param n Function code number
     * @return Function fitness
     */
    public static double funLib(double[] x,int n){
        double fitness = 0 ;
        switch (n){
            case 1:
                fitness = 200-Math.pow((Math.pow(x[0], 2)+x[1]-11),2)-Math.pow(x[0]+(Math.pow(x[1], 2)-7),2);
                break;

            case 2:
                for(int i=0;i<f2_Dims;i++){
                    fitness = fitness + Math.pow(x[i],2);
                }
                fitness = -fitness;
                break;

            case 3:
                fitness = Math.pow(Math.sin(5*Math.PI*x[0]),6);
                break;

            case 4:
                fitness=Math.pow(Math.E,-Math.pow(x[0]-4,2)-Math.pow(x[1]-4, 2))
                    +Math.pow(Math.E,-Math.pow(x[0]+4,2)-Math.pow(x[1]-4, 2))
                    +2*(Math.pow(Math.E,-Math.pow(x[0],2)-Math.pow(x[1], 2))+Math.pow(Math.E,-Math.pow(x[0],2)-Math.pow(x[1]+4, 2)));
                break;

            case 5:
                double a=Math.exp(((-2)*Math.log(2))*(Math.pow((x[0]-0.1)/0.8, 2)));
                double temp = Math.sin(5*Math.PI*x[0]);
                fitness = a*Math.pow(temp, 6);
                break;
        }
        return fitness;
    }
}
