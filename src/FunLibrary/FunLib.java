package FunLibrary;

/**
 * Functions Library for Algorithm
 */
public class FunLib {

    //f1 function related data
    public static class f1{
        public static int codeNum = 1; //Function code number
        public static int Dims = 1; //Function dimension
        public static double species_rs = 0.1; //Function species radius
        public static double[] maxRange = {1}; //Function max Range
        public static double[] minRange = {0}; //Function min Range
        public static double[] f1_gpoint_1 = {0.1}; // 1
        public static double[] f1_gpoint_2 = {0.3}; // 1
        public static double[] f1_gpoint_3 = {0.5}; // 1
        public static double[] f1_gpoint_4 = {0.7}; // 1
        public static double[] f1_gpoint_5 = {0.9}; // 1
        public static double[][] gpoints = {f1_gpoint_1,f1_gpoint_2,f1_gpoint_3,f1_gpoint_4,f1_gpoint_5};
        public static double[][] lPoints = {};
        public static double alpha = 1;
        public static double DisThreshold = 1E-7; // 与最优点的距离临界值
        public static double FitThreshold = 1E-6; // 与最优点的相对适应值临界值
    }

    //f2 function related data
    public static class f2{
        public static int codeNum = 2; //Function code number
        public static int Dims = 1; //Function dimension
        public static double species_rs = 0.1; //Function species radius
        public static double[] maxRange = {1}; //Function max Range
        public static double[] minRange = {0}; //Function min Range
        public static double[] f2_gpoint_1 = {0.07969939305111944}; // 1.0
        public static double[] f2_gpoint_2 = {0.24665545628047417}; // 0.9999999999999993
        public static double[] f2_gpoint_3 = {0.4506266992974431}; // 1.0
        public static double[] f2_gpoint_4 = {0.6814202229902825}; // 1.0
        public static double[] f2_gpoint_5 = {0.9338951933519088}; // 1.0
        public static double[][] gpoints = {f2_gpoint_1,f2_gpoint_2,f2_gpoint_3,f2_gpoint_4,f2_gpoint_5};
        public static double[][] lPoints = {};
        public static double alpha = 1;
        public static double DisThreshold = 1E-7; // 与最优点的距离临界值
        public static double FitThreshold = 1E-6; // 与最优点的相对适应值临界值
    }

    //f3 function related data
    public static class f3{
        public static int codeNum = 3; //Function code number
        public static int Dims = 2; //Function dimension
        public static double species_rs = 2.5; //Function species radius
        public static double[] maxRange = {6,6}; //Function max Range
        public static double[] minRange = {-6,-6}; //Function min Range
        public static double[] f3_gpoint_1 = {3.0,2.0}; // 200.0
        public static double[] f3_gpoint_2 = {-2.8051180668054383, 3.1313125194985973}; // 200
        public static double[] f3_gpoint_3 = {-3.7793102424509732, -3.283186002424807}; // 200
        public static double[] f3_gpoint_4 = {3.5844283332483275, -1.8481265260027397}; // 200
        public static double[][] gpoints = {f3_gpoint_1,f3_gpoint_2,f3_gpoint_3,f3_gpoint_4};
        public static double[][] lPoints = {};
        public static double alpha = 10;
        public static double DisThreshold = 0.00005; // 与最优点的距离临界值
        public static double FitThreshold = 1E-6; // 与最优点的相对适应值临界值
    }

    //f4 function related data
    public static class f4{
        public static int codeNum = 4; //Function code number
        public static int Dims = 2; //Function dimension
        public static double species_rs = Math.PI + 1; //Function species radius
        public static double[] maxRange = {10,15}; //Function max Range
        public static double[] minRange = {-5,0}; //Function min Range
        public static double[] f4_gpoint_1 = {-Math.PI, 12.275}; //-0.39788735772973816
        public static double[] f4_gpoint_2 = {Math.PI, 2.275}; //  -0.39788735772973816
        public static double[] f4_gpoint_3 = {3*Math.PI, 2.475}; //-0.39788735772973816
        public static double[][] gpoints = {f4_gpoint_1,f4_gpoint_2,f4_gpoint_3};
        public static double[][] lPoints = {};
        public static double alpha = 10;
        public static double DisThreshold = 0.00005; // 与最优点的距离临界值
        public static double FitThreshold = 1E-6; // 与最优点的相对适应值临界值
    }

