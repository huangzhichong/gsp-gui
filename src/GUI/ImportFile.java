package GUI;

import Helper.Common;
import Helper.Config;
import Helper.DBHelper;
import Helper.ExcelHelper;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author huangsmart
 */
public class ImportFile {

    public static void doImport(String filePath) throws IOException {
        
        String contactPrerson = Config.getInstance().getValue("contactPerson");
        String contactPhoneNumber = Config.getInstance().getValue("contactPhoneNumber");
        String purchaser =Config.getInstance().getValue("purchaser");
        String user = Config.getInstance().getValue("user");
        String password = Config.getInstance().getValue("password");
        String host = Config.getInstance().getValue("host");
        boolean checkExcelColumn = ExcelHelper.checkExcelColumn(filePath);
        if (checkExcelColumn) {
            try {
                importFile(filePath);
            } catch (InterruptedException ex) {
                Logger.getLogger(ImportFile.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else {
            System.out.println("表格内容不符合要求。");
        }
    }

    
    
    
    public static void importFile(String filePath) throws IOException, InterruptedException {
        System.out.println("Import file to database, file path is " + filePath);

        String contactPrerson = Config.getInstance().getValue("contactPerson");
        String contactPhoneNumber = Config.getInstance().getValue("contactPhoneNumber");
        String purchaser =Config.getInstance().getValue("purchaser");
        String user = Config.getInstance().getValue("user");
        String password = Config.getInstance().getValue("password");
        String host = Config.getInstance().getValue("host");
        DBHelper db = DBHelper.getInstance(host, user, password);

        List<Map<String, String>> listFromFile = ExcelHelper.readExcel(filePath);
        for (Map<String, String> rowMap : listFromFile) {

            String productName = rowMap.get("商品名称");
            String model = rowMap.get("型号");
            String unit = rowMap.get("单位");
            String manufactory = rowMap.get("产地");
            String sku = rowMap.get("商品编码");
            String productRegNumber = rowMap.get("产品注册证") + " " + rowMap.get("产品注册证号");
            String number = rowMap.get("数量");

            String sqlSearchProduct = "Select 商品编号 from GoodsDefinition where 备注 = '" + sku + "'";
            List<Map<String, Object>> searchResults = db.executeQuery(sqlSearchProduct);

            if (searchResults.size() == 0) {
                String sqlInsertProdcut = ("INSERT INTO GoodsDefinition (商品名称,型号,基本单位,生产厂商,供应商,产品注册号,联系方式,联系人,备注,修改人,修改时间) VALUES ('"
                        + productName
                        + "','"
                        + model
                        + "','"
                        + unit
                        + "','"
                        + manufactory
                        + "','"
                        + manufactory
                        + "','"
                        + productRegNumber
                        + "','"
                        + contactPhoneNumber
                        + "','"
                        + contactPrerson
                        + "','"
                        + sku
                        + "','Administrator','" + Common.timeStamp() + "')");
                db.execute(sqlInsertProdcut);
            }

            searchResults = db.executeQuery(sqlSearchProduct);
            String productNumber = searchResults.get(0).get("商品编号").toString();
            String purchaseNumber = "P" + Common.timeStamp("yyyyMMddHHmmss");

            String sqlInsertPOMain = ("INSERT INTO PurchaseOrder_main(订单号,供应商,采购员,预计到货日期,摘要,附加说明,订单日期,是否完成验收,修改人,修改时间) VALUES ('"
                    + purchaseNumber
                    + "','"
                    + manufactory
                    + "','"
                    + purchaser
                    + "','"
                    + Common.timeStamp()
                    + "','"
                    + "无"
                    + "','"
                    + "无" + "','" + Common.timeStamp() + "','" + "0" + "','Administrator','" + Common.timeStamp() + "')");

            db.execute(sqlInsertPOMain);

            String sqlInsertPODetail = ("INSERT INTO PurchaseOrder_detail(订单号,商品编号,商品名称,型号,基本单位,订单数量,生产厂商,供应商,产品注册号,联系方式,联系人,备注) VALUES ('"
                    + purchaseNumber
                    + "','"
                    + productNumber
                    + "','"
                    + productName
                    + "','"
                    + model
                    + "','"
                    + unit
                    + "','"
                    + number
                    + "','"
                    + manufactory
                    + "','"
                    + manufactory
                    + "','"
                    + productRegNumber
                    + "','"
                    + contactPrerson
                    + "','" + contactPhoneNumber + "','无')");
            db.execute(sqlInsertPODetail);
        }

    }
}
