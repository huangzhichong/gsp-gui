package GUI;

import Helper.Common;
import Helper.Config;
import Helper.DBHelper;
import Helper.ExcelHelper;
import java.io.IOException;
import java.util.List;
import java.util.Map;

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

    private static org.apache.log4j.Logger logger = org.apache.log4j.Logger.getLogger(ImportFile.class);

    public static void doImport(String filePath) throws Exception {
        boolean checkExcelColumn = ExcelHelper.checkExcelColumn(filePath);
        if (checkExcelColumn) {
            try {
                logger.info("Start importing data to database. The path for source file is " + filePath);
                importFile(filePath);
            } catch (InterruptedException ex) {
                logger.error("Error in importing excel to database.\n", ex);
                throw ex;
            }
        } else {
            logger.debug("Columns in file header is not matching.");
        }
    }

    public static void importFile(String filePath) throws IOException, InterruptedException {

        String contactPrerson = Config.getInstance().getValue("contactPerson");
        String contactPhoneNumber = Config.getInstance().getValue("contactPhoneNumber");
        String purchaser = Config.getInstance().getValue("purchaser");
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

            if (searchResults.isEmpty()) {

                logger.info("Adding product info to GoodsDifination");
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
            logger.info("Get product number from database -> " + productNumber);
            String purchaseNumber = "P" + Common.timeStamp("yyyyMMddHHmmss");
            logger.info("Add record to PurchaseOrder_main for purchase number ->" + purchaseNumber);

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
            logger.info("Add record to PurchaseOrder_detail for purchase number ->" + purchaseNumber);
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
        
        logger.info("Importing data finished.");

    }
}
