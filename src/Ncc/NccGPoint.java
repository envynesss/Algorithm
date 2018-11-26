package Ncc;

import Firefly.Constant;

import java.util.*;

//4个最优点的坐标： (153, 178, 0.88484)，(278, 282, 0.9251)，(300, 385, 0.88532)，(340, 156, 0.88512)。
public class NccGPoint {
    public static void main(String[] args) {
        new NccValue();
        Constant.fileChaseFW(NC.filePath, "**********************START**********************");
        int[] np = NC.NumofPs;
        int[] rs = NC.rss;
        for (int i = 0; i < np.length; i++) {
            for (int j = 0; j < rs.length; j++) {
                NC.setNPRS(np[i], rs[j]);
                String s = "-----NumofP: " + NC.NumofP+ " rs: "+ NC.species_rs + "------独立运行" + NC.runTimes + "次后平均值为----------";
                Constant.fileChaseFW(NC.filePath, s);
                ParaRun();
            }
        }
    }

    public static void ParaRun(){
        double[] GPointNumbers = new double[NC.runTimes];
        double[] VisitNumbers = new double[NC.runTimes];
        double[] SeedNumbers = new double[NC.runTimes];
        double[] Time = new double[NC.runTimes];
        for (int i = 0; i < NC.runTimes; i++) {
            double[] results = run(1);
            GPointNumbers[i] = results[0];
            VisitNumbers[i] = results[1];
            SeedNumbers[i] = results[2];
            Time[i] = results[3];
        }
        Arrays.sort(Time);
        String s = "";
        s = s + "SucRate VitNum MinTime MaxTime AvgTime SeedNum: ";
        s = s + " " + NC.round(NC.average(GPointNumbers, 1) / 4.0 * 100, 2) + "%";
        s = s + " " + Math.round(NC.average(VisitNumbers, 0));
        s = s + " " + Time[0] + " " + Time[Time.length - 1];
        s = s + " " + NC.round(NC.average(Time, 0), 3);
        s = s + " " + Math.round(NC.average(SeedNumbers, 0));
        Constant.fileChaseFW(NC.filePath, s);
    }

    public static double[] run(int code) {
        long sTime = new Date().getTime();
        PointSwarm swarm = new PointSwarm();
        swarm.writeXY(0);
        swarm.setSeedList();
        for(int k = 1; k <= NC.iterations; k++){
            if (code != 0 && k > NC.iterations / 3) {
                swarm.localSearch();
            }
            swarm.classification();
            swarm.move();
            swarm.writeXY(k);
            swarm.SetRedundant(k);
            swarm.setSeedList();
        }
        /*for (int i = 0; i < NC.validSeedSize; i++) {
            System.out.println(swarm.seedList.get(i));
        }*/
        double[] results = new double[4];

        int GPointNumber = swarm.getGPointNumber();
        results[0] = GPointNumber;
        String s0 = "Number of global points: " + GPointNumber;
        //Constant.fileChaseFW(NC.filePath, s0);

        int VisitNumber = swarm.getVisitNumber();
        results[1] = VisitNumber;
        String s1 = "Number of points visited: " + VisitNumber;
        //Constant.fileChaseFW(NC.filePath, s1);

        int SeedNumber = swarm.getSeedNumber();
        results[2] = SeedNumber;
        String s2 = "Number of Seeds: " + SeedNumber;
        //Constant.fileChaseFW(NC.filePath, s2);

        long eTime = new Date().getTime();
        double time = 1.0 * (eTime - sTime) / (1000);
        results[3] = time;
        String s3 = "run time: " + time + "S";
        //Constant.fileChaseFW(NC.filePath, s3);

        return results;
    }
}