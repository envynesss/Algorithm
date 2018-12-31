package Firefly;

import java.io.*;
import java.util.*;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
/**
 * The Constants for Firefly Algorithm
 */
public class Constant{

    public static final int NumofP = 100;
    //γ为light_absorption光吸收率属于[0.1,10]
    public static final double gamma = 1;
    //β0, 一般取1
    public static final double beta0 = 1;
    //α为Step factor步长因子属于[0,1] alpha在函数中配置

    public static int iterations = 30000; //迭代次数
    public static int runTimes = 3; //独立运行次数
    public static int CBLS_step = 5; //局域搜索的探索次数
    public static double CBLS_move = 0.000001; //局域搜索的移动距离

    //函数相关参数
    public static int codeNum;
    public static int funDims;
    public static double speciesRs;
    public static double[] maxRange;
    public static double[] minRange;
    public static double[][] lPoints;
    public static double[][] gPoints;
    public static double alpha;
    public static double DisThreshold; // 与最优点的距离临界值
    public static double FitThreshold; // 与最优点的相对适应值临界值

    //输出文件路径
    public static String folder = "z-output/";
    public static String filePath = folder + "0_data.txt";
    //excel文件路径
    public static String XlsxPath = folder + "result.xlsx";

    /**
     * Function 函数相关参数社设置
     */
    public static void setFuncPara(int code_Num, int fun_Dims, double species_Rs, double[] max_Range, double[] min_Range,
                                   double[][] l_Points, double[][] g_Points, double alpha, double Dis_Threshold, double Fit_Threshold) {
        Constant.codeNum = code_Num;
        Constant.funDims = fun_Dims;
        Constant.speciesRs = species_Rs;
        Constant.maxRange = max_Range;
        Constant.minRange = min_Range;
        Constant.lPoints = l_Points;
        Constant.gPoints = g_Points;
        Constant.alpha = alpha;
        Constant.DisThreshold = Dis_Threshold;
        Constant.FitThreshold = Fit_Threshold;
    }

    /**
     * 取到环形邻域结构的seed的邻域
     */
    public static List<Integer> get_neibor_num_list(int num, int k, int rs){
        List<Integer> list = new ArrayList<>();
        if (k - rs < 0) {
            int s1 = k - rs + num;
            int e1 = num;
            //i=s1;i < e1
            for (int i = s1; i < e1; i++) {
                list.add(i);
            }
            int s2 = 0;
            int e2 = k + rs + 1;
            //i=s2;i < e2
            for (int i = s2; i < e2; i++) {
                list.add(i);
            }
        }
        else if (k + rs > num - 1) {
            int s1 = k - rs;
            int e1 = num;
            //i=s1;i < e1
            for (int i = s1; i < e1; i++) {
                list.add(i);
            }
            int s2 = 0;
            int e2 = k + rs - num + 1;
            //i=s2;i < e2
            for (int i = s2; i < e2; i++) {
                list.add(i);
            }
        }
        else {
            int s1 = k - rs;
            int e1 = k + rs + 1;
            for (int i = s1; i < e1; i++) {
                list.add(i);
            }
        }
        return list;
    }

    /**
     * 取数组的平均值
     */
    public static double average(double[] array, int code) {
        double sum = 0;
        int end = array.length;
        Arrays.sort(array);
        if (code == 1) {
            end = array.length - array.length / 10;
        }
        for (int i = 0; i < end; i++) {
            sum = sum + array[i];
        }
        return sum / end;
    }

    /**
     * 获得标准正太分布的随机因子
     */
    public static double getGaussian(){
        Random r = new Random();
        return r.nextGaussian();
    }

    /**
     * 输出写入txt文件函数
     */
    public static void fileChaseFW(String filePath, String content) {
        try {
            //构造函数中的第二个参数true表示以追加形式写文件
            FileWriter fw = new FileWriter(filePath,true);
            fw.write(content);
            fw.write('\n');
            fw.close();
            System.out.println(content);
        } catch (IOException e) {
            System.out.println("文件写入失败！" + e);
        }
    }

    /**
     * 输出写入Excel文件函数
     */
    public static void writeExcel(List<Double> dataList, String finalXlsxPath, int codeNum, int swarmCode){
        OutputStream out = null;
        try {
            // 读取Excel文档
            File finalXlsxFile = new File(finalXlsxPath);
            Workbook workBook = getWorkbok(finalXlsxFile);
            // sheet 对应一个工作页
            Sheet sheet = workBook.getSheetAt(0);

            //往Excel中写新数据, 创建一行：从第二行开始，跳过属性列
            Row row = sheet.getRow(codeNum + 1);
            int s = 9 + (swarmCode - 1) * 4;
            for (int j = 0; j < dataList.size(); j++) {
                // 得到要插入的每一条记录
                double data = dataList.get(j);
                Cell first = row.createCell(s++);
                first.setCellValue(data);
            }
            //创建文件输出流，准备输出电子表格：这个必须有，否则你在sheet上做的任何操作都不会有效
            out =  new FileOutputStream(finalXlsxPath);
            workBook.write(out);
        } catch (Exception e) {
            e.printStackTrace();
        } finally{
            try {
                if(out != null){
                    out.flush();
                    out.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        //System.out.println("数据导出成功");
    }

    /**
     * 判断Excel的版本,获取Workbook
     * @param file
     * @return
     * @throws IOException
     */
    public static Workbook getWorkbok(File file) throws IOException{
        Workbook wb = null;
        FileInputStream in = new FileInputStream(file);
        if(file.getName().endsWith("xls")){     //Excel&nbsp;2003
            wb = new HSSFWorkbook(in);
        }else if(file.getName().endsWith("xlsx")){    // Excel 2007/2010
            wb = new XSSFWorkbook(in);
        }
        return wb;
    }
}