package FireWorks;

import java.util.*;

public class TestFireworks {
    public static void main(String[] args) {
        Fireworks fworks = new Fireworks();
        //1.将初始的烟花的数组类型的点转化成List类型的点，并存入到fireworks池里
        for(int k=1;k<21;k++){
            List<Point> pointslist = new ArrayList(Arrays.asList(fworks.getFireworks()));
            Fireworks.addfireworks(pointslist);
            //2.将初始烟花爆炸后产生的火花存入到fireworks池里
            for(int i=0;i<fworks.getFireworks().length;i++){
                //每个点的爆炸半径
                fworks.Explosion_radius(fworks.getFireworks()[i]);
                //每个点爆炸产生的火花数目
                fworks.SparksNumber(fworks.getFireworks()[i]);
                //每个点产生爆炸火花的过程
                fworks.FireworksFly(fworks.getFireworks()[i]);
                //把每个点产生的所有爆炸火花存入到fireworks池里
                Fireworks.addfireworks(fworks.getFireworks()[i].getListfires());
            }
            //3.将高斯爆炸产生的烟花存入到fireworks池里
            Set<Integer> hs = Constant.randomZ(Constant.numOfN,Constant.m);
            Iterator<Integer> it = hs.iterator();
            while(it.hasNext()){
                int j = it.next();
                fworks.FireworksGaussFly(fworks.getFireworks()[j]);
                Fireworks.addfireworks(fworks.getFireworks()[j].getListguassfires());
            }
            Point pbest = Collections.min(Fireworks.getAllfireworks());
            System.out.println("pbest:"+pbest);
            Set<Point> otherPointset = new HashSet();
            otherPointset.add(pbest);
            for(int i=0;i<Fireworks.getAllfireworks().size();i++){
                fworks.setDistanceToAll(Fireworks.getAllfireworks().get(i));
            }
            double Cumulative_probability = 0.0;
            for(int i=0;i<Fireworks.getAllfireworks().size();i++){
                fworks.setfireworkProbability(Fireworks.getAllfireworks().get(i));
                Cumulative_probability = Cumulative_probability + Fireworks.getAllfireworks().get(i).getProbability();
                Fireworks.getAllfireworks().get(i).setCumulative_probability(Cumulative_probability);
            }
            while(otherPointset.size()<Constant.numOfN){
                double random = Math.random();
                for(int i=0;i<Fireworks.getAllfireworks().size()-1;i++){
                    double a = Fireworks.getAllfireworks().get(0).getCumulative_probability();
                    double b = Fireworks.getAllfireworks().get(i).getCumulative_probability();
                    double c = Fireworks.getAllfireworks().get(i+1).getCumulative_probability();
                    if(random<Fireworks.getAllfireworks().get(0).getCumulative_probability()){
                        otherPointset.add(Fireworks.getAllfireworks().get(0));
                        break;
                    }
                    boolean bln = random>Fireworks.getAllfireworks().get(i).getCumulative_probability()
                            && random<Fireworks.getAllfireworks().get(i+1).getCumulative_probability();
                    if(bln){
                        otherPointset.add(Fireworks.getAllfireworks().get(i+1));
                        break;
                    }
                }
            }
            Point[] newfireworks = (Point[])otherPointset.toArray(new Point[Constant.numOfN]);
            fworks.setFireworks(newfireworks);
            System.out.println("第" + k + "次结束时" + Arrays.toString(fworks.getFireworks()));
        }

    }
}
/**
 * 常量类
 */
class Constant{
    //维度
    public static int numOfD = 2;
    //种群粒子数量
    public static int numOfN = 30;
    //爆炸半径参数
    public static int A = 40;
    //爆炸火花数目参数
    public static int M = 50;
    public static double a = 0.04;
    public static double b = 0.8;
    //上下界限
    public static double Xmax = 5;
    public static double Xmin = -Xmax;
    //高斯火花个数参数
    public static int m = 5;

