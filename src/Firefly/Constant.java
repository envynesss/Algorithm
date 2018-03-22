package Firefly;

import FunLibrary.FunLib;

/**
 * The Constants for FireFly Algorithm
 */
class Constant{

    public static final int NumofP = 50;
    //γ为light_absorption光吸收率属于[0.1,10],its setting depends on the problem to be optimized.
    public static final double gamma=1.0;
    //β0
    public static final double beta0=1.0;
    //α为Step factor步长因子属于[0,1]
    public static final double alpha=0.125;

    public static int iterations = 150;

    public static int codeNum = FunLib.f3_codeNum;
    public static int funDims = FunLib.f3_Dims;
    public static double species_rs = FunLib.f3_species_rs;
    public static double maxRange = FunLib.f3_maxRange;
    public static double minRange = FunLib.f3_minRange;

}

