package Ncc;

import java.util.Arrays;

class NC{
    //4个最优点的坐标： (90, 151, 0.88484)，(237, 358, 0.88532)，(274, 47, 0.88512)，(356, 195, 0.87952)。
    public static int[] NumofPs = {30,/* 50, 100, 120*/};
    public static int[] rss = {/*50, 85, 100, 120, */150};

    public static int NumofP;
    //γ为light_absorption光吸收率属于[0.1,10],its setting depends on the problem to be optimized.
    public static final double gamma = 1;
    //β0
    public static final double beta0 = 1;
    //α为Step factor步长因子属于[0,1]
    public static double alpha = 1; // 函数中配置

    public static int iterations = 1000; // 迭代次数
    public static int runTimes = 30; // 独立运行次数

    //局域搜索参数
    public static int LS_move = 1;
    public static int CBLS_step = 10;
    //除去pointList里的冗余粒子参数
    public static int in = 2;

    //函数相关参数
    public static int funDims = 2;
    public static double species_rs;
    public static int[] maxRange = {421, 392}; // {422, 393}
    public static int[] minRange = {0, 0};
    public static int[][] gPoints = {{90, 151}, {237, 358}, {274, 47}, {356, 195}};
    public static int validSeedSize = 5;
    public static double Acc_Thr = 1.1;

    //输出文件路径
    public static String folder = "z-output/";
    public static String filePath = folder + "0_ncc_data.txt";

    /**
     * 设置种群总粒子数和种群半径
     */
    public static void setNPRS(int NP, double RS){
        NumofP = NP;
        species_rs = RS;
    }

    /**
     * 取数组的平均值
     */
    public static double average(double[] array, int code) {
        Arrays.sort(array);
        double sum = 0;
        int s = 0;
        int e = array.length;
        if (code == 1) {
            s = s + (int)(e * 0.1);
            //System.out.println(Arrays.toString(array));
            //System.out.println(s + " " + e);
        }
        for (int i = s; i < e; i++) {
            sum = sum + array[i];
        }


        return sum / (e - s);
    }

    /**
     * double类型数据保留n位有效数字
     */
    public static double round(double v, int n) {
        double scale = Math.pow(10, n);
        return Math.round(v * scale) / scale;
    }
}