    //在一定范围(0~N)内随机生成z个不重复正数
    public static Set<Integer> randomZ(int N,int z){
        Set<Integer> hs = new HashSet<>();
        Random random = new Random();
        while(hs.size()<z){
            hs.add(random.nextInt((int)N));
        }
        return hs;
    }
}

/**
 * 烟花点类
 */
class Point implements Comparable, Cloneable{
    public double x[];
    private double fitness;
    private double r;
    private int firesnum;
    private double distanceToAll;
    private double probability;
    //累积概率
    private double cumulative_probability;
    private List<Point> listfires = new ArrayList();
    private List<Point> listguassfires = new ArrayList();
    public Point(){
        x = new double[Constant.numOfD];
        for(int i=0;i<x.length;i++){
            x[i] = Math.random()*Constant.Xmax*2-Constant.Xmax;
        }
        fitness = fun();
    }
    public double fun(){
        fitness = 0;
        //Sphere函数
        /*for(int i=0;i<x.length;i++){
            fitness = fitness + Math.pow(x[i], 2);
        }*/
        //萤火虫测试函数
        fitness=-(Math.pow(Math.E,-Math.pow(x[0]-4,2)-Math.pow(x[1]-4, 2))
                +Math.pow(Math.E,-Math.pow(x[0]+4,2)-Math.pow(x[1]-4, 2))
                +2*(Math.pow(Math.E,-Math.pow(x[0],2)-Math.pow(x[1], 2))+Math.pow(Math.E,-Math.pow(x[0],2)-Math.pow(x[1]+4, 2))));
        return fitness;
    }
    public double[] getX() {
        return x;
    }

    public void setX(double[] x) {
        this.x = x;
    }

    public double getFitness() {
        return fitness;
    }
    public void setR(double r) {
        this.r = r;
    }
    public double getR() {
        return r;
    }
    public int getFiresnum() {
        return firesnum;
    }
    public void setFiresnum(int firesnum) {
        this.firesnum = firesnum;
    }

    public double getDistanceToAll() {
        return distanceToAll;
    }

    public void setDistanceToAll(double distanceToAll) {
        this.distanceToAll = distanceToAll;
    }

    public double getProbability() {
        return probability;
    }

    public void setProbability(double probability) {
        this.probability = probability;
    }

    public double getCumulative_probability() {
        return cumulative_probability;
    }

    public void setCumulative_probability(double cumulative_probability) {
        this.cumulative_probability = cumulative_probability;
    }

    public List<Point> getListfires() {
        return listfires;
    }
    public List<Point> getListguassfires() {
        return listguassfires;
    }
    public String toString(){
        /*String str = "(";
        for(int i=0;i<Constant.numOfD;i++){
            str = str + " x[" + i + "]=" + x[i];
        }
        str = str + " fitness=" + fitness + ")";
        return str;*/
        return fitness +"";
    }
    //按照fun()大小升序排列list。
    @Override
    public int compareTo(Object obj) {
        Point ps = (Point)obj;
        //System.out.println("compareTo(Object obj)");
        return new Double(this.fun()).compareTo(ps.fun());
    }
    @Override
    public Object clone() {
        Point point = null;
        try{
            point = (Point) super.clone();
        }catch(CloneNotSupportedException e) {
            e.printStackTrace();
        }
        return point;
    }
}

/**
 * 烟花种群类
 */
class Fireworks{
    private Point[] fireworks = new Point[Constant.numOfN];
    private static List<Point> allfireworks = new ArrayList();

    public static void addfireworks(List<Point> list){
        allfireworks.addAll(list);
    }
    public static List<Point> getAllfireworks(){
        return allfireworks;
    }
    public Fireworks(){
        for(int i=0;i<fireworks.length;i++){
            fireworks[i] = new Point();
        }
    }
    public Point[] getFireworks() {
        return fireworks;
    }
    public void setFireworks(Point[] fireworks) {
        this.fireworks = fireworks;
    }

