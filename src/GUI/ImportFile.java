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
        String manufactoryInfoFilePath = System.getProperty("user.dir") + "/config/信息表.xls";
        boolean checkManufactoryInfoColumn = ExcelHelper.checkManufactoryInfoExcelColumn(manufactoryInfoFilePath);
        boolean checkExcelColumn = ExcelHelper.checkPurchaseInfoExcelColumn(filePath);
        if (checkManufactoryInfoColumn && checkExcelColumn) {
            try {
                logger.info("Start importing data to database. The path for source file is " + filePath);
                importFile(filePath);
            } catch (InterruptedException ex) {
                logger.error("Error in importing excel to database.\n", ex);
                throw ex;
            }
        } else {
            logger.debug("Columns in file header is not matching.Please check the soruce file or manufactory info file.");
        }
    }

    public static void importFile(String filePath) throws IOException, InterruptedException {
        String manufactoryInfoFilePath = System.getProperty("user.dir") + "/config/信息表.xls";
        Map<String, List<String>> manufactoryInfo = ExcelHelper.readManufactoryInfo(manufactoryInfoFilePath);

        String purchaser = Config.getInstance().getValue("purchaser");
        String user = Config.getInstance().getValue("user");
        String password = Config.getInstance().getValue("password");
        String host = Config.getInstance().getValue("host");
        String qualityPerson = Config.getInstance().getValue("qualityPerson");
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
            String batchNumber = rowMap.get("批号");
            String productionDate = rowMap.get("出厂日期");
            String expireDate = rowMap.get("保质期截至日期");
            List<String> temp = manufactoryInfo.get(manufactory);
            
            String supplier = temp.get(0);
            String contactPrerson = temp.get(1);
            String contactPhoneNumber = temp.get(2);

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
                        + supplier
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
            String checkNumber = ("CN" + purchaseNumber.substring(5, 14));
            logger.info("Add record to PurchaseOrder_main for purchase number ->" + purchaseNumber);

            String sqlInsertPOMain = ("INSERT INTO PurchaseOrder_main(订单号,供应商,采购员,预计到货日期,摘要,附加说明,订单日期,是否完成验收,修改人,修改时间) VALUES ('"
                    + purchaseNumber
                    + "','"
                    + supplier
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
                    + supplier
                    + "','"
                    + productRegNumber
                    + "','"
                    + contactPhoneNumber
                    + "','" + contactPrerson + "','无')");
            db.execute(sqlInsertPODetail);

            logger.info("Add record to PurchaseOrder_check for purchase number ->" + purchaseNumber);

            String sqlInsertPOCheck = ("INSERT INTO PurchaseOrder_check (订单号,商品编号,商品批号,验收编号,生产日期,有效期至,合格数量,不合格数量,检验报告号,合格质量状态,是否拒收,拒收原因,质量员,是否已质检,修改人,修改时间) VALUES ('"
                    + purchaseNumber
                    + "','"
                    + productNumber
                    + "','"
                    + batchNumber
                    + "','1','"
                    + productionDate
                    + "','"
                    + expireDate
                    + "','"
                    + number
                    + "','0','"
                    + checkNumber
                    + "','合格','0','无','"
                    + qualityPerson
                    + "','1','Administrator','" + Common.timeStamp() + "')");
            db.execute(sqlInsertPOCheck);

        }

        logger.info("Importing data finished.");

    }
}
