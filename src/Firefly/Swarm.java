package Firefly;

import java.util.*;
import java.io.*;

public class Swarm {
    List<firefly> fireflyList = new ArrayList();
    List<firefly> seedList = new ArrayList<>();
    List<List<firefly>> speciesList = new ArrayList<>();
    int firstTime;
    boolean isFirstTime;
    int gPointNumber;

    public Swarm(){
        for(int i = 0; i < Constant.NumofP; i++){
            firefly fy = new firefly();
            fy.number = i;
            fy.pbest = new firefly();
            for(int j = 0; j < Constant.funDims; j++){
                fy.pbest.x[j] = fy.x[j];
            }
            fireflyList.add(fy);
        }
        firstTime = Constant.iterations;
        isFirstTime = false;
        gPointNumber = 0;
    }

    /**
     * 生成种子集合
     */
    public void setSeedList() {
        seedList.clear();
        sortSwarm();
        for(int i = 0; i < Constant.NumofP; i++){
            boolean foundseed = true;
            for(int j = 0; j < seedList.size(); j++){
                if(distance(fireflyList.get(i), seedList.get(j)) < Constant.speciesRs){
                    fireflyList.get(i).seed = seedList.get(j);
                    foundseed = false;
                    break;
                }
            }
            if(foundseed){
                seedList.add(fireflyList.get(i));
                fireflyList.get(i).seed = fireflyList.get(i);
            }
        }
    }

    /**
     * 粒子朝比自己亮度高的粒子移动，移动的距离与粒子间吸引度和距离有关
     */
    public void move(){
        for(int i = 0; i < Constant.NumofP; i++){
            for(int j = 0; j < Constant.NumofP; j++){
                firefly fi = fireflyList.get(i);
                firefly fj = fireflyList.get(j);
                double r = distance(fi, fj);
                if(fi.light_intensityfun(r) < fj.light_intensityfun(r)){
                    for(int d = 0; d < Constant.funDims; d++){
                        double a = fj.attractivenessfun(r);
                        fi.x[d] = fi.x[d] + a * (fj.x[d] - fi.x[d]) + Constant.alpha * (Math.random() - 0.5);
                        if(fi.x[d] > Constant.maxRange[d]) fi.x[d] = Constant.maxRange[d];
                        if(fi.x[d] < Constant.minRange[d]) fi.x[d] = Constant.minRange[d];
                    }
                }
            }
        }
    }

    /**
     * 求两个firefly之间的距离
     */
    public double distance(firefly f1,firefly f2){
        double distance = 0;
        for(int i = 0; i < Constant.funDims; i++){
            distance = distance + Math.pow(f1.x[i] - f2.x[i], 2);
        }
        return Math.sqrt(distance);
    }

    /**
     * 对fireflyList进行降序排列
     */
    public void sortSwarm(){
        for(int i = 0; i < Constant.NumofP; i++){
            fireflyList.get(i).fitnessfun();
        }
        Collections.sort(fireflyList);
    }