    //f5 function related data
    public static class f5{
        public static int codeNum = 5; //Function code number
        public static int Dims = 2; //Function dimension
        public static double species_rs = 5; //Function species radius
        public static double[] maxRange = {10,10}; //Function max Range
        public static double[] minRange = {-10,-10}; //Function min Range
        public static double[] f5_gpoint_1 = {-7.589893, -7.708314}; // 176.54179313659145
        public static double[] f5_gpoint_2 = {-7.589893, -1.425128}; // 176.5417931363413
        public static double[] f5_gpoint_3 = {-7.589893, 4.858057}; // 176.54179313671324
        public static double[] f5_gpoint_4 = {-1.306708, -7.708314}; // 176.54179313652003
        public static double[] f5_gpoint_5 = {-1.306708, -1.425128}; // 176.5417931362699
        public static double[] f5_gpoint_6 = {-1.306708, 4.858057}; // 176.54179313664181
        public static double[] f5_gpoint_7 = {4.976478, -7.708314}; // 176.54179313621344
        public static double[] f5_gpoint_8 = {4.976478, -1.425128}; // 176.5417931364636
        public static double[] f5_gpoint_9 = {4.976478, 4.858057}; // 176.5417931365854
        public static double[][] gpoints = {f5_gpoint_1,f5_gpoint_2,f5_gpoint_3,f5_gpoint_4,f5_gpoint_5,f5_gpoint_6,f5_gpoint_7,f5_gpoint_8,f5_gpoint_9};
        public static double[][] lPoints = {};
        public static double alpha = 15; // 未配置好
        public static double DisThreshold = 0.001; // 与最优点的距离临界值
        public static double FitThreshold = 1E-6; // 与最优点的相对适应值临界值

    }

    //f6 function related data
    public static class f6{
        public static int codeNum = 6; //Function code number
        public static int Dims = 2; //Function dimension
        public static double species_rs = 3.5; //Function species radius
        public static double[] maxRange = {5,5}; //Function max Range
        public static double[] minRange = {-5,-5}; //Function min Range
        public static double[] f_gpoint_1 = {0,0};  // 2
        public static double[] f_gpoint_2 = {0,-4}; // 2
        public static double[] f_lpoint_3 = {-4,4}; // 1
        public static double[] f_lpoint_4 = {4,4}; // 1

        public static double[][] gpoints = {f_gpoint_1,f_gpoint_2};
        public static double[][] lPoints = {f_lpoint_3,f_lpoint_4};
        public static double alpha = 1;
        public static double DisThreshold = 0.00001; // 与最优点的距离临界值
        public static double FitThreshold = 1E-6; // 与最优点的相对适应值临界值
    }
    //f7 function related data
    public static class f7{
        public static int codeNum = 7; //Function code number
        public static int Dims = 1; //Function dimension
        public static double species_rs = 0.1; //Function species radius
        public static double[] maxRange = {1}; //Function max Range
        public static double[] minRange = {0}; //Function min Range
        public static double[] f7_gpoint_1 = {0.1}; //1.0
        public static double[] f7_lpoint_2 = {0.29941646977674397}; // 0.9172358899604152
        public static double[] f7_lpoint_3 = {0.49883303750620595}; // 0.7078221356124454
        public static double[] f7_lpoint_4 = {0.6982497999696433}; // 0.4595462709964165
        public static double[] f7_lpoint_5 = {0.8976668561314929}; // 0.25101303015873766
        public static double[][] gpoints = {f7_gpoint_1};
        public static double[][] lPoints = {f7_lpoint_2,f7_lpoint_3,f7_lpoint_4,f7_lpoint_5};
        public static double alpha = 1; // 未配置好
        public static double DisThreshold = 0.00125; // 与最优点的距离临界值
        public static double FitThreshold = 1E-6; // 与最优点的相对适应值临界值
    }


