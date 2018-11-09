package Ncc;

import java.util.Random;
import java.util.*;
//4个最优点的坐标： (153, 178, 0.88484)，(278, 282, 0.9251)，(300, 385, 0.88532)，(340, 156, 0.88512)。
public class NccGPoint {
    public static void main(String[] args) {
        NccValue nv = new NccValue();
        for (int i = 0; i < 10; i++) {
            run(0);
            run(1);
        }
    }

    public static void run(int code) {
        long sTime = new Date().getTime();
        PointSwarm swarm = new PointSwarm();
        int[][] state = new int[543][452];
        swarm.setSeedList();
        for(int k = 1; k <= NccConstant.iterations; k++){
            if (code != 0 && k > NccConstant.iterations / 3) {
                swarm.localSearch();
            }
            swarm.classification();
            swarm.move();
            swarm.setSeedList();
        }
        for (int i = 0; i < swarm.pointList.size(); i++) {
            int row = swarm.pointList.get(i).x[0];
            int col = swarm.pointList.get(i).x[1];
            state[row][col] = 1;
        }
        int sum = 0;
        for (int i = 0; i < 543; i++) {
            for (int j = 0; j < 452; j++) {
                if (state[i][j] == 1) {
                    sum++;
                }
            }
        }
        System.out.println("sum:" + sum);
        /*System.out.println("seedList size: " + swarm.seedList.size());
        for (int i = 0; i < NccConstant.validSeedSize; i++) {
            System.out.println(swarm.seedList.get(i));
        }*/

        System.out.println("swarm.getGPointNumber():" + swarm.getGPointNumber());
        long eTime = new Date().getTime();
        String str = "run time:" + 1.0 * (eTime - sTime) / (1000 * 60) + "M";
        System.out.println(str);
    }

}

class NccConstant{
    //4个最优点的坐标： (153, 178, 0.88484)，(278, 282, 0.9251)，(300, 385, 0.88532)，(340, 156, 0.88512)。
    public static final int NumofP = 800;
    //γ为light_absorption光吸收率属于[0.1,10],its setting depends on the problem to be optimized.
    public static final double gamma = 0.9;
    //β0
    public static final double beta0 = 0.1;
    //α为Step factor步长因子属于[0,1]
    public static double alpha = 0.2; // 函数中配置

    public static int iterations = 40; //迭代次数
    public static int runTimes = 1; //独立运行次数
    public static int LS_move = 1;

    //函数相关参数
    public static int funDims = 2;
    public static double species_rs = 75;
    public static int[] maxRange = {400, 450}; // {543, 452};
    public static int[] minRange = {100, 100};
    public static int[][] gPoints = {{153,178}, {278,282}, {300,385}, {340,156}};
    public static int validSeedSize = 6;
    public static double Acc_Thr = 2.1;

    /**
     * 获得标准正太分布的随机因子
     */
    public static double getGaussian(){
        Random r = new Random();
        return r.nextGaussian();
    }
}

class Point implements Comparable{
    int[] x;
    double fitness;
    double lightIntensity;
    double attractiveness;
    Point seed;

    public Point(){
        x = new int[NccConstant.funDims];
        x[0] = new Random().nextInt(NccConstant.maxRange[0]);
        x[1] = new Random().nextInt(NccConstant.maxRange[1]);
        fitnessfun();
    }

    public Point(int[] x){
        this.x = x;
        fitnessfun();
    }

    /**
     * 计算粒子适应值函数.
     */
    public double fitnessfun(){
        fitness = NccValue.nccMatrix[x[0]][x[1]];
        return fitness;
    }

    /**
     * 相对亮度函数,粒子的亮度取决于粒子本身函数值大小和距离.两个粒子的相对亮度可以不同.
     */
    public double getlightIntensity(double r) {
        lightIntensity = fitness * Math.pow(Math.E, -NccConstant.gamma * Math.pow(r, 2));
        return lightIntensity;
    }

    /**
     * 吸引度函数,取决于两个粒子间的距离,两个粒子的相互吸引度是相等的.
     */
    public double getAttractiveness(double r){
        attractiveness = NccConstant.beta0 * Math.pow(Math.E, -NccConstant.gamma * Math.pow(r, 2));
        return attractiveness;
    }

    @Override
    public String toString() {
        String str = "(" + x[0] + ", "+ x[1] + ", " + fitness + ")";
        return str;
    }

    @Override
    public int compareTo(Object obj){
        Point point = (Point)obj;
        return new Double(point.fitnessfun()).compareTo(this.fitnessfun());
    }
}

class PointSwarm{
    List<Point> pointList = new ArrayList();
    List<Point> seedList = new ArrayList<>();
    List<List<Point>> speciesList = new ArrayList<>();

    public PointSwarm(){
        for(int i = 0; i < NccConstant.NumofP; i++){
            Point point = new Point();
            pointList.add(point);
        }
    }

