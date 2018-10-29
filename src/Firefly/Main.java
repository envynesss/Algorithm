package Firefly;

import FunLibrary.FunLib;

import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        String str;

        /*FunLib.f1 f1 = new FunLib.f1();
        Constant.setFuncPara(f1.codeNum,f1.Dims,f1.species_rs,f1.maxRange,f1.minRange,f1.optimalpoints,f1.gpoints,f1.alpha,f1.Acc_Thr);
        str = "********************f1 function start*********************";
        System.out.println(str);
        Constant.fileChaseFW(Constant.filePath, str);
        runFunc();

        FunLib.f2 f2 = new FunLib.f2();
        Constant.setFuncPara(f2.codeNum,f2.Dims,f2.species_rs,f2.maxRange,f2.minRange,f2.optimalpoints,f2.gpoints,f2.alpha,f2.Acc_Thr);
        str = "********************f2 function start*********************";
        System.out.println(str);
        Constant.fileChaseFW(Constant.filePath, str);
        runFunc();

        FunLib.f3 f3 = new FunLib.f3();
        Constant.setFuncPara(f3.codeNum,f3.Dims,f3.species_rs,f3.maxRange,f3.minRange,f3.optimalpoints,f3.gpoints,f3.alpha,f3.Acc_Thr);
        str = "********************f3 function start*********************";
        System.out.println(str);
        Constant.fileChaseFW(Constant.filePath, str);
        runFunc();

        FunLib.f4 f4 = new FunLib.f4();
        Constant.setFuncPara(f4.codeNum,f4.Dims,f4.species_rs,f4.maxRange,f4.minRange,f4.optimalpoints,f4.gpoints,f4.alpha,f4.Acc_Thr);
        str = "********************f4 function start*********************";
        System.out.println(str);
        Constant.fileChaseFW(Constant.filePath, str);
        runFunc();

        FunLib.f5 f5 = new FunLib.f5();
        Constant.setFuncPara(f5.codeNum,f5.Dims,f5.species_rs,f5.maxRange,f5.minRange,f5.optimalpoints,f5.gpoints,f5.alpha,f5.Acc_Thr);
        str = "********************f5 function start*********************";
        System.out.println(str);
        Constant.fileChaseFW(Constant.filePath, str);
        runFunc();

        FunLib.f6 f6 = new FunLib.f6();
        Constant.setFuncPara(f6.codeNum,f6.Dims,f6.species_rs,f6.maxRange,f6.minRange,f6.optimalpoints,f6.gpoints,f6.alpha,f6.Acc_Thr);
        str = "********************f6 function start*********************";
        System.out.println(str);
        Constant.fileChaseFW(Constant.filePath, str);
        runFunc();*/

        FunLib.f7 f7 = new FunLib.f7();
        Constant.setFuncPara(f7.codeNum,f7.Dims,f7.species_rs,f7.maxRange,f7.minRange,f7.optimalpoints,f7.gpoints,f7.alpha,f7.Acc_Thr);
        str = "********************f7 function start*********************";
        System.out.println(str);
        Constant.fileChaseFW(Constant.filePath, str);
        runFunc();

        /*FunLib.f8 f8 = new FunLib.f8();
        Constant.setFuncPara(f8.codeNum,f8.Dims,f8.species_rs,f8.maxRange,f8.minRange,f8.optimalpoints,f8.gpoints,f8.alpha,f8.Acc_Thr);
        str = "********************f8 function start*********************";
        System.out.println(str);
        Constant.fileChaseFW(Constant.filePath, str);
        runFunc();

        FunLib.f9 f9 = new FunLib.f9();
        Constant.setFuncPara(f9.codeNum,f9.Dims,f9.species_rs,f9.maxRange,f9.minRange,f9.optimalpoints,f9.gpoints,f9.alpha,f9.Acc_Thr);
        str = "********************f9 function start*********************";
        System.out.println(str);
        Constant.fileChaseFW(Constant.filePath, str);
        runFunc();

        FunLib.f10 f10 = new FunLib.f10();
        Constant.setFuncPara(f10.codeNum,f10.Dims,f10.species_rs,f10.maxRange,f10.minRange,f10.optimalpoints,f10.gpoints,f10.alpha,f10.Acc_Thr);
        str = "********************f10 function start*********************";
        System.out.println(str);
        Constant.fileChaseFW(Constant.filePath, str);
        runFunc();*/

        /*FunLib.f11 f11 = new FunLib.f11();
        Constant.setFuncPara(f11.codeNum,f11.Dims,f11.species_rs,f11.maxRange,f11.minRange,f11.optimalpoints,f11.gpoints,f11.alpha,f11.Acc_Thr);
        str = "********************f11 function start*********************";
        System.out.println(str);
        Constant.fileChaseFW(Constant.filePath, str);
        runFunc();*/
    }

    public static void runFunc() {
        int times = Constant.runTimes;
        List<Double> resultList = new ArrayList<>();
        double[] arrAccuracy = new double[times];
        double[] arrVRate = new double[times];
        double[] arrLPumber = new double[times];

        /*String s11 = "runBaseSwarm()";
        System.out.println(s11);
        Constant.fileChaseFW(Constant.filePath, s11);
        for (int i = 0; i < times; i++) {
            resultList = runBaseSwarm();
            arrAccuracy[i] = resultList.get(0);
            arrVRate[i] = resultList.get(1);
            arrLPumber[i] = resultList.get(2);
        }
        String s12 = "\n独立运行" + times + "次后 accuracy 平均值为：" + Constant.average(arrAccuracy, 1);
        s12 = s12 + " Success rate 平均值为：" + Constant.average(arrVRate, 0);
        s12 = s12 + " find local optimal point 平均值为：" + Constant.average(arrLPumber, 0);
        System.out.println(s12);
        Constant.fileChaseFW(Constant.filePath, s12);*/

        String s21 = "runSpeciesSwarm()";
        System.out.println(s21);
        Constant.fileChaseFW(Constant.filePath, s21);
        for (int i = 0; i < times; i++) {
            resultList = runSpeciesSwarm();
            arrAccuracy[i] = resultList.get(0);
            arrVRate[i] = resultList.get(1);
            arrLPumber[i] = resultList.get(2);
        }
        String s22 = "\n独立运行" + times + "次后 accuracy 平均值为：" + Constant.average(arrAccuracy, 1);
        s22 = s22 + " Success rate 平均值为：" + Constant.average(arrVRate, 0);
        s22 = s22 + " find local optimal point 平均值为：" + Constant.average(arrLPumber, 0);
        System.out.println(s22);
        Constant.fileChaseFW(Constant.filePath, s22);

        /*String s31 = "runSpeciesSwarm_number()";
        System.out.println(s31);
        Constant.fileChaseFW(Constant.filePath, s31);
        for (int i = 0; i < times; i++) {
            resultList = runSpeciesSwarm_number();
            arrAccuracy[i] = resultList.get(0);
            arrVRate[i] = resultList.get(1);
            arrLPumber[i] = resultList.get(2);
        }
        String s32 = "\n独立运行" + times + "次后 accuracy 平均值为：" + Constant.average(arrAccuracy, 1);
        s32 = s32 + " Success rate 平均值为：" + Constant.average(arrVRate, 0);
        s32 = s32 + " find local optimal point 平均值为：" + Constant.average(arrLPumber, 0);
        System.out.println(s32);
        Constant.fileChaseFW(Constant.filePath, s32);*/
    }

    public static List<Double> runBaseSwarm(){
        baseSwarm swarm = new baseSwarm();
        for(int k = 1; k <= Constant.iterations; k++){
            //System.out.println(k + "次*********************************");
            swarm.move();
            swarm.sortSwarm();
			/*for(int i = 0; i < Constant.NumofP; i++){
				System.out.print("第" + k + " 次");
				for(int d = 0; d < Constant.funDims; d++){
					System.out.print(" " + swarm.fireflyList.get(i).x[d]);
				}
				System.out.println(" fitness:" + swarm.fireflyList.get(i).fitnessfun());
			}*/
            swarm.setSeedList();
        }
        return swarm.getAccuracy();
    }

    public static List<Double> runSpeciesSwarm() {
        speciesSwarm swarm= new speciesSwarm();
        swarm.setSeedList();
        for(int k = 1; k <= Constant.iterations; k++){
            //swarm.CBLS_RWDE_fun();
            swarm.classification();
            swarm.move();
            swarm.setSeedList();
        }
        return swarm.getAccuracy();
    }

    public static List<Double> runSpeciesSwarm_number(){
        speciesNSwarm swarm= new speciesNSwarm();
        swarm.setSeedList();
        for(int k = 1; k <= Constant.iterations; k++){
            //swarm.CBLS_RWDE_fun();
            swarm.classification();
            swarm.move();
            swarm.setSeedList();
        }
        return swarm.getAccuracy();
    }
}
