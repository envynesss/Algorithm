package Firefly;

public class speciesSwarm extends Swarm{

    @Override
    public void move(){
        for(int n = 0; n < speciesList.size(); n++){
            for(int i = 0; i < speciesList.get(n).size(); i++){
                for(int j = 0; j < speciesList.get(n).size(); j++){
                    firefly fi = speciesList.get(n).get(i);
                    firefly fj = speciesList.get(n).get(j);
                    double r = distance(fi, fj);
                    if(fi.light_intensityfun(r) < fj.light_intensityfun(r)){
                        for(int d = 0; d < Constant.funDims; d++){
                            fi.x[d] = fi.x[d] + fj.attractivenessfun(r) * (fj.x[d] - fi.x[d]) + Constant.alpha * (Math.random()-0.5);
                            if(fi.x[d] > Constant.maxRange[d]) fi.x[d] = Constant.maxRange[d];
                            if(fi.x[d] < Constant.minRange[d]) fi.x[d] = Constant.minRange[d];
                        }
                        fi.fitnessfun();
                    }
                }
            }
        }
        speciesList.clear();//speciesList小种群的list,清空以便下一次迭代放入
    }
}