package Firefly;

import java.util.ArrayList;
import java.util.List;

public class speciesNSwarm extends speciesSwarm{
    @Override
    public void setSeedList() {
        seedList.clear();
        int rs = Constant.NumofP / (Constant.optimalpoints.length * 3);
        int unprocessed_num = fireflyList.size();
        for (int i = 0; i < fireflyList.size(); i++) {
            fireflyList.get(i).isProcessed = false;
        }
        while(unprocessed_num > 0){
            boolean foundseed = true;
            firefly best_firefly = best_firefly(fireflyList);
            for(int j = 0; j < seedList.size(); j++){
                if(distance(best_firefly, seedList.get(j)) < Constant.species_rs){
                    best_firefly.x = new firefly().x;
                    best_firefly.fitnessfun();
                    best_firefly.isProcessed = true;
                    unprocessed_num--;
                    foundseed = false;
                    break;
                }
            }
            if(foundseed){
                seedList.add(best_firefly);
                int k = best_firefly.number;
                List<Integer> neibor_num_list;
                neibor_num_list = Constant.get_neibor_num_list(Constant.NumofP, k, rs);
                for (int i = 0; i < neibor_num_list.size(); i++) {
                    for (int j = 0; j < fireflyList.size(); j++) {
                        if (neibor_num_list.get(i) == fireflyList.get(j).number && fireflyList.get(j).isProcessed == false) {
                            fireflyList.get(j).seed = best_firefly;
                            fireflyList.get(j).isProcessed = true;
                            unprocessed_num--;
                        }
                    }
                }
            }
        }
        /*System.out.print("seedlist length:"+seedList.size()+ " seeds：");
        for(int i = 0; i < seedList.size(); i++){
            System.out.print( "#" + seedList.get(i).number + " " + seedList.get(i).fitnessfun() + ", ");
        }
        System.out.println();*/
    }

    /**
     * 选取最好的未处理的粒子
     */
    public firefly best_firefly(List<firefly> listfirefly) {
        List<firefly> unprocessed_listfirefly = new ArrayList<>();
        for (int i = 0; i < listfirefly.size(); i++) {
            if (listfirefly.get(i).isProcessed == false) {
                unprocessed_listfirefly.add(listfirefly.get(i));
            }
        }
        firefly best_firefly = unprocessed_listfirefly.get(0);
        for (int i = 1; i < unprocessed_listfirefly.size(); i++) {
            if (unprocessed_listfirefly.get(i).fitnessfun() > best_firefly.fitnessfun()) {
                best_firefly = unprocessed_listfirefly.get(i);
            }
        }
        return best_firefly;
    }
}
