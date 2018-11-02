package Firefly;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class speciesSwarm extends Swarm{

    @Override
    public void setSeedList() {
        super.setSeedList();
    }

    @Override
    public void move(){
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
        }
        speciesList.clear();//speciesList小种群的list,清空以便下一次迭代放入
    }

    /**
     * 局域搜索函数 CBLS Step 和 RWDE Step
     */
    public void localSearch() {
        for (int i = 0; i < seedList.size(); i++) {
            double random = Math.random();
            if (random < 0.5) {
                double Lambda = 1.0;
                double[] xtemp = new double[Constant.funDims];
                firefly fytemp = new firefly(xtemp);
                if (distance(seedList.get(i), seedList.get(i).pbest) < 1.1e-6) {
                    //RWDE Step
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
                } else {
                    double p = Math.random();
                    if (0 < p && p < 1/3) {
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
                    if (1/3 < p && p < 2/3) {
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
                    if (2/3 < p && p < 1) {
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
}