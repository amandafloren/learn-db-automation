package stepdefinitions;
import org.openqa.selenium.WebDriver;
import page.connection.PostgresConn;
import page.repo.DataRepo;
import page.utils.Function;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import driver.Driverfactory;

public class CommonSetup {
    //WebDriver driver = Driverfactory.getWebDriver();
    static Map<String, String> SourceData = new HashMap<String, String>();
    static PostgresConn Connection = new PostgresConn();
    static DataRepo DataRepo= new DataRepo();

    public static String reference_id;
    public static String url;
    public static String dob;
    public static String full_name;
    public static String email;
    public static String created_date;
    public static String UI_ref_id;
    public static String UI_full_name;
    public static String UI_created_date;
    public static String UI_email;
    public static String warn_text;
    public static String warntextASNTBX;
    public static String submit_warn_text;

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
    protected String getPassword()throws IOException{
        return Function.decryptPass(getProperty("Password"));
    }
}
