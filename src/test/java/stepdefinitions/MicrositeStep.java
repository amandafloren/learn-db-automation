package stepdefinitions;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import page.utils.Settings;
import java.util.concurrent.TimeUnit;


public class MicrositeStep extends CommonSetup {
    RemoteWebDriver driver;
    @Given("^User have data$")
    public void fetchData() throws Throwable {
        SourceData = Connection.GetListData(Connection.OpenPostgres(),
                Connection.ExcuteQuery(DataRepo.queryGetDataNonF2F()));
        reference_id = SourceData.get("reference_number");
        url = SourceData.get("url");
        dob = SourceData.get("dob");
        full_name = SourceData.get("full_name");
        email = SourceData.get("email");
        created_date = SourceData.get("created_date");

    }
    @And("^User access the Microsite$")
    public void accessTheMicrosite() throws Throwable {
        try {
            driver = new ChromeDriver();
            driver.get(url);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @And("User input valid DOB")
    public void getMicrositeData(){
        driver.manage().timeouts().implicitlyWait(Settings.getDefaultTimeOut(), TimeUnit.SECONDS);
        driver.manage().timeouts().pageLoadTimeout(180, TimeUnit.SECONDS);
        driver.manage().window().maximize();
        driver.findElement(By.xpath("//input")).sendKeys(dob);
        driver.findElement(By.xpath("//*[(text() = 'SUBMIT' or . = 'SUBMIT')]")).click();
        WebElement get_ref_id = (new WebDriverWait(driver,10).until(ExpectedConditions.presenceOfElementLocated(By.xpath("//div[@id='content']/app-asntbx-template/div/div/div/p[2]/span"))));
        WebElement get_full_name = driver.findElement(By.xpath("//div[@id='sign_canvas']/p[2]/b"));
        //WebElement get_email = driver.findElement(By.xpath("//div[@id='sign_canvas']/p[3]/b"));
        WebElement get_created_date = driver.findElement(By.xpath("//div[@id='sign_canvas']/p[4]/b"));
        UI_ref_id = get_ref_id.getText();
        UI_full_name = get_full_name.getText();
        //UI_email = get_email.getText();
        UI_created_date = get_created_date.getText();
        System.out.println("UI ref id:" + UI_ref_id );
        System.out.println(UI_full_name);
        System.out.println(UI_created_date);

    }
    @Then("^Validate value from Microsite with data from DB$")
    public void validateData(){
        Assert.assertEquals(reference_id,UI_ref_id);
        Assert.assertEquals(full_name, UI_full_name);
        //Assert.assertEquals(email, UI_email);
        Assert.assertEquals(created_date,UI_created_date);
    }

    @And("^User input invalid DOB 3 times$")
    public void inputInvalidDOB(){
        for(int i = 0; i <=3 ; i++) {
            driver.findElement(By.xpath("//input")).sendKeys("11121995");
            driver.findElement(By.xpath("//*[(text() = 'SUBMIT' or . = 'SUBMIT')]")).click();
        }
    }

    @Then("^User can not access the Microsite$")
    public void outputInvalidDOB(){
        WebElement attemptWarn = (new WebDriverWait(driver,10).until(ExpectedConditions.presenceOfElementLocated(By.xpath("//p[@nxcopytext = 'small' and (text() = 'Tautan yang Anda klik tidak valid. Silakan hubungi AllianzCare 1500136' or . = 'Tautan yang Anda klik tidak valid. Silakan hubungi AllianzCare 1500136')]"))));
        warn_text = attemptWarn.getAttribute("innerText");
        Assert.assertEquals("Tautan yang Anda klik tidak valid. Silakan hubungi AllianzCare 1500136", warn_text);
    }

    @And("^User submit without input sign$")
    public void submitTheFormWithoutSigning(){
        WebElement submit_btn = driver.findElement(By.xpath("//button[(text() = 'Ya' or . = 'Ya')]"));
        submit_btn.click();
    }

    @Then("^User can not submit the form$")
    public void unableToSubmit(){
        WebElement sign_warning = driver.findElement(By.xpath("//p[(text() = '* Tanda tangan di sini' or . = '* Tanda tangan di sini')]"));
        submit_warn_text = sign_warning.getAttribute("innerText");
        Assert.assertEquals("* Tanda tangan di sini", submit_warn_text);
    }

    @Given("^User have data for ASNTBX$")
    public void fetchASNTBXdata() throws Throwable{
        SourceData = Connection.GetListData(Connection.OpenPostgres(),
                Connection.ExcuteQuery(DataRepo.queryGetASNTBXData()));
        reference_id = SourceData.get("reference_number");
        url = SourceData.get("url");
        dob = SourceData.get("dob");
        full_name = SourceData.get("full_name");
        email = SourceData.get("email");
        created_date = SourceData.get("created_date");
    }

    @And("^User click Tidak Setuju$")
    public void clickTidakSetujuASNTBX(){
        WebElement btnTidak = (new WebDriverWait(driver,10).until(ExpectedConditions.presenceOfElementLocated(By.xpath("//button[(text() = 'TIDAK' or . = 'TIDAK')]"))));
        btnTidak.click();
    }

    @And("^Display pop up warning for ASNTBX$")
    public void warningForASNTBX(){
        WebElement btnTidak = (new WebDriverWait(driver,10).until(ExpectedConditions.presenceOfElementLocated(By.xpath("//div[@id='content']/app-asntbx-template/nx-modal/div[2]/div/div/div/div/div/div/div/p"))));
        warntextASNTBX = btnTidak.getAttribute("innerText");
        Assert.assertEquals("Terima kasih atas konfirmasi Anda, proses permohonan Asuransi Jiwa Anda sudah kami batalkan. Jika Anda membutuhkan bantuan atau berminat untuk mendapatkan perlindungan jiwa dari Allianz, silakan menghubungi AllianzCare 6221 1500 136 atau AllianzCare Syariah +6221 1500 139 atau melalui email +contactus@Allianz.co.id", warntextASNTBX);
        WebElement btnOk = (new WebDriverWait(driver,10).until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@type = 'button' and (text() = 'Ok' or . = 'Ok')]"))));
        btnOk.click();
    }




}
