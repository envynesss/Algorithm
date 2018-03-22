package Firefly;

import FunLibrary.FunLib;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class speciesFA {
    public static void main(String[] args) {
        speciesSwarm swarm= new speciesSwarm();
        for(int k=0;k<Constant.iterations;k++){
            swarm.addListseed();
            swarm.classification();
            swarm.move();
            swarm.speciesList.clear();
            swarm.SortofSwarm();
            for(int i=0;i<Constant.NumofP;i++){
                System.out.print("第 "+k+"次");
                for(int d=0;d<Constant.funDims;d++){
                    System.out.print(" " + swarm.listfirefly.get(i).x[d]);
                }
                System.out.println(" fitness:" + swarm.listfirefly.get(i).fitnessfun());
            }
            swarm.getAccuracy();
            swarm.seedlist.clear();
        }
    }
}

class speciesSwarm{
    List<firefly> listfirefly = new ArrayList();
    List<firefly> seedlist = new ArrayList<>();
    List<List<firefly>> speciesList = new ArrayList<>();

    public speciesSwarm(){
        for(int i=0;i<Constant.NumofP;i++){
            listfirefly.add(new firefly());
        }
    }

    public double distance(firefly f1,firefly f2){
        double distance = 0;
        for(int i=0;i<Constant.funDims;i++){
            distance = distance + Math.pow(f1.x[i]-f2.x[i],2);
        }
        return Math.sqrt(distance);
    }

    //生成种子集.
    public void addListseed(){
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
        for(int i=0;i<seedlist.size();i++){
            System.out.print(seedlist.get(i).fitnessfun()+" ");
        }
        System.out.println();
        System.out.println("seedlist长度为 ："+seedlist.size());
    }

    //按种子给种群分子群
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

    //粒子朝比自己亮度高的粒子移动，移动的距离与粒子间吸引度和距离有关。
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
                    }
                }
            }
            listfirefly.addAll(speciesList.get(n));
        }
    }

    //对listfirefly进行降序排列。
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
        optimallist.add(new firefly(FunLib.f3_gpoint_1));
        optimallist.add(new firefly(FunLib.f3_gpoint_2));
        optimallist.add(new firefly(FunLib.f3_gpoint_3));
        optimallist.add(new firefly(FunLib.f3_gpoint_4));
        optimallist.add(new firefly(FunLib.f3_gpoint_5));
        int numofoptimal = optimallist.size();
        double accurary = 1.0/numofoptimal;
        System.out.println("accurary = "+accurary);
        double sum = 0;
        int num =0;
        for(int i=0;i<numofoptimal;i++){
            for(int j=0;j<seedlist.size();j++){
                if(distance(optimallist.get(i),seedlist.get(j))<0.05){
                    sum = sum + Math.abs((optimallist.get(i).fitnessfun()-seedlist.get(j).fitnessfun()));
                    System.out.println("sum = "+sum);
                    num++;
                }

            }
        }
        System.out.println("num = " + num);
        accurary = accurary * sum;
        System.out.println("accurary = "+accurary);
    }
}