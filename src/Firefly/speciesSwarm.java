package Firefly;

import java.util.ArrayList;
import java.util.List;

public class speciesSwarm extends Swarm{

    @Override
    public void setSeedList() {
        super.setSeedList();
    }

    /*
    粒子朝比自己亮度高的粒子移动，移动的距离与粒子间吸引度和距离有关。
     */
    @Override
    public void move(){
        //fireflyList.clear();
        for(int n = 0; n < speciesList.size(); n++){
            for(int i = 0; i < speciesList.get(n).size(); i++){
                for(int j = 0; j < speciesList.get(n).size(); j++){
                    if(speciesList.get(n).get(i).light_intensityfun(distance(speciesList.get(n).get(i), speciesList.get(n).get(j)))
                            < speciesList.get(n).get(j).light_intensityfun(distance(speciesList.get(n).get(i), speciesList.get(n).get(j)))){

                        for(int d = 0; d < Constant.funDims; d++){
                            speciesList.get(n).get(i).x[d] = speciesList.get(n).get(i).x[d]
                                    + speciesList.get(n).get(j).attractivenessfun(distance(speciesList.get(n).get(i), speciesList.get(n).get(j)))
                                    * (speciesList.get(n).get(j).x[d] - speciesList.get(n).get(i).x[d])
                                    + Constant.alpha * (Math.random()-0.5);
                            if(speciesList.get(n).get(i).x[d] > Constant.maxRange[d]) speciesList.get(n).get(i).x[d] = Constant.maxRange[d];
                            if(speciesList.get(n).get(i).x[d] < Constant.minRange[d]) speciesList.get(n).get(i).x[d] = Constant.minRange[d];
                        }
                        speciesList.get(n).get(i).fitnessfun();
                    }
                }
            }
            //fireflyList.addAll(speciesList.get(n));
        }
        speciesList.clear();//speciesList小种群的list,清空以便下一次迭代放入
    }

    /*
    局域搜索函数 CBLS Step 和 RWDE Step
     */
    public void CBLS_RWDE_fun() {

        for (int i = 0; i < seedList.size(); i++) {
            //System.out.println(i + " p...pbest...distance " + "#" + seedList.get(i).number + "..." + distance(seedList.get(i), seedList.get(i).pbest));
            double random = Math.random();
            if (random < 1.0) {
                double Lambda = 1.0;
                double[] xtemp = new double[Constant.funDims];
                firefly fytemp = new firefly(xtemp);
                if (distance(seedList.get(i), seedList.get(i).pbest) < 1.1e-6) {
                    //RWDE Step
                    //System.out.println("#" + seedList.get(i).number + " Enter the RWDE Step out");
                    for (int index = 0; index < Constant.CBLS_step; index++) {
                        for(int j = 0; j < Constant.funDims; j++){
                            xtemp[j] = seedList.get(i).x[j];
                            xtemp[j] = xtemp[j] + Lambda * Constant.CBLS_move;
                        }
                        if(fytemp.fitnessfun() > seedList.get(i).fitnessfun()){
                            for(int j = 0; j < Constant.funDims; j++){
                                seedList.get(i).x[j] = xtemp[j];
                            }
                            //System.out.println("#" + seedList.get(i).number + " Enter the RWDE Step in");
                        }else {
                            Lambda = Lambda * 0.5;
                        }
                    }
                } else {
                    //CBLS Step
                    //System.out.println("#" + seedList.get(i).number + " Enter the CBLS Step out");
                    for (int index = 0; index < Constant.CBLS_step; index++) {
                        for(int j = 0; j < Constant.funDims; j++) {
                            xtemp[j] = seedList.get(i).x[j];
                            xtemp[j] = xtemp[j] + Constant.beta0 * (seedList.get(i).pbest.x[j] - xtemp[j]) + Constant.CBLS_move;
                        }
                        if (fytemp.fitnessfun() > seedList.get(i).fitnessfun()) {
                            for(int j = 0; j < Constant.funDims; j++) {
                                seedList.get(i).x[j] = xtemp[j];
                            }
                            //System.out.println("#" + seedList.get(i).number + " Enter the CBLS Step in");
                        }
                    }
                }
            }
        }
        addPbestList();
    }

    /*
    更新每个粒子的历史最优值pbest
     */
    public void addPbestList(){
        for(int i = 0; i < Constant.NumofP; i++){
            //System.out.println("addPbestList() out");
            if(fireflyList.get(i).fitnessfun() > fireflyList.get(i).pbest.fitnessfun()) {
                for(int j = 0; j < Constant.funDims; j++) {
                    fireflyList.get(i).pbest.x[j] = fireflyList.get(i).x[j];
                }
                //System.out.println("addPbestList() in #" + listfirefly.get(i).bianhao);
            }
        }
    }

    /*
   按种子给种群分子群
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
}