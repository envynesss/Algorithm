package PSO;

public class Constant {
    public static final int numofP = 100;
    public static final double w = 0.729844;
    public static final double ran1 = 0.9280770459248898;
    public static final double ran2 = 0.2554051873354638;
    public static final double c1 = 1.4962;
    public static final double c2 = c1;
    public static final double v = 0.5;

    public static final double vMax = 2;
    public static final double vMin = -vMax;

    public static int iterations = 30000;
    public static int runTimes = 1; //独立运行次数

    //输出文件路径
    public static String folder = "z-output/";
    public static String filePath = folder + "0_pso_data.txt";

    //函数相关参数
    public static int codeNum;
    public static int funDims;
    public static double speciesRs;
    public static double[] maxRange;
    public static double[] minRange;
    public static double[][] lPoints;
    public static double[][] gPoints;
    public static double DisThreshold; // 与最优点的距离临界值
    public static double FitThreshold; // 与最优点的相对适应值临界值

    public static void setFuncPara(int code_Num, int fun_Dims, double species_Rs, double[] max_Range, double[] min_Range,
                                   double[][] l_Points, double[][] g_Points, double Dis_Threshold, double Fit_Threshold) {
        Constant.codeNum = code_Num;
        Constant.funDims = fun_Dims;
        Constant.speciesRs = species_Rs;
        Constant.maxRange = max_Range;
        Constant.minRange = min_Range;
        Constant.lPoints = l_Points;
        Constant.gPoints = g_Points;
        Constant.DisThreshold = Dis_Threshold;
        Constant.FitThreshold = Fit_Threshold;
    }
}
