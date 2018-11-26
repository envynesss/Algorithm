package Ncc;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

class PointSwarm{
    List<Point> pointList = new ArrayList();
    List<Point> seedList = new ArrayList<>();
    List<List<Point>> speciesList = new ArrayList<>();
    int[][] state;

    public PointSwarm(){
        for(int i = 0; i < NC.NumofP; i++){
            Point point = new Point();
            point.pBest = new Point();
            for(int j = 0; j < NC.funDims; j++){
                point.pBest.x[j] = point.x[j];
            }
            pointList.add(point);
        }
        state = new int[NccValue.row][NccValue.col];
    }

    /**
     * 生成种子集合
     */
    public void setSeedList() {
        seedList.clear();
        sortSwarm();
        for(int i = 0; i < NC.NumofP; i++){
            boolean foundseed = true;
            for(int j = 0; j < seedList.size(); j++){
                if(distance(pointList.get(i), seedList.get(j)) < NC.species_rs){
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
        //标记状态
        for (int i = 0; i < pointList.size(); i++) {
            int row = pointList.get(i).x[0];
            int col = pointList.get(i).x[1];
            state[row][col]++;
        }
    }

    /**
     * 粒子朝比自己亮度高的粒子移动，移动的距离与粒子间吸引度和距离有关
     */
    public void move(){
        for(int n = 0; n < speciesList.size(); n++){
            for(int i = 0; i < speciesList.get(n).size(); i++){
                for(int j = 0; j < speciesList.get(n).size(); j++){
                    Point pi = speciesList.get(n).get(i);
                    Point pj = speciesList.get(n).get(j);
                    if(pi.getLightIntensity() < pj.getLightIntensity()){
                        for(int d = 0; d < NC.funDims; d++){
                            double beta = pj.getAttractiveness(distance(pi, pj));
                            pi.x[d] = (int)(pi.x[d] + beta * (pj.x[d] - pi.x[d]) + NC.alpha * (Math.random() - 0.5));
                            if(pi.x[d] > NC.maxRange[d]) pi.x[d] = NC.maxRange[d];
                            if(pi.x[d] < NC.minRange[d]) pi.x[d] = NC.minRange[d];
                        }
                        pi.fitnessfun();
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
            if (random < 0.9) {
                double Lambda = 1.0;
                int[] xTemp = new int[NC.funDims];
                Point pTemp = new Point(xTemp);
                if (distance(seedList.get(i), seedList.get(i).pBest) < 2) {
                    for (int index = 0; index < NC.CBLS_step; index++) {
                        for(int j = 0; j < NC.funDims; j++){
                            xTemp[j] = seedList.get(i).x[j];
                            xTemp[j] = (int)(xTemp[j] + Lambda * NC.LS_move);
                            if(xTemp[j] > NC.maxRange[j]) xTemp[j] = NC.maxRange[j];
                            if(xTemp[j] < NC.minRange[j]) xTemp[j] = NC.minRange[j];
                        }
                        if(pTemp.fitnessfun() > seedList.get(i).fitnessfun()){
                            for(int j = 0; j < NC.funDims; j++){
                                seedList.get(i).x[j] = xTemp[j];
                            }
                        }else {
                            Lambda = Lambda * 2;
                        }
                    }
                } else {
                    for (int index = 0; index < NC.CBLS_step; index++) {
                        for (int j = 0; j < NC.funDims; j++) {
                            xTemp[j] = seedList.get(i).x[j];
                            xTemp[j] = (int)(xTemp[j] + NC.beta0 * (seedList.get(i).pBest.x[j] - xTemp[j]) + NC.LS_move);
                            if(xTemp[j] > NC.maxRange[j]) xTemp[j] = NC.maxRange[j];
                            if(xTemp[j] < NC.minRange[j]) xTemp[j] = NC.minRange[j];
                        }
                        if (pTemp.fitnessfun() > seedList.get(i).fitnessfun()) {
                            for(int j = 0; j < NC.funDims; j++) {
                                seedList.get(i).x[j] = xTemp[j];
                            }
                        }
                    }
                }
            }
        }
        addPbestList();
    }

    /**
     * 除去pointList里的冗余粒子；
     */
    public void SetRedundant(int k){
        if (k > 0.8 * NC.iterations) return;
        Iterator<Point> pointListIter = pointList.iterator();
        while (pointListIter.hasNext()) {
            Point pTemp = pointListIter.next();
            if(distance(pTemp, pTemp.seed) < NC.in && pTemp != pTemp.seed)
                pointListIter.remove();
        }
        while(pointList.size() < NC.NumofP){
            pointList.add(new Point());
            pointList.get(pointList.size() - 1).pBest = pointList.get(pointList.size() - 1);

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
        for(int i = 0; i < NC.NumofP; i++){
            pointList.get(i).fitnessfun();
        }
        Collections.sort(pointList);
    }

    /**
     * 更新每个粒子的历史最优值pbest
     */
    public void addPbestList(){
        for(int i = 0; i < NC.NumofP; i++){
            if(pointList.get(i).fitnessfun() > pointList.get(i).pBest.fitnessfun()) {
                for(int j = 0; j < NC.funDims; j++) {
                    pointList.get(i).pBest.x[j] = pointList.get(i).x[j];
                }
            }
        }
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

    /**
     * 找到的最优值个数
     */
    public int getGPointNumber(){
        List<Point> gPointList = new ArrayList<>();
        int gPointLen = NC.gPoints.length;
        for(int i = 0; i < gPointLen; i++){
            gPointList.add(new Point(NC.gPoints[i]));
        }
        int gPointNumber = 0;
        for(int i = 0; i < gPointLen; i++){
            int MIN = Math.min(NC.validSeedSize, seedList.size());
            for(int j = 0; j < MIN; j++){
                if(distance(gPointList.get(i), seedList.get(j)) < NC.Acc_Thr){
                    gPointNumber++;
                }
            }
        }
        return gPointNumber;
    }

    /**
     * 子种群数（种群种子数）
     */
    public int getSeedNumber(){
        return seedList.size();
    }

    /**
     * 访问的点的总个数
     */
    public int getVisitNumber(){
        int sum = 0;
        for (int i = 0; i < NccValue.row; i++) {
            for (int j = 0; j < NccValue.col; j++) {
                if (state[i][j] > 0) {
                    sum++;
                }
            }
        }
        return sum;
    }

    /**
     * 向文件写入种群x,y坐标，便于MATLAB 读取
     * @param k 迭代的次数
     */
    public void writeXY(int k) {
        try {
            if (k == 0 || k == NC.iterations / 2 || k == NC.iterations) {
                FileWriter writer;
                int kk = 0;
                if(k == 0) kk = 1;
                if(k == NC.iterations / 2) kk = 2;
                if(k == NC.iterations) kk = 3;
                String filepath = "01-ncc-" + kk + ".txt";
                writer = new FileWriter(NC.folder + filepath);
                for (int i = 0; i < NC.NumofP; i++){
                    for (int d = 0; d < NC.funDims; d++){
                        writer.write(pointList.get(i).x[d] + " ");
                    }
                    writer.write("" + pointList.get(i).fitnessfun());
                    writer.write("\n");
                }
                writer.flush();
                writer.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
