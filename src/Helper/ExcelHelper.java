/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Helper;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;

/**
 *
 * @author huangsmart
 */
public class ExcelHelper {

    public static String imgList;
    public static String errorMsg;
    public static boolean status;

    /**
     * Example: upperFirst("test") => "Test"
     *
     * @param target
     * @return format string
     */
    public static String upperFirst(String target) {
        String temp = target.substring(0, 1);
        return temp.toUpperCase() + target.substring(1);
    }

    public static boolean checkExcelColumn(String filePath) {
        Boolean result = false;
        if (filePath.endsWith(".xls")) {
            File f = new File(filePath);
            List<List<String>> list = null;
            InputStream is = null;
            HSSFWorkbook wb = null;

            try {
                is = new FileInputStream(f);
                wb = new HSSFWorkbook(is);
            } catch (Exception e) {
                System.out.println("Cannot find file or file cannot be reading!");
                System.out.println(e);
            } finally {
                try {
                    if (null != is) {
                        is.close();
                    }
                } catch (IOException e) {
                    System.out.println("Cannot close input stream!");
                }
            }
            if (null != wb) {
                HSSFSheet sheet = wb.getSheetAt(wb.getFirstVisibleTab());
                HSSFRow headerRow = sheet.getRow(0);
                List<String> headers = new ArrayList<String>();
                int cellNum = headerRow.getLastCellNum();

                for (int k = 0; k < cellNum; k++) {
                    Cell c = headerRow.getCell(k);
                    headers.add(c.getStringCellValue());
                }
                List<String> requiredColumns = Arrays.asList("商品名称", "型号", "单位", "产地", "商品编码", "产品注册证", "产品注册证号", "数量");

                System.out.println(headers.toString());
                System.out.println(requiredColumns.toString());

                if (headers.containsAll(requiredColumns)) {
                    result = true;
                }

            } else {
                System.out.println("Excel file has nothing!");
            }
        } else {
            System.out.println("Excel file must be in xls format");
        }
        return result;
    }

    public static List<List<String>> readCSV(String filePath) {
        List<List<String>> list = null;
        BufferedReader br = null;
        try {
            br = new BufferedReader(new FileReader(filePath));
            String data = null;
            try {
                data = br.readLine();
                if (null != data) {
                    list = new ArrayList<List<String>>();
                    while (data != null) {
                        list.add(Arrays.asList(data.split(",")));
                        data = br.readLine();
                    }
                } else {
                    System.out.println("Target file has nothing!");
                }
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        } catch (Exception e) {
            System.out.println("Cannot find file or file cannot be reading!");
        } finally {
            try {
                br.close();
            } catch (IOException e) {
                System.out.println("Cannot close input stream!");
            }
        }
        return list;
    }

    public static List<Map<String, String>> readExcel(String filePath) {
        File f = new File(filePath);
        List<List<String>> list = null;
        InputStream is = null;
        HSSFWorkbook wb = null;
        ArrayList<Map<String, String>> hashResult = new ArrayList<Map<String, String>>();

        try {
            is = new FileInputStream(f);
            wb = new HSSFWorkbook(is);
        } catch (Exception e) {
            System.out.println("Cannot find file or file cannot be reading!");
        } finally {
            try {
                if (null != is) {
                    is.close();
                }
            } catch (IOException e) {
                System.out.println("Cannot close input stream!");
            }
        }
        if (null != wb) {
            list = new ArrayList<List<String>>();
            HSSFSheet sheet = wb.getSheetAt(wb.getFirstVisibleTab());
            int rowNum = sheet.getLastRowNum();
            HSSFRow headerRow = sheet.getRow(0);
            List<String> headers = new ArrayList<String>();
            int cellNum = headerRow.getLastCellNum();

            for (int k = 0; k < cellNum; k++) {
                Cell c = headerRow.getCell(k);
                headers.add(c.getStringCellValue());
            }

            for (int j = 1; j < rowNum; j++) {
                HSSFRow row = sheet.getRow(j);
                List<String> args = new ArrayList<String>();
                Map<String, String> line_map = new HashMap<String, String>();
                for (int k = 0; k < cellNum; k++) {
                    Cell c = row.getCell(k);
                    c.setCellType(Cell.CELL_TYPE_STRING);
                    args.add(c.getStringCellValue());
                    line_map.put(headers.get(k), c.getStringCellValue());
                }
                list.add(args);
                hashResult.add(line_map);
            }

        } else {
            System.out.println("Excel file has nothing!");
        }
        return hashResult;
    }
}
