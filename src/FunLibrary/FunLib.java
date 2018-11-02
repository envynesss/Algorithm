package FunLibrary;

import Firefly.Constant;

import java.util.Arrays;

/**
 * Functions Library for Algorithm
 */
public class FunLib {

    //f1 function related data
    public static class f1{
        public static int codeNum = 1; //Function code number
        public static int Dims = 1; //Function dimension
        public static double species_rs = 0.05; //Function species radius
        public static double[] maxRange = {1}; //Function max Range
        public static double[] minRange = {0}; //Function min Range
        public static double[] f1_gpoint_1 = {0.1}; // 1
        public static double[] f1_gpoint_2 = {0.3}; // 1
        public static double[] f1_gpoint_3 = {0.5}; // 1
        public static double[] f1_gpoint_4 = {0.7}; // 1
        public static double[] f1_gpoint_5 = {0.9}; // 1
        public static double[][] optimalpoints = {f1_gpoint_1,f1_gpoint_2,f1_gpoint_3,f1_gpoint_4,f1_gpoint_5};
        public static double[][] gpoints = optimalpoints;
        public static double alpha = 0.0001;
        public static double Acc_Thr = 0.0000001; // Accuracy_Threshold
    }


    //f2 function related data
    public static class f2{
        public static int codeNum = 2; //Function code number
        public static int Dims = 1; //Function dimension
        public static double species_rs = 0.05; //Function species radius
        public static double[] maxRange = {1}; //Function max Range
        public static double[] minRange = {0}; //Function min Range
        public static double[] f2_gpoint_1 = {0.07969939305111944}; // 1.0
        public static double[] f2_gpoint_2 = {0.24665545628047417}; // 0.9999999999999993
        public static double[] f2_gpoint_3 = {0.4506266992974431}; // 1.0
        public static double[] f2_gpoint_4 = {0.6814202229902825}; // 1.0
        public static double[] f2_gpoint_5 = {0.9338951933519088}; // 1.0
        public static double[][] optimalpoints = {f2_gpoint_1,f2_gpoint_2,f2_gpoint_3,f2_gpoint_4,f2_gpoint_5};
        public static double[][] gpoints = optimalpoints;
        public static double alpha = 0.0001;
        public static double Acc_Thr = 0.0000001; // Accuracy_Threshold
    }


    //f3 function related data
    public static class f3{
        public static int codeNum = 3; //Function code number
        public static int Dims = 2; //Function dimension
        public static double species_rs = 2.0; //Function species radius
        public static double[] maxRange = {6,6}; //Function max Range
        public static double[] minRange = {-6,-6}; //Function min Range
        public static double[] f3_gpoint_1 = {3.0,2.0}; // 200.0
        public static double[] f3_gpoint_2 = {-2.8051183461479066, 3.131312668188017}; // 199.99999999999696
        public static double[] f3_gpoint_3 = {-3.779310,-3.283186}; // 199.99999999999622
        public static double[] f3_gpoint_4 = {3.5844282964246212, -1.8481264669902808}; // 199.9999999999999
        public static double[][] optimalpoints = {f3_gpoint_1,f3_gpoint_2,f3_gpoint_3,f3_gpoint_4};
        public static double[][] gpoints = optimalpoints;
        public static double alpha=0.001;
        public static double Acc_Thr = 0.00005; // Accuracy_Threshold
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
        public static double[][] optimalpoints = {f4_gpoint_1,f4_gpoint_2,f4_gpoint_3};
        public static double[][] gpoints = optimalpoints;
        public static double alpha=0.001;
        public static double Acc_Thr = 0.00005; // Accuracy_Threshold
    }

