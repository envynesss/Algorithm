package shudu;

import java.util.*;

public class test{
    public static void main(String[] arrgs){
        int i = 0;
        i = i++;
        System.out.println(i);

        StringBuffer a = new StringBuffer("A");
        StringBuffer b = new StringBuffer("B");
        fun(a,b);
        System.out.println(a+"."+b);

    }

    public static void fun(StringBuffer x,StringBuffer y){
        x.append(y);
        y = x;
    }
}