    /**
     * 局域搜索函数
     */
    public void localSearch(int code) {
        for (int i = 0; i < seedList.size(); i++) {
            double[] xtemp = new double[Constant.funDims];
            firefly fytemp = new firefly(xtemp);
            if (code == 1){
                boolean search = true;
                double st = Constant.CBLS_move;
                List<firefly> listSol = new ArrayList<>();
                while (search) {
                    for(int j = 0; j < Constant.funDims; j++){
                        xtemp[j] = seedList.get(i).x[j];
                        xtemp[j] = seedList.get(i).x[j] + st;
                        listSol.add(new firefly(xtemp));
                        xtemp[j] = seedList.get(i).x[j];
                        xtemp[j] = seedList.get(i).x[j] - st;
                        listSol.add(new firefly(xtemp));
                    }
                    Collections.sort(listSol);
                    firefly fbest = listSol.get(0);
                    if (fbest.fitnessfun() > seedList.get(i).fitnessfun()) {
                        seedList.get(i).x = fbest.x;
                        seedList.get(i).fitnessfun();
                    } else {
                        search = false;
                    }
                }
            }
            if (code == 2){
                double T = 100;
                double T_Min = 1;
                double r = 0.5;
                double st = Constant.CBLS_move;
                List<firefly> listSol = new ArrayList<>();
                while (T > T_Min) {
                    for(int j = 0; j < Constant.funDims; j++){
                        xtemp[j] = seedList.get(i).x[j];
                        xtemp[j] = seedList.get(i).x[j] + st;
                        listSol.add(new firefly(xtemp));
                        xtemp[j] = seedList.get(i).x[j];
                        xtemp[j] = seedList.get(i).x[j] - st;
                        listSol.add(new firefly(xtemp));
                    }
                    Collections.sort(listSol);
                    firefly fbest = listSol.get(0);
                    double E = fbest.fitnessfun() - seedList.get(i).fitnessfun();
                    if (E > 0) {
                        seedList.get(i).x = fbest.x;
                        seedList.get(i).fitnessfun();
                    } else {
                        if (Math.exp(E / T) > Math.random()) {
                            seedList.get(i).x = fbest.x;
                            seedList.get(i).fitnessfun();
                        }
                    }
                    T = T * r;
                }
            }
            if (code == 3){
                double random = Math.random();
                if (random < 0.5) {
                    double Lambda = 1.0;
                    if (distance(seedList.get(i), seedList.get(i).pbest) < 1.1e-6) {
                        for (int index = 0; index < Constant.CBLS_step; index++) {
                            for(int j = 0; j < Constant.funDims; j++){
                                xtemp[j] = seedList.get(i).x[j];
                                xtemp[j] = xtemp[j] + Lambda * Constant.CBLS_move;
                            }
                            if(fytemp.fitnessfun() > seedList.get(i).fitnessfun()){
                                for(int j = 0; j < Constant.funDims; j++){
                                    seedList.get(i).x[j] = xtemp[j];
                                }
                            }else {
                                Lambda = Lambda * 0.5;
                            }
                        }
                    }else {
                        for (int index = 0; index < Constant.CBLS_step; index++) {
                            for (int j = 0; j < Constant.funDims; j++) {
                                xtemp[j] = seedList.get(i).x[j];
                                xtemp[j] = xtemp[j] + Constant.beta0 * (seedList.get(i).pbest.x[j] - xtemp[j]) + Constant.CBLS_move;
                            }
                            if (fytemp.fitnessfun() > seedList.get(i).fitnessfun()) {
                                for(int j = 0; j < Constant.funDims; j++) {
                                    seedList.get(i).x[j] = xtemp[j];
                                }
                            }
                        }
                    }
                }
            }
        }
        addPbestList();
    }

    /**
     * 更新每个粒子的历史最优值pbest
     */
    public void addPbestList(){
        for(int i = 0; i < Constant.NumofP; i++){
            if(fireflyList.get(i).fitnessfun() > fireflyList.get(i).pbest.fitnessfun()) {
                for(int j = 0; j < Constant.funDims; j++) {
                    fireflyList.get(i).pbest.x[j] = fireflyList.get(i).x[j];
                }
            }
        }
    }

    /**
     * 按种子给种群分子群
     */
    public void classification(){

        for(int i = 0; i < seedList.size(); i++){
            List<firefly> species = new ArrayList<>();
            for(int j = 0; j < fireflyList.size(); j++){
                if(fireflyList.get(j).seed != null && fireflyList.get(j).seed.equals(seedList.get(i))){
                    species.add(fireflyList.get(j));
                }
            }
            speciesList.add(species);
        }
    }

