package Firefly;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class speciesFA {
    public static void main(String[] args) {
        speciesSwarm swarm= new speciesSwarm();
        swarm.addListseed();//生成第一代seed
        for(int k=1;k<=Constant.iterations;k++){
            System.out.println("****************"+ k +" time****************");
            swarm.CBLS_RWDE_fun();
            swarm.classification();
            swarm.move();
            swarm.SortofSwarm();
            /*for(int i=0;i<Constant.NumofP;i++){
                System.out.print("第 "+k+"次 ");
                for(int d=0;d<Constant.funDims;d++){
                    System.out.print("x[" + d + "] = " + swarm.listfirefly.get(i).x[d] +" ");
                }
                System.out.println(" fitness:" + swarm.listfirefly.get(i).fitnessfun());
            }*/
            swarm.addListseed();//更新seed
            swarm.getAccuracy();
        }
    }
}

class speciesSwarm{

    List<firefly> listfirefly = new ArrayList();
    List<firefly> seedlist = new ArrayList<>();
    List<List<firefly>> speciesList = new ArrayList<>();

    public speciesSwarm(){
        for(int i=0;i<Constant.NumofP;i++){
            firefly fy = new firefly();
            fy.bianhao = i;
            fy.pbest = new firefly();
            for(int j=0;j<Constant.funDims;j++){
                fy.pbest.x[j] = fy.x[j];
            }
            listfirefly.add(fy);
        }
    }

    public double distance(firefly f1,firefly f2){
        double distance = 0;
        for(int i=0;i<Constant.funDims;i++){
            distance = distance + Math.pow(f1.x[i]-f2.x[i],2);
        }
        return Math.sqrt(distance);
    }

    /*
    生成种子集.
     */
    public void addListseed(){
        seedlist.clear();
        SortofSwarm();
        for(int i=0;i<Constant.NumofP;i++){
            boolean foundseed = true;
            for(int j=0;j<seedlist.size();j++){
                if(distance(listfirefly.get(i),seedlist.get(j))<Constant.species_rs){
                    listfirefly.get(i).seed = seedlist.get(j);
                    foundseed=false;
                    break;
                }
            }
            if(foundseed){
                seedlist.add(listfirefly.get(i));
                listfirefly.get(i).seed = listfirefly.get(i);
            }
        }
        System.out.print("seedlist length:"+seedlist.size()+ " seeds：");
        for(int i=0;i<seedlist.size();i++){
            System.out.print( "#" + seedlist.get(i).bianhao + " " + seedlist.get(i).fitnessfun() + ", ");
        }
        System.out.println();
    }

    /*
    局域搜索函数 CBLS Step 和 RWDE Step
     */
    public void CBLS_RWDE_fun() {

        for (int i = 0; i < seedlist.size(); i++) {
            System.out.println(i + " p...pbest...distance " + "#" + seedlist.get(i).bianhao + "..." + distance(seedlist.get(i), seedlist.get(i).pbest));
            double random = Math.random();
            if (random < 1.0) {
                double Lambda = 1.0;
                double[] xtemp = new double[Constant.funDims];
                firefly fytemp = new firefly(xtemp);
                if (distance(seedlist.get(i), seedlist.get(i).pbest) < 1.1e-6) {
                    //RWDE Step
                    System.out.println("#" + seedlist.get(i).bianhao + " Enter the RWDE Step out");
                    for (int index = 0; index < Constant.CBLS_step; index++) {
                        for(int j=0;j<Constant.funDims;j++){
                            xtemp[j] = seedlist.get(i).x[j];
                            xtemp[j] = xtemp[j] + Lambda * Constant.CBLS_move;
                        }
                        if(fytemp.fitnessfun()>seedlist.get(i).fitnessfun()){
                            for(int j=0;j<Constant.funDims;j++){
                                seedlist.get(i).x[j] = xtemp[j];
                            }
                            System.out.println("#" + seedlist.get(i).bianhao + " Enter the RWDE Step in");
                        }else {
                            Lambda = Lambda * 0.5;
                        }
                    }
                } else {
                    //CBLS Step
                    System.out.println("#" + seedlist.get(i).bianhao + " Enter the CBLS Step out");
                    for (int index = 0; index < Constant.CBLS_step; index++) {
                        for(int j = 0; j < Constant.funDims; j++) {
                            xtemp[j] = seedlist.get(i).x[j];
                            xtemp[j] = xtemp[j] + Constant.beta0 * (seedlist.get(i).pbest.x[j] - xtemp[j]) + Constant.CBLS_move;
                        }
                        if (fytemp.fitnessfun()>seedlist.get(i).fitnessfun()) {
                            for(int j = 0; j < Constant.funDims; j++) {
                                seedlist.get(i).x[j] = xtemp[j];
                            }
                            System.out.println("#" + seedlist.get(i).bianhao + " Enter the CBLS Step in");
                        }
                    }
                }
            }
        }
        addPbestList();
    }

