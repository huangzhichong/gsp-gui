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
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class Config {

    private static Config instance = null;
    private Properties properties;

    protected Config() {
        properties = new Properties();
        try {
            InputStream inputStream;
            String propFileName = "./config.properties";
            properties.setProperty("contactPerson", "联系人");
            properties.setProperty("contactPhoneNumber", "8008700302");
            properties.setProperty("purchaser", "采购员");
            properties.setProperty("user", "sa");
            properties.setProperty("password", "@ctive123");
            properties.setProperty("host", "10.109.0.161");
            inputStream = getClass().getResourceAsStream("config.properties");
            properties.load(inputStream);
        } catch (IOException e) {
            System.out.println("Error in opening config file, use defaul values.");
        }

    }

    public static Config getInstance() {
        if (instance == null) {
            instance = new Config();
        }
        return instance;
    }

    public String getValue(String key) {
        return properties.getProperty(key);
    }

    public void setValue(String key, String value) {
        properties.setProperty(key, value);
    }

}