    public void Explosion_radius(Point p){
        List<Point> list = new ArrayList();
        for(int i=0;i<fireworks.length;i++){
            list.add(fireworks[i]);
        }
        //System.out.println("list:"+list);
        //Collections.sort(list);
        Point pt = Collections.min(list);
        double fitnessmin = pt.fun();
        double minsum = Double.MIN_VALUE;
        for(int i=0;i<fireworks.length;i++){
            minsum = minsum + (fireworks[i].fun()-fitnessmin);
        }
        double r = Constant.A*((p.fun()-fitnessmin)/minsum);
        p.setR(r);
    }
    public void SparksNumber(Point p){
        List<Point> list = new ArrayList();
        for(int i=0;i<fireworks.length;i++){
            list.add(fireworks[i]);
        }
        //Collections.sort(list);
        double fitnessmax = Collections.max(list).fun();
        double maxsum = Double.MIN_VALUE;
        for(int i=0;i<fireworks.length;i++){
            maxsum = maxsum + (fitnessmax-fireworks[i].fun());
        }
        double firesnum = Constant.M*((fitnessmax-p.fun())/maxsum);
        if(firesnum<Constant.a*Constant.M) firesnum = Constant.a*Constant.M;
        if(firesnum>Constant.b*Constant.M) firesnum = Constant.b*Constant.M;
        p.setFiresnum((int)Math.round(firesnum));
    }
    public void FireworksFly(Point p){
        try{
            for (int i=0;i<p.getFiresnum();i++){
                Point point = (Point)p.clone();
                long z = Math.round(Constant.numOfD*Math.random());
                double h = p.getR()*(Math.random()*2-1);
                Set<Integer> hs = Constant.randomZ(Constant.numOfD,(int)z);
                Iterator<Integer> it = hs.iterator();
                while (it.hasNext()) {
                    int j = it.next();
                    point.x[j] = point.x[j] + h;
                    if(point.x[j]>Constant.Xmax||point.x[j]<Constant.Xmin){
                        point.x[j] = Constant.Xmin + Math.abs(point.x[j])%(Constant.Xmax - Constant.Xmin);
                    }
                }
                point.fun();
                p.getListfires().add(point);
                //System.out.println("p:" +p);
                //System.out.println("point:" +point);
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }
    public void FireworksGaussFly(Point p){
        try{
            for (int i=0;i<Constant.m;i++){
                Point point = (Point)p.clone();
                long z = Math.round(Constant.numOfD*Math.random());
                double g = new Random().nextGaussian() + 1.0;
                Set<Integer> hs = Constant.randomZ(Constant.numOfD,(int)z);
                Iterator<Integer> it = hs.iterator();
                while (it.hasNext()) {
                    int j = it.next();
                    point.x[j] = point.x[j] * g;
                    if(point.x[j]>Constant.Xmax||point.x[j]<Constant.Xmin){
                        point.x[j] = Constant.Xmin + Math.abs(point.x[j])%(Constant.Xmax - Constant.Xmin);
                    }
                }
                point.fun();
                p.getListguassfires().add(point);
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }
    //设置该点到其它点（allfireworks里）所有距离
    public void setDistanceToAll(Point p){
        double distancetoall = 0.0;
        for(int i=0;i<allfireworks.size();i++){
            distancetoall = distancetoall + getDistance(p,allfireworks.get(i));
        }
        p.setDistanceToAll(distancetoall);
    }
    public double getDistance(Point p1,Point p2){
        double distance = 0.0;
        for(int i=0;i<Constant.numOfD;i++){
            distance = distance + Math.sqrt(Math.pow(p1.x[i]-p2.x[i],2));
        }
        return distance;
    }
    //计算每个点的概率
    public void setfireworkProbability(Point p){
        //setDistanceToAll(p);
        double sumdistancetoall = 0.0;
        for(int i=0;i<allfireworks.size();i++){
            sumdistancetoall = sumdistancetoall + allfireworks.get(i).getDistanceToAll();
        }
        double pby = p.getDistanceToAll()/sumdistancetoall;
        p.setProbability(pby);
    }
}
