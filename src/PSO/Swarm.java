package PSO;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

class Swarm{
    List<Particle> particleList = new ArrayList();
    List<Particle> seedList = new ArrayList<>();
    int firstTime;
    boolean isFirstTime;
    int gPointNumber;
    Particle gBest;

    public Swarm(){
        this.particleList = new ArrayList<>();
        for(int i = 0; i < Constant.numofP; i++){
            Particle p = new Particle();
            p.pBest = new Particle();
            for(int j = 0; j < Constant.funDims; j++){
                p.pBest.x[j] = p.x[j];
            }
            particleList.add(p);
        }
        firstTime = Constant.iterations;
        isFirstTime = false;
        gPointNumber = 0;
    }

    public void move() {
        for(int i = 0; i < Constant.numofP; i++){
            for (int j = 0; j < Constant.funDims; j++) {
                Particle pi = particleList.get(i);
                pi.v[j] = Constant.w * pi.v[j] + Constant.c1 * Constant.ran1 * (pi.pBest.x[j] - pi.x[j]) + Constant.c2 * Constant.ran2 * (gBest.x[j] - pi.x[j]);
                if (pi.v[j] > Constant.vMax) pi.v[j] = Constant.vMax;
                if (pi.v[j] < Constant.vMin) pi.v[j] = Constant.vMin;
                pi.x[j] = pi.x[j] + pi.v[j];
                if (pi.x[j] > Constant.maxRange[j]) pi.x[j] = Constant.maxRange[j];
                if (pi.x[j] < Constant.minRange[j]) pi.x[j] = Constant.minRange[j];
            }
        }
        setPBest();
    }

    /**
     * 生成种子集合
     */
    public void setSeedList() {
        seedList.clear();
        sortSwarm();
        for(int i = 0; i < Constant.numofP; i++){
            boolean foundseed = true;
            for(int j = 0; j < seedList.size(); j++){
                if(distance(particleList.get(i), seedList.get(j)) < Constant.speciesRs){
                    particleList.get(i).seed = seedList.get(j);
                    foundseed = false;
                    break;
                }
            }
            if(foundseed){
                seedList.add(particleList.get(i));
                particleList.get(i).seed = particleList.get(i);
            }
        }
    }

    public void setPBest(){
        for (int i = 0; i < Constant.numofP; i++) {
            if (particleList.get(i).pBest.fitnessfun() < particleList.get(i).fitnessfun()) {
                for(int j = 0; j < Constant.funDims; j++) {
                    particleList.get(i).pBest.x[j] = particleList.get(i).x[j];
                    particleList.get(i).pBest.v[j] = particleList.get(i).v[j];
                }
            }
        }
    }

    public void getGBest(){
        gBest = particleList.get(0);
        for(int i = 0; i < Constant.numofP; i++){
            if(particleList.get(i).fitnessfun() > gBest.fitnessfun()) {
                gBest = particleList.get(i);
            }
        }
    }

    /**
     * 求两个firefly之间的距离
     */
    public double distance(Particle p1,Particle p2){
        double distance = 0;
        for(int i = 0; i < Constant.funDims; i++){
            distance = distance + Math.pow(p1.x[i] - p2.x[i], 2);
        }
        return Math.sqrt(distance);
    }

    /**
     * 对fireflyList进行降序排列
     */
    public void sortSwarm(){
        for(int i = 0; i < Constant.numofP; i++){
            particleList.get(i).fitnessfun();
        }
        Collections.sort(particleList);
    }

    /**
     * 计算全局最优点精确值的函数
     */
    public double getAccuracy(){
        List<Particle> gPointList = new ArrayList<>();
        int gPointLen = Constant.gPoints.length;
        for(int m = 0; m < gPointLen; m++){
            gPointList.add(new Particle(Constant.gPoints[m]));
        }
        double accuracy = 1.0 / gPointLen;
        double sum = 0;

        gPointNumber = 0;
        for(int i = 0; i < gPointLen; i++){
            boolean is_within = false;
            for(int j = 0; j < seedList.size(); j++){
                Particle pi = gPointList.get(i);
                Particle pj = seedList.get(j);
                if( distance(pi, pj) < Constant.DisThreshold){
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
        List<Particle> gPointList = new ArrayList<>();
        int gPointLen = Constant.gPoints.length;
        for(int m = 0; m < gPointLen; m++){
            gPointList.add(new Particle(Constant.gPoints[m]));
        }
        int gPointNum = 0;
        for(int i = 0; i < gPointLen; i++){
            for(int j = 0; j < seedList.size(); j++){
                Particle pi = gPointList.get(i);
                Particle pj = seedList.get(j);
                double FitThreshold = Math.abs(pi.fitnessfun() - pj.fitnessfun());
                if(distance(pi, pj) < (Constant.speciesRs / 4.0) && FitThreshold < Constant.FitThreshold){
                    gPointNum++;
                }
            }
        }
        if (gPointNum == gPointLen) {
            firstTime = k;
            isFirstTime = true;
        }
    }

    /**
     * 取到计算结果的集合
     */
    public List<Double> getResultList(){
        List<Double> resultList = new ArrayList<>();
        double accuracy = getAccuracy();

        List<Particle> lPointList = new ArrayList<>();
        int lPointLen = Constant.lPoints.length;
        for(int m = 0; m < lPointLen; m++){
            lPointList.add(new Particle(Constant.lPoints[m]));
        }
        int lPointNumber = 0; // optimal points number
        for(int i = 0; i < lPointLen; i++){
            for(int j = 0;j < seedList.size(); j++){
                Particle pi = lPointList.get(i);
                Particle pj = seedList.get(j);
                double FitThreshold = Math.abs(pi.fitnessfun() - pj.fitnessfun());
                if(distance(pi, pj) < (Constant.speciesRs / 4) && FitThreshold < Constant.FitThreshold){
                    lPointNumber++;
                }
            }
        }
        double successRate = ((double)gPointNumber / Constant.gPoints.length) * 100.0;

        resultList.add(accuracy);
        resultList.add(successRate);
        resultList.add((double)lPointNumber);
        resultList.add((double)this.firstTime);

        return resultList;
    }
}
