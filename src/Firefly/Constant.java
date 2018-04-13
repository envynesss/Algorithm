package Firefly;

import FunLibrary.FunLib;

/**
 * The Constants for FireFly Algorithm
 */
class Constant{

    public static final int NumofP = 50;
    //γ为light_absorption光吸收率属于[0.1,10],its setting depends on the problem to be optimized.
    public static final double gamma=0.9;
    //β0
    public static final double beta0=0.1;
    //α为Step factor步长因子属于[0,1]
    public static final double alpha=0.125;

    public static int iterations = 3000;
    public static double Acc_Thr = 0.001; // Accuracy_Threshold

    public static int CBLS_step = 5; //局域搜索的探索次数
    public static double CBLS_move = 0.00000001; //局域搜索的移动距离

    public static int RWDE_step = 5; //局域搜索的探索次数
    public static double RWDE_move = 0.00000001; //局域搜索的移动距离


    //Function 参数相关配置
    public static int codeNum = FunLib.f10_codeNum;
    public static int funDims = FunLib.f10_Dims;
    public static double species_rs = FunLib.f10_species_rs;
    public static double maxRange = FunLib.f10_maxRange;
    public static double minRange = FunLib.f10_minRange;
    public static double[][] optimalpoints = FunLib.f10_optimalpoints;

}