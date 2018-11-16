package Ncc;

import java.util.Random;

class NC{
    //4个最优点的坐标： (153, 178, 0.88484)，(278, 282, 0.9251)，(300, 385, 0.88532)，(340, 156, 0.88512)。
    public static int NumofP = 100;
    //γ为light_absorption光吸收率属于[0.1,10],its setting depends on the problem to be optimized.
    public static final double gamma = 0.1;
    //β0
    public static final double beta0 = 1;
    //α为Step factor步长因子属于[0,1]
    public static double alpha = 120; // 函数中配置

    public static int iterations = 2000; // 迭代次数
    public static int runTimes = 30; // 独立运行次数
    public static int LS_move = 1;
    public static int CBLS_step = 5;
    public static int in = 2;

    //函数相关参数
    public static int funDims = 2;
    public static double species_rs = 80;
    public static int[] maxRange = {400, 450}; // {543, 452};
    public static int[] minRange = {100, 100};
    public static int[][] gPoints = {{153,178}, {278,282}, {300,385}, {340,156}};
    public static int validSeedSize = 6;
    public static double Acc_Thr = 1.5;

    //输出文件路径
    public static String folder = "z-output/";
    public static String filePath = folder + "0_ncc_data.txt";

    /**
     * 获得标准正太分布的随机因子
     */
    public static void setNPRS(int NP, double RS){
        NumofP = NP;
        species_rs = RS;
    }

    /**
     * 获得标准正太分布的随机因子
     */
    public static double getGaussian(){
        Random r = new Random();
        return r.nextGaussian();
    }

    /**
     * 取数组的平均值
     */
    public static double average(double[] array) {
        double sum = 0;
        int length = array.length;
        for (int i = 0; i < length; i++) {
            sum = sum + array[i];
        }
        return sum / length;
    }
}
