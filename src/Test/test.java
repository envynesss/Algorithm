package Test;

import FunLibrary.FunLib;
import Ncc.NccValue;

import java.util.Random;


public class test{
    public static void main(String[] args) {
        /**
        double[][] x_temp = new double[5][2];
        String s = "[-3.1415928390709174, 12.275000278553842]\n" +
                "[3.141593142539463, 2.2749996020727328]\n" +
                "[9.42477840277364, 2.474999606016829]";
        String[] ss = s.split("\n");
        for (int i = 0; i < ss.length; i++) {
            String n = ss[i].substring(1, ss[i].length() - 1);
            String[] nn = n.split(",");
            for (int j = 0; j < nn.length; j++) {
                x_temp[0][j] = Double.parseDouble(nn[j].trim());
            }
        }

        double[][] x_temp = {{-Math.PI, 12.275},{Math.PI, 2.275},{3 * Math.PI, 2.475}};

        double[][] x_best = {FunLib.f4.f4_gpoint_1,FunLib.f4.f4_gpoint_2,FunLib.f4.f4_gpoint_3};
        for (int i = 0; i < x_best.length; i++) {
            double f_best = FunLib.funLib(x_best[i], 4);
            double f_temp = FunLib.funLib(x_temp[i], 4);
            if (f_temp > f_best) {
                System.out.println(x_temp[i][0] + ", " + x_temp[i][1] + "}; // " + f_temp);
            }
        }
        */

        /*NccValue nv = new NccValue();
        printArray(nv.nccMatrix, nv.row, nv.col);*/
    }

    public static void printArray(double[][] array, int row, int col) {

        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                if (array[i][j] > 0.87503) {
                    System.out.println("(" + i +", " + j + ", " + array[i][j] + ")");
                }
            }
        }
    }
}