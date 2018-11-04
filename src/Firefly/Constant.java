package Firefly;

import java.io.FileWriter;
import java.io.IOException;
import java.util.*;

/**
 * The Constants for Firefly Algorithm
 */
public class Constant{

    public static final int NumofP = 50;
    //γ为light_absorption光吸收率属于[0.1,10],its setting depends on the problem to be optimized.
    public static final double gamma=0.9;
    //β0
    public static final double beta0=0.1;
    //α为Step factor步长因子属于[0,1]
    //public static double alpha=0.001; // 函数中配置

    public static int iterations = 30000; //迭代次数
    public static int runTimes = 1; //独立运行次数
    public static int CBLS_step = 5; //局域搜索的探索次数
    public static double CBLS_move = 0.00000001; //局域搜索的移动距离

    //函数相关参数
    public static int codeNum;
    public static int funDims;
    public static double species_rs;
    public static double[] maxRange;
    public static double[] minRange;
    public static double[][] optimalpoints;
    public static double[][] gpoints;
    public static double alpha;
    public static double Acc_Thr;

    //输出文件路径
    public static String folder = "z-output/";
    public static String filePath = folder + "0_data.txt";

    /**
     * Function 函数相关参数社设置
     */
    public static void setFuncPara(int code_Num, int fun_Dims, double species_Rs, double[] max_Range, double[] min_Range,
                                   double[][] optimal_points, double[][] g_points, double Alpha, double acc_Thr) {
        Constant.codeNum = code_Num;
        Constant.funDims = fun_Dims;
        Constant.species_rs = species_Rs;
        Constant.maxRange = max_Range;
        Constant.minRange = min_Range;
        Constant.optimalpoints = optimal_points;
        Constant.gpoints = g_points;
        Constant.alpha = Alpha;
        Constant.Acc_Thr = acc_Thr;
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

    public static void printArray(double[][] array, int row, int col) {

        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                System.out.print(array[i][j] + " ");
            }
            System.out.println();
        }
    }
}