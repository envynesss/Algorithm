package Ncc;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class NccValue {
    public double[][] nccMatrix;
    class Image {
        String filePath;
        int m;
        int n;
        double grayAverage;
        int[][] grayValue;

        public Image(String filePath, int rows, int columns, double grayAverage) {
            this.filePath = filePath;
            this.m = rows;
            this.n = columns;
            this.grayAverage = grayAverage;
        }
    }

    public static void txtToArray(Image image) {
        try {
            FileReader fr = new FileReader(image.filePath);
            BufferedReader bfr = new BufferedReader(fr);
            String str;
            int cols = 0;
            while ((str = bfr.readLine()) != null) {
                String[] ss = str.split(",");
                for (int i = 0; i < ss.length; i++) {
                    image.grayValue[cols][i] = Integer.parseInt(ss[i].trim());
                }
                cols++;
            }
            for (int i = 0; i < image.m; i++) {
                for (int j = 0; j < image.n; j++) {
                    System.out.print(image.grayValue[i][j] + " ");
                }
                System.out.println();
            }
        }catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void setNccMatrix(Image F) {
        
    }
    
    public double NccXY(Image t, Image f, int x, int y) {
        double Numerator = 0;// and denominator
        int m = t.m;
        int n = t.n;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                Numerator = Numerator + (f.grayValue[x][y]);
            }
        }
        return 0.0;
    }
}
