package PSO;

import FunLibrary.FunLib;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class PSOMain {
    public static int[] isRunFun = {10, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0}; // 十个函数是否运行，[1]开始，1代表运行
    public static int[] isRunSwarm = {2, 0, 1}; // 2类种群机制是否运行，index[1]开始，1代表运行
    public static void main(String[] args) {
        long sTime = new Date().getTime();
        runWhichFunc();
        long eTime = new Date().getTime();
        String str = "run time:" + 1.0 * (eTime - sTime) / (1000 * 60) + "M";
        Firefly.Constant.fileChaseFW(Constant.filePath, str);
    }

    public static List<Double> runSwarm(int code, Swarm swarm){
        swarm.setSeedList();
        for(int k = 1; k <= Constant.iterations; k++){
            swarm.move();
            swarm.setSeedList();
            swarm.setFirstTime(k);
        }
        System.out.println(swarm.seedList.size());
        for (int i = 0; i < swarm.seedList.size(); i++) {
            System.out.println(swarm.seedList.get(i));
        }
        System.out.println();
        return swarm.getResultList();
    }

    /**
     * 设置并运行标准函数的函数
     */
    public static void runWhichFunc() {
        String str;

        if (isRunFun[1] == 1) {
            FunLib.f1 f1 = new FunLib.f1();
            Constant.setFuncPara(f1.codeNum,f1.Dims,f1.species_rs,f1.maxRange,f1.minRange,f1.lPoints,f1.gpoints,f1.DisThreshold,f1.FitThreshold);
            str = "********************f1 function start*********************";
            Firefly.Constant.fileChaseFW(Constant.filePath, str);
            runWhichSwarm();
        }
        if (isRunFun[2] == 1) {
            FunLib.f2 f2 = new FunLib.f2();
            Constant.setFuncPara(f2.codeNum,f2.Dims,f2.species_rs,f2.maxRange,f2.minRange,f2.lPoints,f2.gpoints,f2.DisThreshold,f2.FitThreshold);
            str = "********************f2 function start*********************";
            Firefly.Constant.fileChaseFW(Constant.filePath, str);
            runWhichSwarm();
        }
        if (isRunFun[3] == 1) {
            FunLib.f3 f3 = new FunLib.f3();
            Constant.setFuncPara(f3.codeNum,f3.Dims,f3.species_rs,f3.maxRange,f3.minRange,f3.lPoints,f3.gpoints,f3.DisThreshold,f3.FitThreshold);
            str = "********************f3 function start*********************";
            Firefly.Constant.fileChaseFW(Constant.filePath, str);
            runWhichSwarm();
        }
        if (isRunFun[4] == 1) {
            FunLib.f4 f4 = new FunLib.f4();
            Constant.setFuncPara(f4.codeNum,f4.Dims,f4.species_rs,f4.maxRange,f4.minRange,f4.lPoints,f4.gpoints,f4.DisThreshold,f4.FitThreshold);
            str = "********************f4 function start*********************";
            Firefly.Constant.fileChaseFW(Constant.filePath, str);
            runWhichSwarm();
        }
        if (isRunFun[5] == 1) {
            FunLib.f5 f5 = new FunLib.f5();
            Constant.setFuncPara(f5.codeNum,f5.Dims,f5.species_rs,f5.maxRange,f5.minRange,f5.lPoints,f5.gpoints,f5.DisThreshold,f5.FitThreshold);
            str = "********************f5 function start*********************";
            Firefly.Constant.fileChaseFW(Constant.filePath, str);
            runWhichSwarm();
        }
        if (isRunFun[6] == 1) {
            FunLib.f6 f6 = new FunLib.f6();
            Constant.setFuncPara(f6.codeNum,f6.Dims,f6.species_rs,f6.maxRange,f6.minRange,f6.lPoints,f6.gpoints,f6.DisThreshold,f6.FitThreshold);
            str = "********************f6 function start*********************";
            Firefly.Constant.fileChaseFW(Constant.filePath, str);
            runWhichSwarm();
        }
        if (isRunFun[7] == 1) {
            FunLib.f7 f7 = new FunLib.f7();
            Constant.setFuncPara(f7.codeNum,f7.Dims,f7.species_rs,f7.maxRange,f7.minRange,f7.lPoints,f7.gpoints,f7.DisThreshold,f7.FitThreshold);
            str = "********************f7 function start*********************";
            Firefly.Constant.fileChaseFW(Constant.filePath, str);
            runWhichSwarm();
        }
        if (isRunFun[8] == 1) {
            FunLib.f8 f8 = new FunLib.f8();
            Constant.setFuncPara(f8.codeNum,f8.Dims,f8.species_rs,f8.maxRange,f8.minRange,f8.lPoints,f8.gpoints,f8.DisThreshold,f8.FitThreshold);
            str = "********************f8 function start*********************";
            Firefly.Constant.fileChaseFW(Constant.filePath, str);
            runWhichSwarm();
        }
        if (isRunFun[9] == 1) {
            FunLib.f9 f9 = new FunLib.f9();
            Constant.setFuncPara(f9.codeNum,f9.Dims,f9.species_rs,f9.maxRange,f9.minRange,f9.lPoints,f9.gpoints,f9.DisThreshold,f9.FitThreshold);
            str = "********************f9 function start*********************";
            Firefly.Constant.fileChaseFW(Constant.filePath, str);
            runWhichSwarm();
        }
        if (isRunFun[10] == 1) {
            FunLib.f10 f10 = new FunLib.f10();
            Constant.setFuncPara(f10.codeNum,f10.Dims,f10.species_rs,f10.maxRange,f10.minRange,f10.lPoints,f10.gpoints,f10.DisThreshold,f10.FitThreshold);
            str = "********************f10 function start*********************";
            Firefly.Constant.fileChaseFW(Constant.filePath, str);
            runWhichSwarm();
        }
    }

    /**
     * 运行哪种机制的种群的函数
     */
    public static void runWhichSwarm() {
        if (isRunSwarm[1] == 1) {
            repeatedRunSwarm(1); //repeat runBaseSwarm()
        }
        if (isRunSwarm[2] == 1) {
            repeatedRunSwarm(2); //repeat runSpeciesSwarm()
        }
    }

    /**
     * 设置种群独立运行次数的函数 code 种群机制的代号
     */
    public static void repeatedRunSwarm(int code){
        int times = Constant.runTimes;
        List<Double> resultList = new ArrayList<>();
        String s = "";
        double[] arrAccuracy = new double[times];
        double[] arrVRate = new double[times];
        double[] arrLPumber = new double[times];
        double[] arrMinTimes = new double[times];
        for (int i = 0; i < times; i++) {
            switch (code){
                case 1:
                    resultList = runSwarm(code, new Swarm());
                    s = code + "-Swarm()";
                    break;
                case 2:
                    resultList = runSwarm(code, new SPSwarm());
                    s = code + "-SPSwarm()";
                    break;
                default:
                    break;
            }
            arrAccuracy[i] = resultList.get(0);
            arrVRate[i] = resultList.get(1);
            arrLPumber[i] = resultList.get(2);
            arrMinTimes[i] = resultList.get(3);
        }

        double accuracy = Firefly.Constant.average(arrAccuracy, 1);
        double VRate = Firefly.Constant.average(arrVRate, 0);
        double LPumber = Firefly.Constant.average(arrLPumber, 0);
        double minTimes = Firefly.Constant.average(arrMinTimes, 0);
        s = s + " 独立运行" + times + "次后平均值为  ACC=" + accuracy; // 准确性
        s = s + "; SR=" + VRate; // 成功率
        s = s + "; LOP=" + LPumber; // 局部最优值个数
        s = s + "; MR=" + minTimes; // 最小收敛速度
        Firefly.Constant.fileChaseFW(Constant.filePath, s);

        List<Double> list = new ArrayList<>();
        list.add(accuracy);
        list.add(VRate);
        list.add(minTimes);
        list.add(LPumber);
        //Firefly.Constant.writeExcel(list, Firefly.Constant.XlsxPath, Constant.codeNum + 15, 1);
    }
}