    //f5 function related data
    public static class f5{
        public static int codeNum = 5; //Function code number
        public static int Dims = 2; //Function dimension
        public static double species_rs = 1; //Function species radius
        public static double[] maxRange = {10,10}; //Function max Range
        public static double[] minRange = {-10,-10}; //Function min Range
        public static double[] f5_gpoint_1 = {-7.0835, 4.8580}; // -186.73090120018114
        public static double[] f5_gpoint_2 = {-7.0835, -7.7083}; // -186.730908300575
        public static double[] f5_gpoint_3 = {-1.4251, -7.0835}; // -186.73090685668902
        public static double[] f5_gpoint_4 = {5.4828, 4.858}; // -186.73089218082114
        public static double[] f5_gpoint_5 = {-1.4251, -0.8003}; // -186.7309059635426
        public static double[] f5_gpoint_6 = {4.858, 5.4828}; // -186.73089218082114
        public static double[] f5_gpoint_7 = {-7.7083, -7.0835}; // -186.730908300575
        public static double[] f5_gpoint_8 = {-7.0835, -1.4251}; // -186.73090685668902
        public static double[] f5_gpoint_9 = {-7.7083, -0.8003}; // -186.73090740742856
        public static double[] f5_gpoint_10 = {-7.7083, 5.4828}; // -186.73089928121468
        public static double[] f5_gpoint_11 = {-0.8003, -7.7083}; // -186.73090740742856
        public static double[] f5_gpoint_12 = {-0.8003, -1.4251}; // -186.7309059635426
        public static double[] f5_gpoint_13 = {-0.8003, 4.858}; // -186.73090030703474
        public static double[] f5_gpoint_14 = {-1.4251, 5.4828}; // -186.73089783732874
        public static double[] f5_gpoint_15 = {5.4828, -7.7083}; // -186.73089928121468
        public static double[] f5_gpoint_16 = {4.858, -7.0835}; // -186.73090120018114
        public static double[] f5_gpoint_17 = {5.4828, -1.4251}; // -186.73089783732874
        public static double[] f5_gpoint_18 = {4.858, -0.8003}; // -186.73090030703474
        public static double[][] optimalpoints = {f5_gpoint_1,f5_gpoint_2,f5_gpoint_3,f5_gpoint_4,f5_gpoint_5,f5_gpoint_6,f5_gpoint_7,f5_gpoint_8,f5_gpoint_9,
                f5_gpoint_10,f5_gpoint_11,f5_gpoint_12,f5_gpoint_13,f5_gpoint_14,f5_gpoint_15,f5_gpoint_16,f5_gpoint_17,f5_gpoint_18};
        public static double[][] gpoints = optimalpoints;
        public static double alpha=0.001; // 未配置好
        public static double Acc_Thr = 0.001; // Accuracy_Threshold
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
        public static double[][] optimalpoints = {f_gpoint_1,f_gpoint_2,f_lpoint_3,f_lpoint_4};
        public static double[][] gpoints = {f_gpoint_1,f_gpoint_2};
        public static double alpha=0.0001;
        public static double Acc_Thr = 0.00001; // Accuracy_Threshold
    }
    //f7 function related data
    public static class f7{
        public static int codeNum = 7; //Function code number
        public static int Dims = 1; //Function dimension
        public static double species_rs = 0.05; //Function species radius
        public static double[] maxRange = {1}; //Function max Range
        public static double[] minRange = {0}; //Function min Range
        public static double[] f7_gpoint_1 = {0.1}; //1.0
        public static double[] f7_lpoint_2 = {0.29941646977674397}; // 0.9172358899604152
        public static double[] f7_lpoint_3 = {0.49883303750620595}; // 0.7078221356124454
        public static double[] f7_lpoint_4 = {0.6982497999696433}; // 0.4595462709964165
        public static double[] f7_lpoint_5 = {0.8976668561314929}; // 0.25101303015873766
        public static double[][] optimalpoints = {f7_gpoint_1,f7_lpoint_2,f7_lpoint_3,f7_lpoint_4,f7_lpoint_5};
        public static double[][] gpoints = {f7_gpoint_1};
        public static double alpha=0.0001; // 未配置好
        public static double Acc_Thr = 0.00125; // Accuracy_Threshold lpoint 0.0015:2, 0.002:3, 0.0025:4
    }


