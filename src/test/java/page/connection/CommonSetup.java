package page.connection;

import page.utils.Function;
import page.utils.Settings;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Properties;

public class CommonSetup {
    static Settings settings = new Settings();
    java.sql.Connection con = null;
    Statement stmt = null;
    ResultSet rs = null;
    static PostgresConn Connection = new PostgresConn();
    String QueryPg;


    private Properties configProperties = new Properties();

    private void readConfigFile() throws IOException {
        configProperties.load(new FileInputStream("Config.properties"));
    }

    private String getProperty(String key) throws IOException {
        if (configProperties.isEmpty()) {
            readConfigFile();
        }
        return configProperties.getProperty(key);
    }
    public String getUrlDataBase()throws IOException{
        return getProperty("UrlDataBase");
    }

    public String getUserNameDatabase()throws IOException{
        return getProperty("UserNameDatabase");
    }

    public  String getPasswordDatabase()throws IOException{
        return Function.decryptPass(getProperty("PasswordDatabase"));
    }

    public  String getUrlDbPostgre()throws IOException{
        return getProperty("UrlDbPostgre");
    }

    public String getUserNameDbPostgre()throws IOException{
        return getProperty("UserNameDbPostgre");
    }

    public  String getPassDbPostgre()throws IOException{
        return Function.decryptPass(getProperty("PassDbPostgre"));
    }
}
