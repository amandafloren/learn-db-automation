package driver;

import io.cucumber.java.Scenario;
import org.openqa.selenium.UnexpectedAlertBehaviour;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import org.openqa.selenium.remote.LocalFileDetector;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.io.IOException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

import page.utils.Settings;
import stepdefinitions.CommonSetup;
public class Driverfactory {
    private static RemoteWebDriver driver;
    private static String defaultWindowHandle;
    static Scenario scenario;
    static Settings settings = new Settings();
    public final static WebDriver getWebDriver(){
        if (driver == null ) {
            try {
                createNewDriverInstance();
            } catch (Exception e) {
                System.out.println("Error on creating driver");
                e.printStackTrace();
            }

        }
        return driver;
    }


    public static void createNewDriverInstance() throws IOException {

        ChromeOptions options = new ChromeOptions();
        options.setAcceptInsecureCerts(true);
        options.setUnhandledPromptBehaviour(UnexpectedAlertBehaviour.ACCEPT);
        //WebDriver driver = new ChromeDriver(options);


        //final URL remoteAddress = new URL(url);
       // driver = new RemoteWebDriver(remoteAddress, options);
        //driver.setFileDetector(new LocalFileDetector());

        // driver = new ChromeDriver(capabilities);
        driver.manage().timeouts().implicitlyWait(Settings.getDefaultTimeOut(), TimeUnit.SECONDS);
        driver.manage().timeouts().pageLoadTimeout(180, TimeUnit.SECONDS);
        driver.manage().window().maximize();
        driver.manage().deleteAllCookies();
       // defaultWindowHandle = driver.getWindowHandle();
    }


    public static void tearDown(){
        if (driver != null) {
            try {
                driver.quit();
                Thread.sleep(5000);
            }catch(Exception e)
            {
                //
            }
            finally {
                driver = null;
            }
        }
    }
}
