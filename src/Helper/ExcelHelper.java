/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Helper;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

/**
 *
 * @author huangsmart
 */
public class ExcelHelper {

    private static org.apache.log4j.Logger logger = org.apache.log4j.Logger.getLogger(ExcelHelper.class);

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

    public static boolean checkManufactoryInfoExcelColumn(String filePath) throws Exception {
        List<String> requiredColumns = Arrays.asList("生产商家", "供应商", "联系人", "联系方式");
        return checkExcelColumn(filePath, requiredColumns);
    }

    public static boolean checkPurchaseInfoExcelColumn(String filePath) throws Exception {
        List<String> requiredColumns = Arrays.asList("商品名称", "型号", "单位", "产地", "商品编码", "产品注册证", "产品注册证号", "数量", "批号","出厂日期","保质期截至日期");
        return checkExcelColumn(filePath, requiredColumns);
    }

    public static boolean checkExcelColumn(String filePath, List<String> requiredColumns) throws Exception {
        Boolean result = false;
        if (filePath.endsWith(".xls")) {
            FileInputStream inputStream = null;
            Workbook workbook = null;

            try {
                inputStream = new FileInputStream(new File(filePath));
                workbook = getWorkbook(inputStream, filePath);
            } catch (Exception ex) {
                logger.error("Failed to open file " + filePath, ex);
                throw ex;
            } finally {
                if (null != inputStream) {
                    try {
                        inputStream.close();
                    } catch (IOException ex) {
                        logger.error("Not able to close this file " + filePath);
                    }
                }
            }
            if (null != workbook) {
                Sheet sheet = workbook.getSheetAt(workbook.getFirstVisibleTab());
                Row headerRow = sheet.getRow(0);
                List<String> headers = new ArrayList<String>();
                int cellNum = headerRow.getLastCellNum();

                for (int k = 0; k < cellNum; k++) {
                    Cell c = headerRow.getCell(k);
                    headers.add(c.getStringCellValue());
                }
                logger.info("Get headers -> " + headers.toString());
                logger.info("Required headers => " + requiredColumns.toString());
                if (headers.containsAll(requiredColumns)) {
                    result = true;
                }

            } else {
                logger.debug("Excel file has nothing!");
            }
        } else {
            logger.debug("Excel file must be in xls format");
        }
        return result;
    }

    public static Map<String, List<String>> readManufactoryInfo(String filePath) {
        List<String> list = null;
        Map<String, List<String>> hashResult = new HashMap<String, List<String>>();        
        List<Map<String, String>> excelArray = readExcel(filePath);
        for (Map<String, String> excelLine : excelArray) {
            System.out.println(excelLine.get("生产商家"));
            list = Arrays.asList(excelLine.get("供应商"), excelLine.get("联系人"), excelLine.get("联系方式"));
            hashResult.put(excelLine.get("生产商家"), list);
        }
        return hashResult;
    }

    public static List<Map<String, String>> readExcel(String filePath) {
        List<List<String>> list = null;
        ArrayList<Map<String, String>> hashResult = new ArrayList<Map<String, String>>();

        FileInputStream inputStream = null;
        Workbook workbook = null;
        try {
            inputStream = new FileInputStream(new File(filePath));
            workbook = getWorkbook(inputStream, filePath);
        } catch (FileNotFoundException ex) {
            logger.error("File can't be found under path " + filePath);
        } catch (IOException ex) {
            logger.error("Not able to open this file " + filePath);
        } finally {
            if (null != inputStream) {
                try {
                    inputStream.close();
                } catch (IOException ex) {
                    logger.error("Not able to close this file " + filePath);
                }
            }

        }
        if (null != workbook) {
            list = new ArrayList<List<String>>();
            Sheet sheet = workbook.getSheetAt(workbook.getFirstVisibleTab());
            int rowNum = sheet.getLastRowNum();
            Row headerRow = sheet.getRow(0);
            List<String> headers = new ArrayList<String>();
            int cellNum = headerRow.getLastCellNum();

            for (int k = 0; k < cellNum; k++) {
                Cell c = headerRow.getCell(k);
                headers.add(c.getStringCellValue());
            }

            for (int j = 1; j < rowNum; j++) {
                Row row = sheet.getRow(j);
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
            try {
                workbook.close();
            } catch (IOException ex) {
                logger.error("Failed to close this file " + filePath);
            }

        } else {
            logger.debug("Excel file has nothing!");
        }

        return hashResult;
    }

    private static Workbook getWorkbook(FileInputStream inputStream, String excelFilePath) throws IOException {
        Workbook workbook = null;

        if (excelFilePath.endsWith("xlsx")) {
            logger.info("Read file with xlsx format");
            workbook = new XSSFWorkbook(inputStream);
        } else if (excelFilePath.endsWith("xls")) {
            logger.info("Read file with xls format");
            workbook = new HSSFWorkbook(inputStream);
        } else {
            IllegalArgumentException notSupportedFormat = new IllegalArgumentException("The specified file is not Excel file");
            logger.error("File is in not supported format", notSupportedFormat);
        }

        return workbook;
    }

    public static Map<String, String> readQualityReportInfo(String filePath) {
        List<String> list = null;
        Map<String, String> hashResult = new HashMap<String, String>();        
        List<Map<String, String>> excelArray = readExcel(filePath);
        for (Map<String, String> excelLine : excelArray) {
            hashResult.put(excelLine.get("型号"), excelLine.get("检验报告号"));
        }
        return hashResult;
    }

}
