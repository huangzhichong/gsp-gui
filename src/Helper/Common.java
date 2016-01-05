/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Helper;

/**
 *
 * @author huangsmart
 */
public class Common {

    public static String timeStamp() throws InterruptedException {
        java.util.Date currentDate = new java.util.Date();
        java.text.SimpleDateFormat dateFormat = new java.text.SimpleDateFormat("yyyy-MM-dd");
        String date = dateFormat.format(currentDate);
        Thread.sleep(1000);
        return date;
    }

    public static String timeStamp(String timeFormat) throws InterruptedException {
        java.util.Date currentDate = new java.util.Date();
        java.text.SimpleDateFormat dateFormat = new java.text.SimpleDateFormat(timeFormat);
        String date = dateFormat.format(currentDate);
        Thread.sleep(1000);
        return date;
    }

}
