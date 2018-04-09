package Firefly;

import FunLibrary.FunLib;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class baseFA {

    public static void main(String[] args) {
        baseSwarm swarm= new baseSwarm();
        for(int k=1;k<=Constant.iterations;k++){
            System.out.println(k+"次*********************************");

            swarm.move();
            swarm.SortofSwarm();
			for(int i=0;i<Constant.NumofP;i++){
				System.out.print("第"+k+" 次");
				for(int d=0;d<Constant.funDims;d++){
					System.out.print(" " + swarm.listfirefly.get(i).x[d]);
				}
				System.out.println(" fitness:" + swarm.listfirefly.get(i).fitnessfun());
			}
            swarm.addListseed();
            swarm.getAccuracy();

        }
    }
}

class baseSwarm{
    List<firefly> listfirefly = new ArrayList();
    List<firefly> seedlist = new ArrayList<>();

    public baseSwarm(){
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
        seedlist.clear();
        SortofSwarm();
        for(int i=0;i<Constant.NumofP;i++){
            boolean foundseed=true;
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
        System.out.println("seedlist长度为："+seedlist.size());
    }

    //粒子朝比自己亮度高的粒子移动，移动的距离与粒子间吸引度和距离有关
    public void move(){
        for(int i=0;i<Constant.NumofP;i++){
            for(int j=0;j<Constant.NumofP;j++){
                if(listfirefly.get(i).light_intensityfun(distance(listfirefly.get(i),listfirefly.get(j)))
                        <listfirefly.get(j).light_intensityfun(distance(listfirefly.get(i),listfirefly.get(j)))){
                    for(int d=0;d<Constant.funDims;d++){
                        listfirefly.get(i).x[d]=listfirefly.get(i).x[d]
                                +listfirefly.get(j).attractivenessfun(distance(listfirefly.get(i),listfirefly.get(j)))
                                *(listfirefly.get(j).x[d]-listfirefly.get(i).x[d])
                                +Constant.alpha*(Math.random()-0.5);
                        if(listfirefly.get(i).x[d]>Constant.maxRange) listfirefly.get(i).x[d]=Constant.maxRange;
                        if(listfirefly.get(i).x[d]<Constant.minRange) listfirefly.get(i).x[d]=Constant.minRange;
                    }
                }
            }
        }
    }

    //对listfirefly进行降序排列
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
        double accuracy = 1.0/numofoptimal;
        double sum = 0;
        int num =0;
        for(int i=0;i<numofoptimal;i++){
            boolean is_within = false;
            for(int j=0;j<seedlist.size();j++){
                if(distance(optimallist.get(i),seedlist.get(j))<Constant.Acc_Thr){
                    is_within = true;
                    sum = sum + Math.abs((optimallist.get(i).fitnessfun()-seedlist.get(j).fitnessfun()));
                    num++;
                }
            }
            if(!is_within){
                sum = sum + Math.abs((optimallist.get(i).fitnessfun()-0));
            }
        }
        System.out.println("num = " + num);
        accuracy = accuracy * sum;
        System.out.println("accurary = "+accuracy);
    }
}