    /**
     * 生成种子集合
     */
    public void setSeedList() {
        seedList.clear();
        sortSwarm();
        for(int i = 0; i < NccConstant.NumofP; i++){
            boolean foundseed = true;
            for(int j = 0; j < seedList.size(); j++){
                if(distance(pointList.get(i), seedList.get(j)) < NccConstant.species_rs){
                    pointList.get(i).seed = seedList.get(j);
                    foundseed = false;
                    break;
                }
            }
            if(foundseed){
                seedList.add(pointList.get(i));
                pointList.get(i).seed = pointList.get(i);
            }
        }
    }

    /**
     * 粒子朝比自己亮度高的粒子移动，移动的距离与粒子间吸引度和距离有关
     */
    public void move(){
        for(int n = 0; n < speciesList.size(); n++){
            for(int i = 0; i < speciesList.get(n).size(); i++){
                for(int j = 0; j < speciesList.get(n).size(); j++){
                    if(speciesList.get(n).get(i).getlightIntensity(distance(speciesList.get(n).get(i), speciesList.get(n).get(j)))
                            < speciesList.get(n).get(j).getlightIntensity(distance(speciesList.get(n).get(i), speciesList.get(n).get(j)))){

                        for(int d = 0; d < NccConstant.funDims; d++){
                            speciesList.get(n).get(i).x[d] = (int)(speciesList.get(n).get(i).x[d]
                                    + speciesList.get(n).get(j).getAttractiveness(distance(speciesList.get(n).get(i), speciesList.get(n).get(j)))
                                    * (speciesList.get(n).get(j).x[d] - speciesList.get(n).get(i).x[d])
                                    + NccConstant.alpha * (NccConstant.getGaussian()-0.5));
                            if(speciesList.get(n).get(i).x[d] > NccConstant.maxRange[d]) speciesList.get(n).get(i).x[d] = NccConstant.maxRange[d];
                            if(speciesList.get(n).get(i).x[d] < NccConstant.minRange[d]) speciesList.get(n).get(i).x[d] = NccConstant.minRange[d];
                        }
                        speciesList.get(n).get(i).fitnessfun();
                    }
                }
            }
        }
        speciesList.clear();//speciesList小种群的list,清空以便下一次迭代放入
    }

    /**
     * 局域搜索函数
     */
    public void localSearch() {
        for (int i = 0; i < seedList.size(); i++) {
            double random = Math.random();
            int[] xtemp = new int[NccConstant.funDims];
            if (random < 0.5) {
                boolean search = true;
                int st = NccConstant.LS_move;
                List<Point> listSol = new ArrayList<>();
                while (search) {
                    for(int j = 0; j < NccConstant.funDims; j++){
                        xtemp[j] = seedList.get(i).x[j] + st;
                        if (xtemp[j] > NccConstant.maxRange[j]) xtemp[j] = NccConstant.maxRange[j];
                        if (xtemp[j] < NccConstant.minRange[j]) xtemp[j] = NccConstant.minRange[j];
                        listSol.add(new Point(xtemp));
                        xtemp[j] = seedList.get(i).x[j] - st;
                        if (xtemp[j] > NccConstant.maxRange[j]) xtemp[j] = NccConstant.maxRange[j];
                        if (xtemp[j] < NccConstant.minRange[j]) xtemp[j] = NccConstant.minRange[j];
                        listSol.add(new Point(xtemp));
                    }
                    Collections.sort(listSol);
                    Point fbest = listSol.get(0);
                    if (fbest.fitnessfun() > seedList.get(i).fitnessfun()) {
                        seedList.get(i).x = fbest.x;
                        seedList.get(i).fitnessfun();
                    } else {
                        search = false;
                    }
                }
            }
        }
    }

    /**
     * 求两个firefly之间的距离
     */
    public double distance(Point p1,Point p2){
        double distance = Math.pow(p1.x[0] - p2.x[0], 2) + Math.pow(p1.x[1] - p2.x[1], 2);
        return Math.sqrt(distance);
    }

    /**
     * 对fireflyList进行降序排列
     */
    public void sortSwarm(){
        for(int i = 0; i < NccConstant.NumofP; i++){
            pointList.get(i).fitnessfun();
        }
        Collections.sort(pointList);
    }

    /**
     * 按种子给种群分子群
     */
    public void classification(){

        for(int i = 0; i < seedList.size(); i++){
            List<Point> species = new ArrayList<>();
            for(int j = 0; j < pointList.size(); j++){
                if(pointList.get(j).seed == seedList.get(i)){
                    species.add(pointList.get(j));
                }
            }
            speciesList.add(species);
        }
    }

    public int getGPointNumber(){
        List<Point> gPointList = new ArrayList<>();
        int gPointLen = NccConstant.gPoints.length;
        for(int i = 0; i < gPointLen; i++){
            gPointList.add(new Point(NccConstant.gPoints[i]));
        }
        int gPointNumber = 0;
        for(int i = 0; i < gPointLen; i++){
            for(int j = 0; j < NccConstant.validSeedSize; j++){
                if(distance(gPointList.get(i), seedList.get(j)) < NccConstant.Acc_Thr){
                    gPointNumber++;
                }
            }
        }
        return gPointNumber;
    }

}
