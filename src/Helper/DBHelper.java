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
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.apache.log4j.Logger;

public class DBHelper {

    private static Logger logger = Logger.getLogger(DBHelper.class);
    private static DBHelper instance = null;

    private String driverName = "net.sourceforge.jtds.jdbc.Driver";
    private String URL = "jdbc:jtds:sqlserver://10.109.0.161:1433;DatabaseName=MEMSDB";
    private String userName = "sa";
    private String userPwd = "@ctive123";
    private Connection conn = null;
    private Statement st = null;
    private ResultSet rs = null;

    /**
     * Used to get the database utilities instance.
     *
     * @return
     */
    public static DBHelper getInstance() {
        if (null == instance) {
            instance = new DBHelper();
        }

        return instance;
    }

    public static DBHelper getInstance(String host, String user, String password) {
        if (null == instance) {
            String URL = "jdbc:jtds:sqlserver://" + host + ":1433;DatabaseName=MEMSDB";
            logger.debug(URL);
            logger.debug(user);
            logger.debug(password);
            instance = new DBHelper(URL, user, password);
        }

        return instance;
    }

    /**
     * Close the database connect session.
     *
     * @return if close success.
     */
    public boolean closeSession() {
        boolean flag = false;
        try {
            if (null != rs) {
                rs.close();
            }
            if (null != st) {
                st.close();
            }
            if (null != conn) {
                conn.close();
            }
            flag = true;
        } catch (SQLException e) {
            logger.error("Failed to close database connection.\n" + e.fillInStackTrace());
        }
        return flag;
    }

    /**
     * Execute update,delete,insert action.
     *
     * @param sql
     * @return true if the first result is a ResultSet object; false if it is an
     * update count or there are no results
     */
    public boolean execute(String sql) {
        boolean result = false;
        try {
            st = conn.createStatement();
            result = st.execute(sql);
            st.close();
        } catch (SQLException e) {
            logger.error("Failed to execute query\n" + e.fillInStackTrace());
        }
        return result;
    }

    /**
     * Executes the given SQL statement, which returns a single result which in
     * a Map style.
     *
     * @param sql
     * @return a Map style record. which contains column name and value.
     */
    public Map<String, Object> exceuteQueryOne(String sql) {
        List<Map<String, Object>> list = executeQuery(sql);
        return list.size() > 0 ? list.get(0) : null;
    }

    /**
     * Executes the given SQL statement, which returns the query results every
     * one in a Map style.
     *
     * @param sql
     * @return a List of Map which contain the column name and value of the
     * table data that contains the data produced by the given query;
     */
    public List<Map<String, Object>> executeQuery(String sql) {
        List<Map<String, Object>> result = null;
        try {
            result = new ArrayList<Map<String, Object>>();
            st = conn.createStatement();
            rs = st.executeQuery(sql);
            ResultSetMetaData metaData = rs.getMetaData();
            int columnCount = metaData.getColumnCount();
            while (rs.next()) {
                Map<String, Object> map = new HashMap<String, Object>();
                for (int i = 1; i <= columnCount; i++) {
                    String name = metaData.getColumnName(i);
                    Object value = rs.getObject(name);
                    map.put(name, value);
                }
                result.add(map);
            }
            rs.close();
            st.close();
        } catch (SQLException e) {
            logger.error("Failed to execute query\n" + e.fillInStackTrace());
        }
        return result;
    }

    private DBHelper() {
        System.out.println(URL);
        System.out.println(userName);
        System.out.println(userPwd);
        if (null == conn) {
            try {
                Class.forName(driverName);
                conn = DriverManager.getConnection(URL, userName, userPwd);
            } catch (ClassNotFoundException e) {
                logger.error("Error in connection string.\n" + e.fillInStackTrace());
            } catch (SQLException e) {
                logger.error("Error in making database connection.\n" + e.fillInStackTrace());

            }
        }
    }

    private DBHelper(String connString, String user, String password) {

        System.out.println(connString);
        System.out.println(user);
        System.out.println(password);
        if (null == conn) {
            try {
                Class.forName(driverName);
                conn = DriverManager.getConnection(connString, user, password);
            } catch (ClassNotFoundException e) {
                logger.error("Error in connection string.\n" + e.fillInStackTrace());
            } catch (SQLException e) {
                logger.error("Error in making database connection.\n" + e.fillInStackTrace());
            }
        }
    }

}
