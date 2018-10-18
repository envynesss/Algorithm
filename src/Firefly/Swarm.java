package Firefly;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Swarm {
    List<firefly> fireflyList = new ArrayList();
    List<firefly> seedList = new ArrayList<>();
    List<List<firefly>> speciesList = new ArrayList<>();

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
    }

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
        System.out.println("seedList长度为：" + seedList.size());
        for(int i = 0; i < seedList.size(); i++){
            System.out.print( "#" + seedList.get(i).number + ":" + seedList.get(i).fitnessfun() + ",");
        }
        System.out.println();

    }

    /*
   粒子朝比自己亮度高的粒子移动，移动的距离与粒子间吸引度和距离有关
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
                                + Constant.alpha * (Math.random() - 0.5);
                        if(fireflyList.get(i).x[d] > Constant.maxRange) fireflyList.get(i).x[d] = Constant.maxRange;
                        if(fireflyList.get(i).x[d] < Constant.minRange) fireflyList.get(i).x[d] = Constant.minRange;
                    }
                }
            }
        }
    }

    /*
    * 求两个firefly之间的距离
    * */
    public double distance(firefly f1,firefly f2){
        double distance = 0;
        for(int i = 0; i < Constant.funDims; i++){
            distance = distance + Math.pow(f1.x[i] - f2.x[i], 2);
        }
        return Math.sqrt(distance);
    }

    /*
   对fireflyList进行降序排列
    */
    public void sortSwarm(){
        for(int i = 0; i < Constant.NumofP; i++){
            fireflyList.get(i).fitnessfun();
        }
        Collections.sort(fireflyList);
    }

    /**
     * 计算算法精确值函数
     */
    public void getAccuracy(){
        List<firefly> optimalList = new ArrayList<>();
        for(int m = 0; m < Constant.optimalpoints.length; m++){
            optimalList.add(new firefly(Constant.optimalpoints[m]));
        }
        int numOptimal = optimalList.size();
        double accuracy = 1.0 / numOptimal;
        double sum = 0;
        int num = 0;
        for(int i = 0;i < numOptimal; i++){
            boolean is_within = false;
            for(int j = 0;j < seedList.size(); j++){
                if(distance(optimalList.get(i), seedList.get(j)) < Constant.Acc_Thr){
                    is_within = true;
                    sum = sum + Math.abs((optimalList.get(i).fitnessfun() - seedList.get(j).fitnessfun()));
                    num++;
                }
            }
            if(!is_within){
                sum = sum + Math.abs((optimalList.get(i).fitnessfun() - 0));
            }
        }
        System.out.println("num = " + num);
        accuracy = accuracy * sum;
        System.out.println("accurary = " + accuracy);
    }
}
