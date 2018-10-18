package Firefly;

public class Main {
    public static void main(String[] args) {
        //runBaseSwarm();
        //runSpeciesSwarm();
        runSpeciesSwarm_number();
    }

    public static void runBaseSwarm(){
        baseSwarm swarm = new baseSwarm();
        for(int k = 1; k <= Constant.iterations; k++){
            System.out.println(k + "次*********************************");

            swarm.move();
            swarm.sortSwarm();
			/*for(int i=0;i<Constant.NumofP;i++){
				System.out.print("第"+k+" 次");
				for(int d=0;d<Constant.funDims;d++){
					System.out.print(" " + swarm.listfirefly.get(i).x[d]);
				}
				System.out.println(" fitness:" + swarm.listfirefly.get(i).fitnessfun());
			}*/
            swarm.setSeedList();
            swarm.getAccuracy();
        }
    }

    public static void runSpeciesSwarm() {
        speciesSwarm swarm= new speciesSwarm();
        swarm.setSeedList();//生成第一代seed
        for(int k = 1; k <= Constant.iterations; k++){
            System.out.println("****************"+ k +" time****************");
            swarm.CBLS_RWDE_fun();
            swarm.classification();
            swarm.move();
            swarm.sortSwarm();
            System.out.println(swarm.fireflyList.size());
            for(int i = 0; i < Constant.NumofP; i++){
                System.out.println(i);
                System.out.print("第 "+k+"次 ");
                for(int d = 0; d < Constant.funDims; d++){
                    System.out.print("x[" + d + "] = " + swarm.fireflyList.get(i).x[d] +" ");
                }
                System.out.println(" fitness:" + swarm.fireflyList.get(i).fitnessfun());
            }
            swarm.setSeedList();//更新seed
            swarm.getAccuracy();
        }
    }

    public static void runSpeciesSwarm_number() {
        speciesSwarm_number swarm= new speciesSwarm_number();
        swarm.setSeedList();//生成第一代seed
        for(int k = 1; k <= Constant.iterations; k++){
            System.out.println("****************"+ k +" time****************");
            swarm.CBLS_RWDE_fun();
            swarm.classification();
            swarm.move();
            swarm.sortSwarm();
            System.out.println(swarm.fireflyList.size());
            for(int i = 0; i < Constant.NumofP; i++){
                System.out.println(i);
                System.out.print("第 "+k+"次 ");
                for(int d = 0; d < Constant.funDims; d++){
                    System.out.print("x[" + d + "] = " + swarm.fireflyList.get(i).x[d] +" ");
                }
                System.out.println(" fitness:" + swarm.fireflyList.get(i).fitnessfun());
            }
            swarm.setSeedList();//更新seed
            swarm.getAccuracy();
        }
    }
}
