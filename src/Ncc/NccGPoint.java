package Ncc;

import Firefly.Constant;

import java.util.*;

//4个最优点的坐标： (153, 178, 0.88484)，(278, 282, 0.9251)，(300, 385, 0.88532)，(340, 156, 0.88512)。
public class NccGPoint {
    public static void main(String[] args) {
        new NccValue();
        Constant.fileChaseFW(NC.filePath, "**********************START**********************");
        int[] np = {100, 120};
        int[] rs = {50, 80, 100, 120};
        for (int i = 0; i < np.length; i++) {
            for (int j = 0; j < rs.length; j++) {
                Constant.fileChaseFW(NC.filePath, "----------------NumofP = " + np[i] + " species_rs = "+ rs[j] + "----------------");
                NC.setNPRS(np[i], rs[j]);
                ParaRun();
            }
        }
    }

    public static void ParaRun(){
        double[] GPointNumbers = new double[NC.runTimes];
        double[] VisitNumbers = new double[NC.runTimes];
        double[] Time = new double[NC.runTimes];
        for (int i = 0; i < NC.runTimes; i++) {
            double[] results = run(1);
            GPointNumbers[i] = results[0];
            VisitNumbers[i] = results[1];
            Time[i] = results[2];
        }
        String s = "";
        s = s + "独立运行" + NC.runTimes + "次后平均值为 GPN: " + NC.average(GPointNumbers);
        s = s + "; VN: " + NC.average(VisitNumbers);
        s = s + "; TIME: " + NC.average(Time);
        Arrays.sort(Time);
        s = s + ". 其中，Min time: " + Time[0] + "  Max time: " + Time[Time.length - 1];
        Constant.fileChaseFW(NC.filePath, s);
    }

    public static double[] run(int code) {
        long sTime = new Date().getTime();
        PointSwarm swarm = new PointSwarm();
        swarm.setSeedList();
        for(int k = 1; k <= NC.iterations; k++){
            if (code != 0 && k > NC.iterations / 3) {
                swarm.localSearch();
            }
            swarm.classification();
            swarm.move();
            swarm.SetRedundant();
            swarm.setSeedList();
        }
        /*System.out.println("Size of seedList: " + swarm.seedList.size());
        for (int i = 0; i < NC.validSeedSize; i++) {
            System.out.println(swarm.seedList.get(i));
        }*/
        double[] results = new double[3];

        int GPointNumber = swarm.getGPointNumber();
        results[0] = GPointNumber;
        String s0 = "Number of global points: " + GPointNumber;
        //Constant.fileChaseFW(NC.filePath, s0);

        int VisitNumber = swarm.getVisitNumber();
        results[1] = VisitNumber;
        String s1 = "Number of points visited: " + VisitNumber;
        //Constant.fileChaseFW(NC.filePath, s1);

        long eTime = new Date().getTime();
        double time = 1.0 * (eTime - sTime) / (1000);
        results[2] = time;
        String s2 = "run time: " + time + "S";
        //Constant.fileChaseFW(NC.filePath, s2);
        return results;
    }
}