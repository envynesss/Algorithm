package Test;

import Firefly.Constant;
import FunLibrary.FunLib;
import Ncc.NccValue;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Workbook;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;


public class test {
    public static void main(String[] args) {
        /*double[][] x_temp = new double[5][2];
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

        /*double[][] x_temp = {
                {-7.589893, -7.708314},
                {-7.589893, -1.425128},
                {-7.589893, 4.858057},
                {-1.306708, -7.708314},
                {-1.306708, -1.425128},
                {-1.306708, 4.858057},
                {4.976478, -1.425128},
                {4.976478, -7.708314},
                {4.976478, 4.858057},
        };
        for (int i = 0; i < x_temp.length; i++) {
            double f_temp = FunLib.funLib(x_temp[i], 11);
            System.out.println("{" + x_temp[i][0] + ", " + x_temp[i][1] + "}; // " + f_temp);
        }*/

        String s1 = "Echo State Networks Based Data-Driven Adaptive Fault Tolerant Control With Its Application to Electromechanical System";
        String s2 = "Echo State Networks Based Data-Driven Adaptive Fault Tolerant Control With Its Application to Electromechanical System";
        System.out.println(s1==s2);
    }

    public static void getType(Object object) {
        String typeName = object.getClass().getName();
        int length = typeName.lastIndexOf(".");
        String type = typeName.substring(length + 1);
        System.out.println(type);
    }

    public static void printArray(double[][] array, int row, int col) {

        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                if (array[i][j] > 0.87503) {
                    System.out.println("(" + i + ", " + j + ", " + array[i][j] + ")");
                }
            }
        }
    }
}