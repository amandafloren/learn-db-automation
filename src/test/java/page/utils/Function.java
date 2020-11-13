package page.utils;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.jasypt.util.text.BasicTextEncryptor;

public class Function {
    public static String encrypt(String text) {
        BasicTextEncryptor textEncryptor = new BasicTextEncryptor();
        textEncryptor.setPassword("Pa$$w0rd");
        return textEncryptor.encrypt(text);
    }

    public static String decryptPass(String text) {
        BasicTextEncryptor textEncryptor = new BasicTextEncryptor();
        textEncryptor.setPassword("Pa$$w0rd");
        return textEncryptor.decrypt(text);
    }


    public static boolean Scroll_Down(WebDriver driver) throws InterruptedException{
        try {
            JavascriptExecutor jse = (JavascriptExecutor)driver;
            jse.executeScript("window.scrollBy(0,500)", "");
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}
