package Firefly;

import FunLibrary.FunLib;

import java.util.*;

/**
 * The Constants for FireFly Algorithm
 */
public class Constant{

    public static final int NumofP = 50;
    //γ为light_absorption光吸收率属于[0.1,10],its setting depends on the problem to be optimized.
    public static final double gamma=0.9;
    //β0
    public static final double beta0=0.1;
    //α为Step factor步长因子属于[0,1]
    public static final double alpha=0.01;

    public static int iterations = 30000;
    public static double Acc_Thr = 0.0001; // Accuracy_Threshold

    public static int CBLS_step = 5; //局域搜索的探索次数
    public static double CBLS_move = 0.00000001; //局域搜索的移动距离

    public static int RWDE_step = 5; //局域搜索的探索次数
    public static double RWDE_move = 0.00000001; //局域搜索的移动距离


    //Function 参数相关配置
    static FunLib.f9 fun = new FunLib.f9();
    public static int codeNum = fun.codeNum;
    public static int funDims = fun.Dims;
    public static double species_rs = fun.species_rs;
    public static double[] maxRange = fun.maxRange;
    public static double[] minRange = fun.minRange;
    public static double[][] optimalpoints = fun.optimalpoints;

    /*
    取到环形邻域结构的seed的邻域
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

    /*
    取数组的平均值
     */
    public static double average(double[] array) {
        double sum = 0;
        Arrays.sort(array);
        for (int i = 0; i < array.length - array.length * 0.1; i++) {
            sum = sum + array[i];
        }
        return sum / array.length;
    }

    /*
    获得标准正太分布的随机因子
     */
    public static double getGaussian(){
        Random r = new Random();
        return r.nextGaussian();
    }
}