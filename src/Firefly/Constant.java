package Firefly;

import java.io.FileWriter;
import java.io.IOException;
import java.util.*;

/**
 * The Constants for Firefly Algorithm
 */
public class Constant{

    public static final int NumofP = 100;
    //γ为light_absorption光吸收率属于[0.1,10]
    public static final double gamma = 1;
    //β0, 一般取1
    public static final double beta0 = 1;
    //α为Step factor步长因子属于[0,1] alpha在函数中配置

    public static int iterations = 30000; //迭代次数
    public static int runTimes = 1; //独立运行次数
    public static int CBLS_step = 5; //局域搜索的探索次数
    public static double CBLS_move = 0.000001; //局域搜索的移动距离

    //函数相关参数
    public static int codeNum;
    public static int funDims;
    public static double speciesRs;
    public static double[] maxRange;
    public static double[] minRange;
    public static double[][] lPoints;
    public static double[][] gPoints;
    public static double alpha;
    public static double DisThreshold; // 与最优点的距离临界值
    public static double FitThreshold; // 与最优点的相对适应值临界值

    //输出文件路径
    public static String folder = "z-output/";
    public static String filePath = folder + "0_data.txt";

    /**
     * Function 函数相关参数社设置
     */
    public static void setFuncPara(int code_Num, int fun_Dims, double species_Rs, double[] max_Range, double[] min_Range,
                                   double[][] l_Points, double[][] g_Points, double alpha, double Dis_Threshold, double Fit_Threshold) {
        Constant.codeNum = code_Num;
        Constant.funDims = fun_Dims;
        Constant.speciesRs = species_Rs;
        Constant.maxRange = max_Range;
        Constant.minRange = min_Range;
        Constant.lPoints = l_Points;
        Constant.gPoints = g_Points;
        Constant.alpha = alpha;
        Constant.DisThreshold = Dis_Threshold;
        Constant.FitThreshold = Fit_Threshold;
    }

    /**
     * 取到环形邻域结构的seed的邻域
     */
    public static List<Integer> get_neibor_num_list(int num, int k, int rs){
        List<Integer> list = new ArrayList<>();
        if (k - rs < 0) {
            int s1 = k - rs + num;
            int e1 = num;
            //i=s1;i < e1
            for (int i = s1; i < e1; i++) {
                list.add(i);
            }
            int s2 = 0;
            int e2 = k + rs + 1;
            //i=s2;i < e2
            for (int i = s2; i < e2; i++) {
                list.add(i);
            }
        }
        else if (k + rs > num - 1) {
            int s1 = k - rs;
            int e1 = num;
            //i=s1;i < e1
            for (int i = s1; i < e1; i++) {
                list.add(i);
            }
            int s2 = 0;
            int e2 = k + rs - num + 1;
            //i=s2;i < e2
            for (int i = s2; i < e2; i++) {
                list.add(i);
            }
        }
        else {
            int s1 = k - rs;
            int e1 = k + rs + 1;
            for (int i = s1; i < e1; i++) {
                list.add(i);
            }
        }
        return list;
    }

    /**
     * 取数组的平均值
     */
    public static double average(double[] array, int code) {
        double sum = 0;
        int end = array.length;
        Arrays.sort(array);
        if (code == 1) {
            end = array.length - array.length / 10;
        }
        for (int i = 0; i < end; i++) {
            sum = sum + array[i];
        }
        return sum / end;
    }

    /**
     * 获得标准正太分布的随机因子
     */
    public static double getGaussian(){
        Random r = new Random();
        return r.nextGaussian();
    }

    /**
     * 输出写入txt文件函数
     */
    public static void fileChaseFW(String filePath, String content) {
        try {
            //构造函数中的第二个参数true表示以追加形式写文件
            FileWriter fw = new FileWriter(filePath,true);
            fw.write(content);
            fw.write('\n');
            fw.close();
            System.out.println(content);
        } catch (IOException e) {
            System.out.println("文件写入失败！" + e);
        }
    }
}