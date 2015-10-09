package dao;

import java.io.*;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.io.FileInputStream;
import java.io.IOException;
import java.sql.SQLException;
import java.util.Properties;

public class Database {
    public Connection getConnection() throws Exception {
        String propFileName = "database.properties";
        Properties props = new Properties();
        Connection con = null;
        InputStream inputStream;
        try {
            inputStream = getClass().getClassLoader().getResourceAsStream(propFileName);
            if (inputStream != null) {
                props.load(inputStream);
            } else {
                throw new FileNotFoundException("property file '" + propFileName + "' not found in the classpath");
            }
            Class.forName(props.getProperty("DB_DRIVER_CLASS"));
            con = DriverManager.getConnection(props.getProperty("DB_URL"),
                    props.getProperty("DB_USERNAME"),
                    props.getProperty("DB_PASSWORD"));
            return con;
        } catch (Exception e) {
            // TODO Auto-generated catch block
            throw e;
        }

    }

}