    /**
     * 向文件写入种群x,y坐标，便于MATLAB 读取, k 为迭代的次数
     */
    public void writeXY(int code, int k) {
        try {
            if (k == 1 || k == Constant.iterations / 2 || k == Constant.iterations) {
                FileWriter writer;
                String filepath = Constant.codeNum + "-" + code + "-" + k + ".txt";
                writer = new FileWriter(Constant.folder + filepath);
                for (int i = 0; i < Constant.NumofP; i++){
                    for (int d = 0; d < Constant.funDims; d++){
                        writer.write(fireflyList.get(i).x[d] + " ");
                    }
                    writer.write("" + fireflyList.get(i).fitnessfun());
                    writer.write("\n");
                }
                writer.flush();
                writer.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 计算全局最优点精确值的函数
     */
    public double getAccuracy(){
        List<firefly> gPointList = new ArrayList<>();
        int gPointLen = Constant.gPoints.length;
        for(int m = 0; m < gPointLen; m++){
            gPointList.add(new firefly(Constant.gPoints[m]));
        }
        double accuracy = 1.0 / gPointLen;
        double sum = 0;

        gPointNumber = 0;
        for(int i = 0; i < gPointLen; i++){
            boolean is_within = false;
            for(int j = 0; j < seedList.size(); j++){
                firefly fi = gPointList.get(i);
                firefly fj = seedList.get(j);
                if( distance(fi, fj) < Constant.DisThreshold){
                    is_within = true;
                    sum = sum + Math.abs((gPointList.get(i).fitnessfun() - seedList.get(j).fitnessfun()));
                    gPointNumber++;
                }
            }
            if(!is_within){
                sum = sum + Math.abs((gPointList.get(i).fitnessfun() - 0));
            }
        }
        accuracy = accuracy * sum;
        return accuracy;
    }

    /**
     * 设置收敛速度函数
     */
    public void setFirstTime(int k) {
        if (isFirstTime) return;
        List<firefly> gPointList = new ArrayList<>();
        int gPointLen = Constant.gPoints.length;
        for(int m = 0; m < gPointLen; m++){
            gPointList.add(new firefly(Constant.gPoints[m]));
        }
        int gPointNum = 0;
        for(int i = 0; i < gPointLen; i++){
            for(int j = 0; j < seedList.size(); j++){
                firefly fi = gPointList.get(i);
                firefly fj = seedList.get(j);
                double FitThreshold = Math.abs(fi.fitnessfun() - fj.fitnessfun());
                if(distance(fi, fj) < (Constant.speciesRs / 4.0) && FitThreshold < Constant.FitThreshold){
                    gPointNum++;
                }
            }
        }
        if (gPointNum == gPointLen) {
            firstTime = k;
            isFirstTime = true;
            /*for (int i = 0; i < seedList.size(); i++) {
                System.out.println(seedList.get(i));
            }*/
        }
    }

    /**
     * 取到计算结果的集合
     */
    public List<Double> getResultList(){
        List<Double> resultList = new ArrayList<>();
        double accuracy = getAccuracy();

        List<firefly> lPointList = new ArrayList<>();
        int lPointLen = Constant.lPoints.length;
        for(int m = 0; m < lPointLen; m++){
            lPointList.add(new firefly(Constant.lPoints[m]));
        }
        int lPointNumber = 0; // optimal points number
        for(int i = 0; i < lPointLen; i++){
            for(int j = 0;j < seedList.size(); j++){
                firefly fi = lPointList.get(i);
                firefly fj = seedList.get(j);
                double FitThreshold = Math.abs(fi.fitnessfun() - fj.fitnessfun());
                if(distance(fi, fj) < (Constant.speciesRs / 4) && FitThreshold < Constant.FitThreshold){
                    lPointNumber++;
                }
            }
        }
        double successRate = ((double)gPointNumber / Constant.gPoints.length) * 100.0;

        /*String s1 = "find global points: " + gPointNumber;
        Constant.fileChaseFW(Constant.filePath, s1);

        String s2 = "find local optimal points: " + (lPointNumber);
        Constant.fileChaseFW(Constant.filePath, s2);

        String s3 = "accuracy = " + accuracy;
        Constant.fileChaseFW(Constant.filePath, s3);

        String s4 = "Success rate = " + successRate +"%" ;
        Constant.fileChaseFW(Constant.filePath, s4);

        String s5 = "Minimum number of runs = " + this.firstTime;
        Constant.fileChaseFW(Constant.filePath, s5);*/

        resultList.add(accuracy);
        resultList.add(successRate);
        resultList.add((double)lPointNumber);
        resultList.add((double)this.firstTime);

        return resultList;
    }
}