    /*
    按种子给种群分子群
     */
    public void classification(){

        for(int i=0;i<seedlist.size();i++){
            List<firefly> species = new ArrayList<>();
            for(int j=0;j<listfirefly.size();j++){
                if(listfirefly.get(j).seed.equals(seedlist.get(i))){
                    species.add(listfirefly.get(j));
                }
            }
            speciesList.add(species);
        }
    }

    /*
    粒子朝比自己亮度高的粒子移动，移动的距离与粒子间吸引度和距离有关。
     */
    public void move(){
        listfirefly.clear();
        for(int n=0;n<speciesList.size();n++){
            for(int i=0;i<speciesList.get(n).size();i++){
                for(int j=0;j<speciesList.get(n).size();j++){
                    if(speciesList.get(n).get(i).light_intensityfun(distance(speciesList.get(n).get(i),speciesList.get(n).get(j)))
                            <speciesList.get(n).get(j).light_intensityfun(distance(speciesList.get(n).get(i),speciesList.get(n).get(j)))){

                        for(int d=0;d<Constant.funDims;d++){
                            speciesList.get(n).get(i).x[d]=speciesList.get(n).get(i).x[d]
                                    +speciesList.get(n).get(j).attractivenessfun(distance(speciesList.get(n).get(i),speciesList.get(n).get(j)))
                                    *(speciesList.get(n).get(j).x[d]-speciesList.get(n).get(i).x[d])
                                    +Constant.alpha*(Math.random()-0.5);
                            if(speciesList.get(n).get(i).x[d]>Constant.maxRange) speciesList.get(n).get(i).x[d]=Constant.maxRange;
                            if(speciesList.get(n).get(i).x[d]<Constant.minRange) speciesList.get(n).get(i).x[d]=Constant.minRange;
                        }
                        speciesList.get(n).get(i).fitnessfun();
                    }
                }
            }
            listfirefly.addAll(speciesList.get(n));
        }
        speciesList.clear();//speciesList小种群的list,清空以便下一次迭代放入
        //addPbestList();
    }

    /*
    更新每个粒子的历史最优值pbest
     */
    public void addPbestList(){
        for(int i=0;i<Constant.NumofP;i++){
            //System.out.println("addPbestList() out");
            if(listfirefly.get(i).fitnessfun() > listfirefly.get(i).pbest.fitnessfun()) {
                for(int j = 0; j < Constant.funDims; j++) {
                    listfirefly.get(i).pbest.x[j] = listfirefly.get(i).x[j];
                }
                //System.out.println("addPbestList() in #" + listfirefly.get(i).bianhao);
            }
        }
    }

    /*
    对listfirefly进行降序排列。
     */
    public void SortofSwarm(){
        for(int i=0;i<Constant.NumofP;i++){
            listfirefly.get(i).fitnessfun();
        }
        Collections.sort(listfirefly);
    }

    /**
     * 计算算法精确值函数
     */
    public void getAccuracy(){
        List<firefly> optimallist = new ArrayList<>();
        for(int m=0;m<Constant.optimalpoints.length;m++){
            optimallist.add(new firefly(Constant.optimalpoints[m]));
        }
        int numofoptimal = optimallist.size();
        double accuracy = 1.0 / numofoptimal;
        double sum = 0;
        int num = 0;
        for (int i = 0; i < numofoptimal; i++) {
            boolean is_within = false;
            for(int j = 0; j < seedlist.size(); j++){
                if(distance(optimallist.get(i), seedlist.get(j)) < Constant.Acc_Thr) {
                    is_within = true;
                    sum = sum + Math.abs((optimallist.get(i).fitnessfun() - seedlist.get(j).fitnessfun()));
                    num++;
                }
            }
            if (!is_within) {
                sum = sum + Math.abs((optimallist.get(i).fitnessfun() - 0));
            }
        }
        System.out.print("num = " + num);
        accuracy = accuracy * sum;
        System.out.println(" accurary = " + accuracy);
    }
}