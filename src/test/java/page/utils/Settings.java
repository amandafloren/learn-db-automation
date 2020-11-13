package page.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;
import io.cucumber.java.Scenario;

public class Settings {
    final private static long defaultTimeOut = 50;
    private static Scenario currentScenario;
    private static String currentScreencaptureFolderPath;
    private static String _imageFilePath;
    private static String browserDownloadHostPath = System.getProperty("user.home") + File.separator
            + "docksel_downloads";
    private static String browserDownloadRemotePath = "/home/seluser/Downloads";
    private static String browserDownloadRemoteFolderUR = "file://seluser/home/seluser/Downloads/";
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

    public String getDriverRemoteURL()throws IOException {
        //driverRemoteURL = "http://localhost:4444/wd/hub";
        return getProperty("driverRemoteURL");
    }



    public static long getDefaultTimeOut() {
        return defaultTimeOut;
    }

    public static void setCurrentScreenCaptureFolderPath(String folderPath) {
        currentScreencaptureFolderPath = folderPath;
    }

    public static String getCurrentScreenCaptureFolderPath() {
        if (!currentScreencaptureFolderPath.endsWith(File.separator))
            currentScreencaptureFolderPath = currentScreencaptureFolderPath + "/";
        return currentScreencaptureFolderPath;
    }

    public static void setImagePathForUpload(String imageFilePath) {
        _imageFilePath = imageFilePath;
    }

    public static String getImagePathForUpload() {
        return _imageFilePath;
    }

    public static String getBrowserDownloadHostPath() {
        return browserDownloadHostPath;
    }

    public static void setBrowserDownloadPath(String browserDownloadPath) {
        Settings.browserDownloadHostPath = browserDownloadPath;
    }

    public static String getBrowserDownloadRemotePath() {
        return browserDownloadRemotePath;
    }

    public static void setBrowserDownloadRemotePath(String browserDownloadRemotePath) {
        Settings.browserDownloadRemotePath = browserDownloadRemotePath;
    }

    public static Scenario getCurrentScenario() {
        return currentScenario;
    }

    public static void setCurrentScenario(Scenario currentScenario) {
        Settings.currentScenario = currentScenario;
    }

    public static String getBrowserDownloadRemoteFolderURL() {
        return browserDownloadRemoteFolderUR;
    }

    public static boolean shoudlDeleteOriginalScreenCaptures() {
        // TODO Auto-generated method stub
        return false;
    }

}