    //f8 function related data
    public static class f8{
        public static int codeNum = 8; //Function code number
        public static int Dims = 1; //Function dimension
        public static double species_rs = 0.1; //Function species radius
        public static double[] maxRange = {1}; //Function max Range
        public static double[] minRange = {0}; //Function min Range
        public static double[] f8_gpoint_1 = {0.07972916815706897}; // 0.9991090310270107
        public static double[] f8_lpoint_2 = {0.24665546669843014}; // 0.9545953361349704
        public static double[] f8_lpoint_3 = {0.45062670094296264}; // 0.7669217995890066
        public static double[] f8_lpoint_4 = {0.6789383041085573}; // 0.4823351006413628
        public static double[] f8_lpoint_5 = {0.9297342454298296}; // 0.22341336187772662
        public static double[][] gpoints = {f8_gpoint_1,};
        public static double[][] lPoints = {f8_lpoint_2,f8_lpoint_3,f8_lpoint_4,f8_lpoint_5};
        public static double alpha = 1; // 未配置好
        public static double DisThreshold = 0.00125; // 与最优点的距离临界值
        public static double FitThreshold = 1E-6; // 与最优点的相对适应值临界值
    }


    //f9 function related data
    public static class f9{
        public static int codeNum = 9; //Function code number
        public static int Dims = 30; //Function dimension
        public static double species_rs = Math.pow(Dims, 0.5) * 60; //Function species radius
        public static double[] maxRange = getArray(Dims, 100); //Function max Range
        public static double[] minRange = getArray(Dims, -100); //Function min Range
        public static double[] f_gpoint_1 = getArray(Dims, 0); // 0
        public static double[][] gpoints = {f_gpoint_1};
        public static double[][] lPoints = {};
        public static double alpha = 10;
        public static double DisThreshold = 0.01; // 与最优点的距离临界值
        public static double FitThreshold = 1E-3; // 与最优点的相对适应值临界值
    }


    //f10 function related data
    public static class f10{
        public static int codeNum = 10; //Function code number
        public static int Dims = 30; //Function dimension
        public static double species_rs = Math.pow(Dims, 0.5) * 60; //Function species radius
        public static double[] maxRange = getArray(Dims, 100); //Function max Range
        public static double[] minRange = getArray(Dims, -100); //Function min Range
        public static double[] f10_gpoint_1 = getArray(Dims, 0); // 0
        public static double[][] gpoints = {f10_gpoint_1};
        public static double[][] lPoints = {};
        public static double alpha = 10;
        public static double DisThreshold = 0.01; // 与最优点的距离临界值
        public static double FitThreshold = 1E-3; // 与最优点的相对适应值临界值
    }

