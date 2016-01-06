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
import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.Properties;
import org.apache.log4j.Logger;

public class Config {

    private static Logger logger = Logger.getLogger(Config.class);

    private static Config instance = null;
    private Properties properties;

    protected Config() {
        properties = new Properties();

        InputStream inputStream;
        properties.setProperty("contactPerson", "联系人");
        properties.setProperty("contactPhoneNumber", "8008700302");
        properties.setProperty("purchaser", "采购员");
        properties.setProperty("user", "sa");
        properties.setProperty("password", "@ctive123");
        properties.setProperty("host", "10.109.0.161");
        try {
            String filePath = System.getProperty("user.dir") + "/config/config.properties";
            logger.info("loading config from path " + filePath);
            inputStream = new BufferedInputStream(new FileInputStream(filePath));
            properties.load(inputStream);
        } catch (Exception e) {
            logger.debug("Error in opening config file, use defaul values.", e);
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
