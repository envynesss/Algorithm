package Firefly;

public class Main {
    public static void main(String[] args) {
        int times = 10;
        double[] array = new double[times];

        /*for (int i = 0; i < times; i++) {
            array[i] = runBaseSwarm();
        }
        System.out.println("\n独立运行" + times + "次后 accurary 平均值为：" + Constant.average(array));*/

        for (int i = 0; i < times; i++) {
            array[i] = runSpeciesSwarm();
        }

        System.out.println("\n独立运行" + times + "次后accurary平均值为：" + Constant.average(array));

        /*for (int i = 0; i < times; i++) {
            array[i] = runSpeciesSwarm_number();
        }
        System.out.println("\n独立运行" + times + "次后accurary平均值为：" + Constant.average(array));*/
    }

    public static double runBaseSwarm(){
        baseSwarm swarm = new baseSwarm();
        for(int k = 1; k <= Constant.iterations; k++){
            //System.out.println(k + "次*********************************");
            swarm.move();
            swarm.sortSwarm();
			/*for(int i = 0; i < Constant.NumofP; i++){
				System.out.print("第" + k + " 次");
				for(int d = 0; d < Constant.funDims; d++){
					System.out.print(" " + swarm.fireflyList.get(i).x[d]);
				}
				System.out.println(" fitness:" + swarm.fireflyList.get(i).fitnessfun());
			}*/
            swarm.setSeedList();
        }
        return swarm.getAccuracy();
    }

    public static double runSpeciesSwarm() {
        speciesSwarm swarm= new speciesSwarm();
        swarm.setSeedList();
        for(int k = 1; k <= Constant.iterations; k++){
            //swarm.CBLS_RWDE_fun();
            swarm.classification();
            swarm.move();
            swarm.setSeedList();
        }
        for (int i = 0; i < swarm.seedList.size(); i++) {
            System.out.println(swarm.seedList.get(i));
        }
        return swarm.getAccuracy();
    }

    public static double runSpeciesSwarm_number(){
        speciesNSwarm swarm= new speciesNSwarm();
        swarm.setSeedList();
        for(int k = 1; k <= Constant.iterations; k++){
            //swarm.CBLS_RWDE_fun();
            swarm.classification();
            swarm.move();
            swarm.setSeedList();
        }
        return swarm.getAccuracy();
    }
}
