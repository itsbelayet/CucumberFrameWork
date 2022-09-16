package stepDefinition;

import io.cucumber.java.*;
import io.cucumber.java.en.*;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.apache.commons.io.FileUtils;
import org.apache.logging.log4j.LogManager;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import pageObject.BaseClass;
import pageObject.LoginPage;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class LoginPageStepDefinition extends BaseClass {
    @Before
    public void setup() throws IOException {
        //Logger initializing
        logger = LogManager.getLogger("LoginPageStepDefinition");

        //Properties Initializing
        prop = new Properties();
        FileInputStream fiStream = new FileInputStream("Config.properties");
        prop.load(fiStream);

        String browserName = prop.getProperty("browser");
//        String url= prop.getProperty("url");

        // Driver Setup
        if (browserName.contains("Chrome")) {
            WebDriverManager.chromedriver().setup();
            driver = new ChromeDriver();
        } else if (browserName.contains("FireFox")) {
            WebDriverManager.firefoxdriver().setup();
            driver = new FirefoxDriver();
        } else if (browserName.contains("ED")) {
            WebDriverManager.edgedriver().setup();
            driver = new EdgeDriver();
        }
    }

    @BeforeStep
    public void beforeStep() {
        System.out.println("This is Before Step");
    }

    @AfterStep
    public void afterStep() {
        System.out.println("This is After Step");
    }

    @After
    public void teardown(Scenario sc) throws IOException {
        //For Screen Shot
        if(sc.isFailed()==true){
            logger.info("Login Unsuccessful");  // For Log Message
            // For Screen Shot
            TakesScreenshot scrShot=((TakesScreenshot) driver);
            File createImage=scrShot.getScreenshotAs(OutputType.FILE);            //Create Image
            File saveToFile=new File(".\\Screenshot\\FailScreen.png");   //Save Image to File
            FileUtils.copyFile(createImage,saveToFile);
        }
        driver.close();
    }

    //----------------- ------------- Test Steps ------------- -------------

    @Given("User Launch browser")
    public void user_launch_browser() {
        loginPage = new LoginPage(driver);
        logger.info("Browser  ------- Launch");
    }

    @When("User opens URL {string}")
    public void user_opens_url(String url) {
        driver.get(url);
        logger.info("Open ========= URL");
    }

    @When("User enters Email as {string} and Password as {string}")
    public void user_enters_email_as_and_password_as(String email, String password) {
        loginPage.enterEmail(email);
        loginPage.enterPassword(password);
    }

    @When("Click on Login")
    public void click_on_login() {
        loginPage.clickButton();
        logger.info("Login Successful");
    }

    @Then("Page Title should be {string}")
    public void page_title_should_be(String pageTitle) {
        String actualPageTitle = driver.getTitle();
        Assert.assertEquals(actualPageTitle, pageTitle, "Not Match");
    }

    @When("User cannot Login")
    public void user_cannot_login() {
        logger.info("Login with Invalid Credential");
    }

    @When("User click on Logout link")
    public void user_click_on_logout_link() {
        loginPage.clickLogout();
        logger.info("Logout Successful");
    }

}
