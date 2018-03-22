package shudu;

import java.util.Scanner;

public class test{
    public static void main(String[] arrgs){
        Scanner reader =new Scanner(System.in);
        while (reader.hasNext()) {
            int start=reader.nextInt();
            int end=reader.nextInt();
            System.out.println(start+end);
        }
    }
}