    //f11 function related data
    public static class f11{
        public static int codeNum = 11; //Function code number
        public static int Dims = 2; //Function dimension
        public static double species_rs = 2.5; //Function species radius
        public static double[] maxRange = {8,8}; //Function max Range
        public static double[] minRange = {-10,-10}; //Function min Range
        public static double[] f11_gpoint_1 = {-7.0835, 4.8580}; // -186.73090120018114
        public static double[] f11_gpoint_2 = {-7.0835, -7.7083}; // -186.730908300575
        public static double[] f11_gpoint_3 = {-1.4251, -7.0835}; // -186.73090685668902
        public static double[] f11_gpoint_4 = {5.4828, 4.858}; // -186.73089218082114
        public static double[] f11_gpoint_5 = {-1.4251, -0.8003}; // -186.7309059635426
        public static double[] f11_gpoint_6 = {4.858, 5.4828}; // -186.73089218082114
        public static double[] f11_gpoint_7 = {-7.7083, -7.0835}; // -186.730908300575
        public static double[] f11_gpoint_8 = {-7.0835, -1.4251}; // -186.73090685668902
        public static double[] f11_gpoint_9 = {-7.7083, -0.8003}; // -186.73090740742856
        public static double[] f11_gpoint_10 = {-7.7083, 5.4828}; // -186.73089928121468
        public static double[] f11_gpoint_11 = {-0.8003, -7.7083}; // -186.73090740742856
        public static double[] f11_gpoint_12 = {-0.8003, -1.4251}; // -186.7309059635426
        public static double[] f11_gpoint_13 = {-0.8003, 4.858}; // -186.73090030703474
        public static double[] f11_gpoint_14 = {-1.4251, 5.4828}; // -186.73089783732874
        public static double[] f11_gpoint_15 = {5.4828, -7.7083}; // -186.73089928121468
        public static double[] f11_gpoint_16 = {4.858, -7.0835}; // -186.73090120018114
        public static double[] f11_gpoint_17 = {5.4828, -1.4251}; // -186.73089783732874
        public static double[] f11_gpoint_18 = {4.858, -0.8003}; // -186.73090030703474
        public static double[][] gpoints = {f11_gpoint_1,f11_gpoint_2,f11_gpoint_3,f11_gpoint_4,f11_gpoint_5,f11_gpoint_6,f11_gpoint_7,f11_gpoint_8,f11_gpoint_9,
                f11_gpoint_10,f11_gpoint_11,f11_gpoint_12,f11_gpoint_13,f11_gpoint_14,f11_gpoint_15,f11_gpoint_16,f11_gpoint_17,f11_gpoint_18};
        public static double[][] lPoints = {};
        public static double alpha = 1; // 未配置好
        public static double DisThreshold = 0.01; // 与最优点的距离临界值
        public static double FitThreshold = 1E-6; // 与最优点的相对适应值临界值
    }

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
                fitness = Math.pow(x[1]-(5.1*Math.pow(x[0],2)/(4*Math.PI*Math.PI))+(5*x[0]/(Math.PI))-6,2)+10*(1-(1/(8*Math.PI)))*Math.cos(x[0])+10;
                fitness = -fitness;
                break;

            case 5:
                double temp51 = 0;
                double temp52 = 0;
                for(int i = 0; i <= 4; i++){
                    temp51 = temp51 + (i + 1) * Math.cos(i * x[0] + i + 1);
                }
                for(int j = 0; j <= 4; j++){
                    temp52 = temp52 + (j + 1) * Math.cos((j + 2) * x[1] + j + 1);
                }
                fitness = -temp51 * temp52;
                break;

            case 6:
                fitness=Math.pow(Math.E,-Math.pow(x[0]-4,2)-Math.pow(x[1]-4, 2))
                        +Math.pow(Math.E,-Math.pow(x[0]+4,2)-Math.pow(x[1]-4, 2))
                        +2*(Math.pow(Math.E,-Math.pow(x[0],2)-Math.pow(x[1], 2))+Math.pow(Math.E,-Math.pow(x[0],2)-Math.pow(x[1]+4, 2)));
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
                for(int i=0;i<f9.Dims;i++){
                    fitness = fitness + Math.pow(x[i], 2);
                }
                fitness = -(fitness + 1);
                break;

            case 10:
                for(int i = 0; i < f10.Dims; i++){
                    double temp10 = 0;
                    for (int j = 0; j <= i; j++) {
                        temp10 = temp10 + x[j];
                    }
                    fitness = fitness + Math.pow(temp10,2);
                }
                fitness = -(fitness + 1);
                break;

            case 11:
                fitness = 1;
                for (int i = 0; i <= 1; i++) {
                    double temp11 = 0;
                    for (int j = 1; j <= 5; j++) {
                        temp11 = temp11 + j * Math.cos((j + 1) * x[i] + j);
                    }
                    fitness = fitness * temp11;
                }
                fitness = -fitness;
                break;

            default:
                break;
        }
        return fitness;
    }

    public static double[] getArray(int d, int value) {
        double[] array = new double[d];
        for (int i = 0; i < d; i++) {
            array[i] = value;
        }
        return array;
    }
}