    //f8 function related data
    public static class f8{
        public static int codeNum = 8; //Function code number
        public static int Dims = 1; //Function dimension
        public static double species_rs = 0.05; //Function species radius
        public static double[] maxRange = {1}; //Function max Range
        public static double[] minRange = {0}; //Function min Range
        public static double[] f8_gpoint_1 = {0.07972916815706897}; // 0.9991090310270107
        public static double[] f8_lpoint_2 = {0.24665546669843014}; // 0.9545953361349704
        public static double[] f8_lpoint_3 = {0.45062670094296264}; // 0.7669217995890066
        public static double[] f8_lpoint_4 = {0.6789383041085573}; // 0.4823351006413628
        public static double[] f8_lpoint_5 = {0.9297342454298296}; // 0.22341336187772662
        public static double[][] optimalpoints = {f8_gpoint_1,f8_lpoint_2,f8_lpoint_3,f8_lpoint_4,f8_lpoint_5};
        public static double[][] gpoints = {f8_gpoint_1};
        public static double alpha=0.0001; // 未配置好
        public static double Acc_Thr = 0.00125; // Accuracy_Threshold
    }


    //f9 function related data
    public static class f9{
        public static int codeNum = 9; //Function code number
        public static int Dims = 2; //Function dimension
        public static double species_rs = 2.5; //Function species radius
        public static double[] maxRange = {5,5}; //Function max Range
        public static double[] minRange = {-5,-5}; //Function min Range
        public static double[] f_gpoint_1 = {0,0}; // 0
        public static double[][] optimalpoints = {f_gpoint_1};
        public static double[][] gpoints = optimalpoints;
        public static double alpha=0.001;
        public static double Acc_Thr = 0.0001; // Accuracy_Threshold
    }


    //f10 function related data
    public static class f10{
        public static int codeNum = 10; //Function code number
        public static int Dims = 2; //Function dimension
        public static double species_rs = 2.5; //Function species radius
        public static double[] maxRange = {5,5}; //Function max Range
        public static double[] minRange = {-5,-5}; //Function min Range
        public static double[] f10_gpoint_1 = {0,0}; // 0
        public static double[][] optimalpoints = {f10_gpoint_1};
        public static double[][] gpoints = optimalpoints;
        public static double alpha=0.001;
        public static double Acc_Thr = 0.0001; // Accuracy_Threshold
    }

    //f11 function related data
    public static class f11{
        public static int codeNum = 11; //Function code number
        public static int Dims = 2; //Function dimension
        public static double species_rs = 0.5; //Function species radius
        public static double[] maxRange = {1.5,1.5}; //Function max Range
        public static double[] minRange = {-1.5,-1.5}; //Function min Range
        public static double[] f11_gpoint_1 = {0,0}; // 0
        public static double[] f11_2point_2 = {0,0}; // TODO：局部最优
        public static double[][] optimalpoints = {f11_gpoint_1}; //TODO：局部最优
        public static double[][] gpoints = {f11_gpoint_1};
        public static double alpha=0.001; // 未配置好
        public static double Acc_Thr = 0.0001; // Accuracy_Threshold
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
                fitness = 1;
                for (int i = 0; i <= 1; i++) {
                    double temp = 0;
                    for (int j = 1; j <= 5; j++) {
                        temp = temp+j*Math.cos((j+1)*x[i]+j);
                    }
                    fitness = fitness * temp;
                }
                fitness = -fitness;
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
                    fitness = fitness + Math.pow(x[i],2);
                }
                fitness = -fitness;
                break;

            case 10:
                for(int i=0;i<f10.Dims;i++){
                    double temp10 = 0;
                    for (int j = 0; j <= i; j++) {
                        temp10 = temp10 + x[j];
                    }
                    fitness = fitness + Math.pow(temp10,2);
                }
                fitness = -fitness;
                break;

            case 11:
                for(int i=0;i<FunLib.f11.Dims;i++){
                    fitness = fitness + (Math.pow(x[i],2)-10*Math.cos(2*Math.PI*x[i])+10);
                }
                fitness = -fitness;
                break;

            default:
                break;
        }
        return fitness;
    }
}
