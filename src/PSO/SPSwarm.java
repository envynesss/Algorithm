package PSO;

public class SPSwarm extends Swarm{
    @Override
    public void move() {
        for(int i = 0; i < Constant.numofP; i++){
            for (int j = 0; j < Constant.funDims; j++) {
                Particle pi = particleList.get(i);
                pi.v[j] = Constant.w * pi.v[j]
                        + Constant.c1 * Constant.ran1 * (pi.pBest.x[j] - pi.x[j])
                        + Constant.c2 * Constant.ran2 * (pi.seed.x[j] - pi.x[j]);
                if (pi.v[j] > Constant.vMax) pi.v[j] = Constant.vMax;
                if (pi.v[j] < Constant.vMin) pi.v[j] = Constant.vMin;
                pi.x[j] = pi.x[j] + pi.v[j];
                if (pi.x[j] > Constant.maxRange[j]) pi.x[j] = Constant.maxRange[j];
                if (pi.x[j] < Constant.minRange[j]) pi.x[j] = Constant.minRange[j];
            }
        }
        setPBest();
    }
}
