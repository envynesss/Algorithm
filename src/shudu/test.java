package shudu;

import FunLibrary.FunLib;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class test{
    public static void main(String[] args) {
        //{0.07972916815706897}; // 0.9991090310270105
        //{0.24665546669843014}; // 0.9545953361349705
        //{0.45062670094296264}; // 0.7669217995890066
        //{0.6789383041085573}; // 0.4823351006413628
        //{0.9297342454298296}; // 0.22341336187772662
        double[][] x_temp = new double[5][1];
        String s = "[0.07972916797568522]\n" +
                "[0.24627774377415926]\n" +
                "[0.4494078567069479]\n" +
                "[0.6789383038586885]\n" +
                "[0.9297342456419284]";
        String[] ss = s.split("\n");
        for (int i = 0; i < ss.length; i++) {
            x_temp[i][0] = Double.parseDouble(ss[i].substring(1, ss[i].length() - 1));
        }

        double[][] x_best = {{0.07972916815706897},{0.24665546669843014},{0.45062670094296264},{0.6789383041085573},{0.9297342454298296}};
        for (int i = 0; i < x_best.length; i++) {
            double f_best = FunLib.funLib(x_best[i], 8);
            double f_temp = FunLib.funLib(x_temp[i], 8);
            if (f_temp > f_best) {
                System.out.println(x_best[i][0] + "}; // " + f_temp);
            }
        }
    }
}