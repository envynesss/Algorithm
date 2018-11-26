package Ncc;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class NccValue {
    public static double[][] nccMatrix;
    public static int row = 422;
    public static int col = 393;

    public NccValue() {
        setNccMatrix("src/Ncc/ncc422-393.txt", row, col);
    }

    public static void setNccMatrix(String filePath, int row, int col) {
        try {
            nccMatrix = new double[row][col];
            BufferedReader bfr = new BufferedReader(new FileReader(filePath));
            String str;
            int cols = 0;
            while ((str = bfr.readLine()) != null) {
                String[] ss = str.split(",");
                for (int i = 0; i < ss.length; i++) {
                    nccMatrix[cols][i] = Double.parseDouble(ss[i].trim());
                }
                cols++;
            }
            //Constant.printArray(nccMatrix, 543, 452);
        }catch (IOException e) {
            e.printStackTrace();
        }
    }
}
