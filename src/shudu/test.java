package shudu;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class test{
    public static void main(String[] args){
        ArrayList<point> list = new ArrayList<>();
        ArrayList<List<point>> list2 = new ArrayList<>();
        point p = new point(new int[]{1,2});
        p.pbest = new point(new int[]{3,4});
        p.pbest.x[0] = p.x[0];
        p.pbest.x[1] = p.x[1];
        System.out.println(Arrays.toString(p.x));
        System.out.println(Arrays.toString(p.pbest.x));
        p.x[0] = 100;
        p.x[1] = 200;
        System.out.println(Arrays.toString(p.x));
        System.out.println(Arrays.toString(p.pbest.x));
    }
}

class point{
    int x[];
    point pbest;
    public point(int x[]){
        this.x = x;
    }

}
