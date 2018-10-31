package Firefly;

import FunLibrary.FunLib;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        String str;
        long sTime = new Date().getTime();

        FunLib.f1 f1 = new FunLib.f1();
        Constant.setFuncPara(f1.codeNum,f1.Dims,f1.species_rs,f1.maxRange,f1.minRange,f1.optimalpoints,f1.gpoints,f1.alpha,f1.Acc_Thr);
        str = "********************f1 function start*********************";
        Constant.fileChaseFW(Constant.filePath, str);
        runFunc();

        FunLib.f2 f2 = new FunLib.f2();
        Constant.setFuncPara(f2.codeNum,f2.Dims,f2.species_rs,f2.maxRange,f2.minRange,f2.optimalpoints,f2.gpoints,f2.alpha,f2.Acc_Thr);
        str = "********************f2 function start*********************";
        Constant.fileChaseFW(Constant.filePath, str);
        runFunc();

        FunLib.f3 f3 = new FunLib.f3();
        Constant.setFuncPara(f3.codeNum,f3.Dims,f3.species_rs,f3.maxRange,f3.minRange,f3.optimalpoints,f3.gpoints,f3.alpha,f3.Acc_Thr);
        str = "********************f3 function start*********************";
        Constant.fileChaseFW(Constant.filePath, str);
        runFunc();

        FunLib.f4 f4 = new FunLib.f4();
        Constant.setFuncPara(f4.codeNum,f4.Dims,f4.species_rs,f4.maxRange,f4.minRange,f4.optimalpoints,f4.gpoints,f4.alpha,f4.Acc_Thr);
        str = "********************f4 function start*********************";
        Constant.fileChaseFW(Constant.filePath, str);
        runFunc();

        FunLib.f5 f5 = new FunLib.f5();
        Constant.setFuncPara(f5.codeNum,f5.Dims,f5.species_rs,f5.maxRange,f5.minRange,f5.optimalpoints,f5.gpoints,f5.alpha,f5.Acc_Thr);
        str = "********************f5 function start*********************";
        Constant.fileChaseFW(Constant.filePath, str);
        runFunc();

        FunLib.f6 f6 = new FunLib.f6();
        Constant.setFuncPara(f6.codeNum,f6.Dims,f6.species_rs,f6.maxRange,f6.minRange,f6.optimalpoints,f6.gpoints,f6.alpha,f6.Acc_Thr);
        str = "********************f6 function start*********************";
        Constant.fileChaseFW(Constant.filePath, str);
        runFunc();

        FunLib.f7 f7 = new FunLib.f7();
        Constant.setFuncPara(f7.codeNum,f7.Dims,f7.species_rs,f7.maxRange,f7.minRange,f7.optimalpoints,f7.gpoints,f7.alpha,f7.Acc_Thr);
        str = "********************f7 function start*********************";
        Constant.fileChaseFW(Constant.filePath, str);
        runFunc();

        FunLib.f8 f8 = new FunLib.f8();
        Constant.setFuncPara(f8.codeNum,f8.Dims,f8.species_rs,f8.maxRange,f8.minRange,f8.optimalpoints,f8.gpoints,f8.alpha,f8.Acc_Thr);
        str = "********************f8 function start*********************";
        Constant.fileChaseFW(Constant.filePath, str);
        runFunc();

        FunLib.f9 f9 = new FunLib.f9();
        Constant.setFuncPara(f9.codeNum,f9.Dims,f9.species_rs,f9.maxRange,f9.minRange,f9.optimalpoints,f9.gpoints,f9.alpha,f9.Acc_Thr);
        str = "********************f9 function start*********************";
        Constant.fileChaseFW(Constant.filePath, str);
        runFunc();

        FunLib.f10 f10 = new FunLib.f10();
        Constant.setFuncPara(f10.codeNum,f10.Dims,f10.species_rs,f10.maxRange,f10.minRange,f10.optimalpoints,f10.gpoints,f10.alpha,f10.Acc_Thr);
        str = "********************f10 function start*********************";
        Constant.fileChaseFW(Constant.filePath, str);
        runFunc();

        long eTime = new Date().getTime();
        Constant.fileChaseFW(Constant.filePath, "run time:" + 1.0 * (eTime - sTime) / (1000 * 60) + "M");
    }

    public static void runFunc() {
        repeatedRun(1); //repeat runBaseSwarm()
        repeatedRun(2); //repeat runSpeciesSwarm()
        repeatedRun(3); //repeat runSpeciesSwarm_number()
    }

    public static void repeatedRun(int code){
        int times = Constant.runTimes;
        List<Double> resultList;
        String s = "";
        double[] arrAccuracy = new double[times];
        double[] arrVRate = new double[times];
        double[] arrLPumber = new double[times];
        double[] minTimes = new double[times];
        for (int i = 0; i < times; i++) {
            switch (code){
                case 1:
                    resultList = runBaseSwarm(code);
                    s = "runBaseSwarm()";
                    break;
                case 2:
                    resultList = runSpeciesSwarm(code);
                    s = "runSpeciesSwarm()";
                    break;
                case 3:
                    resultList = runSpeciesSwarm_number(code);
                    s = "runSpeciesSwarm_number()";
                    break;
                default:
                    resultList = runSpeciesSwarm(code);
                    s = "default runSpeciesSwarm()";
                    break;
            }
            arrAccuracy[i] = resultList.get(0);
            arrVRate[i] = resultList.get(1);
            arrLPumber[i] = resultList.get(2);
            minTimes[i] = resultList.get(3);
        }
        s = s + "\n独立运行" + times + "次后平均值为  ACC=" + Constant.average(arrAccuracy, 1);
        s = s + "; SR=" + Constant.average(arrVRate, 0);
        s = s + "; LOP=" + Constant.average(arrLPumber, 0);
        s = s + "; MR=" + Constant.average(minTimes, 0);
        Constant.fileChaseFW(Constant.filePath, s);
    }

    public static List<Double> runBaseSwarm(int code){
        baseSwarm swarm = new baseSwarm();
        boolean found = false;
        for(int k = 1; k <= Constant.iterations; k++){
            swarm.move();
            swarm.sortSwarm();
            swarm.writeXY(code, k);
            swarm.setSeedList();
            if (!found && swarm.getAccuracy() < Constant.Acc_Thr) {
                found = true;
                swarm.firstTime = k;
            }
        }
        return swarm.getResultList();
    }

    public static List<Double> runSpeciesSwarm(int code) {
        speciesSwarm swarm= new speciesSwarm();
        swarm.setSeedList();
        boolean found = false;
        for(int k = 1; k <= Constant.iterations; k++){
            swarm.localSearch(k);
            swarm.classification();
            swarm.move();
            swarm.writeXY(code, k);
            swarm.setSeedList();
            if (!found && swarm.getAccuracy() < Constant.Acc_Thr) {
                found = true;
                swarm.firstTime = k;
            }
        }/*
        for (int i = 0; i < swarm.seedList.size(); i++) {
            System.out.println(swarm.seedList.get(i));
        }*/
        return swarm.getResultList();
    }

    public static List<Double> runSpeciesSwarm_number(int code){
        speciesNSwarm swarm= new speciesNSwarm();
        swarm.setSeedList();
        boolean found = false;
        for(int k = 1; k <= Constant.iterations; k++){
            swarm.localSearch(k);
            swarm.classification();
            swarm.move();
            swarm.writeXY(code, k);
            swarm.setSeedList();
            if (!found && swarm.getAccuracy() < Constant.Acc_Thr) {
                found = true;
                swarm.firstTime = k;
            }
        }
        return swarm.getResultList();
    }

}
