package FunLibrary;

/**
 * Functions Library for Algorithm
 */
public class FunLib {

    //f1 function related data
    public static int f1_codeNum = 1; //Function code number
    public static int f1_Dims = 1; //Function dimension
    public static double f1_species_rs = 0.15; //Function species radius
    public static double f1_maxRange = 1; //Function max Range
    public static double f1_minRange = 0; //Function min Range
    public static double[] f1_gpoint_1 = {0.1}; // 1
    public static double[] f1_gpoint_2 = {0.3}; // 1
    public static double[] f1_gpoint_3 = {0.5}; // 1
    public static double[] f1_gpoint_4 = {0.7}; // 1
    public static double[] f1_gpoint_5 = {0.9}; // 1
    public static double[][] f1_optimalpoints = {f1_gpoint_1,f1_gpoint_2,f1_gpoint_3,f1_gpoint_4,f1_gpoint_5};

    //f2 function related data
    public static int f2_codeNum = 2; //Function code number
    public static int f2_Dims = 1; //Function dimension
    public static double f2_species_rs = 0.15; //Function species radius
    public static double f2_maxRange = 1; //Function max Range
    public static double f2_minRange = 0; //Function min Range
    public static double[] f2_gpoint_1 = {0.07969966213537202}; // :0.9999999998929217
    public static double[] f2_gpoint_2 = {0.24665546669843014}; // 0.9999999999998974
    public static double[] f2_gpoint_3 = {0.45062670094296264}; // 0.9999999999999973
    public static double[] f2_gpoint_4 = {0.6814202178683114}; // 0.99999999999999
    public static double[] f2_gpoint_5 = {0.9338951689306259}; // 0.9999999999997322
    public static double[][] f2_optimalpoints = {f2_gpoint_1,f2_gpoint_2,f2_gpoint_3,f2_gpoint_4,f2_gpoint_5};

    //f3 function related data
    public static int f3_codeNum = 3; //Function code number
    public static int f3_Dims = 2; //Function dimension
    public static double f3_species_rs = 3.0; //Function species radius
    public static double f3_maxRange = 6; //Function max Range
    public static double f3_minRange = -6; //Function min Range
    public static double[] f3_gpoint_1 = {3.0,2.0}; // 200.0
    public static double[] f3_gpoint_2 = {-2.805118,3.131312}; // 199.999999999989
    public static double[] f3_gpoint_3 = {-3.779310,-3.283186}; // 199.99999999999622
    public static double[] f3_gpoint_4 = {3.584428,-1.848126}; // 199.9999999999911
    public static double[][] f3_optimalpoints = {f3_gpoint_1,f3_gpoint_2,f3_gpoint_3,f3_gpoint_4};

    //f4 function related data

    //f5 function related data

    //f6 function related data

    //f7 function related data
    public static int f7_codeNum = 7; //Function code number
    public static int f7_Dims = 1; //Function dimension
    public static double f7_species_rs = 0.15; //Function species radius
    public static double f7_maxRange = 1; //Function max Range
    public static double f7_minRange = 0; //Function min Range
    public static double[] f7_gpoint_1 = {0.1}; //1.0
    public static double[] f7_lpoint_2 = {0.3}; //0.9170040432046712
    public static double[] f7_lpoint_3 = {0.5}; //0.7071067811865476
    public static double[] f7_lpoint_4 = {0.7}; //0.4585020216023357
    public static double[] f7_lpoint_5 = {0.9}; //0.25
    public static double[][] f7_optimalpoints = {f7_gpoint_1,f7_lpoint_2,f7_lpoint_3,f7_lpoint_4,f7_lpoint_5};

