package Firefly;

import java.util.*;
import java.io.*;

public class Swarm {
    List<firefly> fireflyList = new ArrayList();
    List<firefly> seedList = new ArrayList<>();
    List<List<firefly>> speciesList = new ArrayList<>();
    int firstTime;
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
                if(distance(fireflyList.get(i), seedList.get(j)) < Constant.species_rs){
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
                if(fireflyList.get(i).light_intensityfun(distance(fireflyList.get(i), fireflyList.get(j)))
                        < fireflyList.get(j).light_intensityfun(distance(fireflyList.get(i), fireflyList.get(j)))){
                    for(int d = 0; d < Constant.funDims; d++){
                        fireflyList.get(i).x[d] = fireflyList.get(i).x[d]
                                + fireflyList.get(j).attractivenessfun(distance(fireflyList.get(i), fireflyList.get(j)))
                                * (fireflyList.get(j).x[d] - fireflyList.get(i).x[d])
                                + Constant.alpha * (Constant.getGaussian() - 0.5);
                        if(fireflyList.get(i).x[d] > Constant.maxRange[d]) fireflyList.get(i).x[d] = Constant.maxRange[d];
                        if(fireflyList.get(i).x[d] < Constant.minRange[d]) fireflyList.get(i).x[d] = Constant.minRange[d];
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
     * 向文件写入种群x,y坐标，便于MATLAB 读取
     * @param k 迭代的次数
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
     * @return 返回计算的精确值
     */
    public double getAccuracy(){
        List<firefly> gPointList = new ArrayList<>();
        int gPointLen = Constant.gpoints.length;
        for(int m = 0; m < gPointLen; m++){
            gPointList.add(new firefly(Constant.gpoints[m]));
        }
        double accuracy = 1.0 / gPointLen;
        double sum = 0;

        gPointNumber = 0;
        for(int i = 0; i < gPointLen; i++){
            boolean is_within = false;
            for(int j = 0; j < seedList.size(); j++){
                if(distance(gPointList.get(i), seedList.get(j)) < Constant.Acc_Thr){
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
     * 取到计算结果的集合
     * @return 结果的集合
     */
    public List<Double> getResultList(){
        List<Double> resultList = new ArrayList<>();
        double accuracy = getAccuracy();

        List<firefly> optimalPointList = new ArrayList<>();
        int oPointLen = Constant.optimalpoints.length;
        for(int m = 0; m < oPointLen; m++){
            optimalPointList.add(new firefly(Constant.optimalpoints[m]));
        }
        int oPointNumber = 0; // optimal points number
        for(int i = 0;i < oPointLen; i++){
            for(int j = 0;j < seedList.size(); j++){
                if(distance(optimalPointList.get(i), seedList.get(j)) < Constant.Acc_Thr){
                    oPointNumber++;
                }
            }
        }

        int lPointNumber = oPointNumber - gPointNumber;
        double successRate = ((double)gPointNumber / Constant.gpoints.length) * 100.0;

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