    //f8 function related data
    public static int f8_codeNum = 8; //Function code number
    public static int f8_Dims = 1; //Function dimension
    public static double f8_species_rs = 0.15; //Function species radius
    public static double f8_maxRange = 1; //Function max Range
    public static double f8_minRange = 0; //Function min Range
    public static double[] f8_gpoint_1 = {0.07969966213537202}; // :0.9999999998929217
    public static double[] f8_gpoint_2 = {0.24665546669843014}; // 0.9999999999998974
    public static double[] f8_gpoint_3 = {0.45062670094296264}; // 0.9999999999999973
    public static double[] f8_gpoint_4 = {0.6814202178683114}; // 0.99999999999999
    public static double[] f8_gpoint_5 = {0.9338951689306259}; // 0.9999999999997322
    public static double[][] f8_optimalpoints = {f8_gpoint_1,f8_gpoint_2,f8_gpoint_3,f8_gpoint_4,f8_gpoint_5};

    //f9 function related data
    public static int f9_codeNum = 9; //Function code number
    public static int f9_Dims = 2; //Function dimension
    public static double f9_species_rs = 3.5; //Function species radius
    public static double f9_maxRange = 5; //Function max Range
    public static double f9_minRange = -5; //Function min Range
    public static double[] f9_gpoint_1 = {0,0};  // 2
    public static double[] f9_gpoint_2 = {0,-4}; // 2
    public static double[] f9_lpoint_3 = {-4,4}; // 1
    public static double[] f9_lpoint_4 = {4,4}; // 1
    public static double[][] f9_optimalpoints = {f9_gpoint_1,f9_gpoint_2,f9_lpoint_3,f9_lpoint_4};

    //f10 function related data
    public static int f10_codeNum = 10; //Function code number
    public static int f10_Dims = 2; //Function dimension
    public static double f10_species_rs = 2; //Function species radius
    public static double f10_maxRange = 5; //Function max Range
    public static double f10_minRange = -5; //Function min Range
    public static double[] f10_gpoint_1 = {0,0}; // 0
    public static double[][] f10_optimalpoints = {f10_gpoint_1};


    /**
     *  Function library function
     * @param x Function coordinates
     * @param codeNum Function code number
     * @return Function fitness
     */
    public static double funLib(double[] x,int codeNum){
        double fitness = 0 ;

        switch (codeNum){
            case 1:
                fitness = Math.pow(Math.sin(5*Math.PI*x[0]),6);
                break;

            case 2:
                fitness = Math.pow(Math.sin(5*Math.PI*(Math.pow(x[0],0.75)-0.05)),6);
                break;

            case 3:
                fitness = 200-Math.pow((Math.pow(x[0], 2)+x[1]-11),2)-Math.pow(x[0]+(Math.pow(x[1], 2)-7),2);
                break;

            case 4:
                break;

            case 5:
                break;

            case 6:
                break;

            case 7:
                double a=Math.exp(((-2)*Math.log(2))*(Math.pow((x[0]-0.1)/0.8, 2)));
                double temp = Math.sin(5*Math.PI*x[0]);
                fitness = a*Math.pow(temp, 6);
                break;

            case 8:
                double a8=Math.exp(((-2)*Math.log(2))*(Math.pow((x[0]-0.1)/0.8, 2)));
                double temp8 = Math.sin(5*Math.PI*(Math.pow(x[0],0.75)-0.05));
                fitness = a8*Math.pow(temp8, 6);
                break;

            case 9:
                fitness=Math.pow(Math.E,-Math.pow(x[0]-4,2)-Math.pow(x[1]-4, 2))
                    +Math.pow(Math.E,-Math.pow(x[0]+4,2)-Math.pow(x[1]-4, 2))
                    +2*(Math.pow(Math.E,-Math.pow(x[0],2)-Math.pow(x[1], 2))+Math.pow(Math.E,-Math.pow(x[0],2)-Math.pow(x[1]+4, 2)));
                break;

            case 10:
                for(int i=0;i<f10_Dims;i++){
                    fitness = fitness + Math.pow(x[i],2);
                }
                fitness = -fitness;
                break;

            default:
                break;
        }
        return fitness;
    }
}
