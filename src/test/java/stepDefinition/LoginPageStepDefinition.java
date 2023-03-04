package stepDefinition;

import io.cucumber.java.*;
import io.cucumber.java.en.*;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.apache.commons.io.FileUtils;
import org.apache.logging.log4j.LogManager;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import pageObject.AddCustomersPage;
import pageObject.BaseClass;
import pageObject.LoginPage;
import pageObject.SearchCustomerPage;

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
            driver.manage().window().maximize();
        } else if (browserName.contains("FireFox")) {
            WebDriverManager.firefoxdriver().setup();
            driver = new FirefoxDriver();
        } else if (browserName.contains("ED")) {
            WebDriverManager.edgedriver().setup();
            driver = new EdgeDriver();
        }
    }

    @After
    public void teardown(Scenario sc) {
        System.out.println("Tear Down method executed..");
        if (sc.isFailed() == true) {
            //Convert web driver object to TakeScreenshot
            String fileWithPath = "D:\\PeopleNtech\\CucumberFramework\\Screenshot\\failedScreenshot.png";
            TakesScreenshot scrShot = ((TakesScreenshot) driver);

            //Call getScreenshotAs method to create image file
            File SrcFile = scrShot.getScreenshotAs(OutputType.FILE);

            //Move image file to new destination
            File DestFile = new File(fileWithPath);

            //Copy file at destination

            try {
                FileUtils.copyFile(SrcFile, DestFile);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        driver.quit();
    }

    @BeforeStep
    public void beforeStep() {
        System.out.println("This is Before Step");
    }

    @AfterStep          // For Screen Shot
    public void afterStep(Scenario sc) {
        System.out.println("After Step method executed..");
        if (sc.isFailed()) {
            final byte[] screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
            sc.attach(screenshot, "image/png", sc.getName());
        }
    }

   /* @After
    public void teardown() {
//        //For Screen Shot
//        if(sc.isFailed()==true){
//            logger.info("Login Unsuccessful");  // For Log Message
//            // For Screen Shot
//            TakesScreenshot scrShot=((TakesScreenshot) driver);
//            File createImage=scrShot.getScreenshotAs(OutputType.FILE);            //Create Image
//            File saveToFile=new File(".\\Screenshot\\FailScreen.png");   //Save Image to File
//            FileUtils.copyFile(createImage,saveToFile);
//        }
        driver.close();
    }
*/
    //----------------- ------------- Test Steps ------------- -------------

    @Given("User Launch browser")
    public void user_launch_browser() {
        loginPage = new LoginPage(driver);
        addCustPg = new AddCustomersPage(driver);
        searchCustPg = new SearchCustomerPage(driver);
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
        logger.info("Entered email address and password");
    }

    @When("Click on Login")
    public void click_on_login() {
        loginPage.clickButton();
        logger.info("Clicked on login button");
    }

    @Then("Page Title should be {string}")
    public void page_title_should_be(String pageTitle) {
        String actualPageTitle = driver.getTitle();
        Assert.assertEquals(actualPageTitle, pageTitle, "Page Title not Matched");
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

    ///////////////////////////Add new customer/////////////////////
    @Then("User can view Dashboad")
    public void user_can_view_dashboad() {
        String actualTitle = addCustPg.getPageTitle();
        String expectedTitle = "Dashboard / nopCommerce administration";

        if (actualTitle.equals(expectedTitle)) {
            logger.info("user can view dashboard test passed.");
            Assert.assertTrue(true);
        } else {
            Assert.assertTrue(false);
            logger.info("user can view dashboard test failed.");
        }
    }

    @When("User click on customers Menu")
    public void user_click_on_customers_menu() {
        addCustPg.clickOnCustomersMenu();
        logger.info("customer menu clicked");
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @When("click on customers Menu Item")
    public void click_on_customers_menu_item() {
        addCustPg.clickOnCustomersMenuItem();
        logger.info("customer menu item clicked");
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @When("click on Add new button")
    public void click_on_add_new_button() {
        addCustPg.clickOnBtnnew();
        logger.info("clicked on add new button.");
    }

    @Then("User can view Add new customer page")
    public void user_can_view_add_new_customer_page() {
        String actualTitle = addCustPg.getPageTitle();
        String expectedTitle = "Add a new customer / nopCommerce administration";

        if (actualTitle.equals(expectedTitle)) {
            logger.info("User can view Add new customer page- passed");

            Assert.assertTrue(true);//pass
        } else {
            logger.info("User can view Add new customer page- failed");

            Assert.assertTrue(false);//fail
        }
    }

    @When("User enter customer info")
    public void user_enter_customer_info() {
        //addNewCustPg.enterEmail("cs129@gmail.com");
        addCustPg.enterEmail(generateEmailId() + "@gmail.com");
        addCustPg.enterPassword("test1");
        addCustPg.enterFirstName("Prachi");
        addCustPg.enterLastName("Gupta");
        addCustPg.enterGender("Female");
        addCustPg.enterDob("6/13/1988");
        addCustPg.enterCompanyName("CodeStudio");
        addCustPg.enterAdminContent("Admin content");
        addCustPg.enterManagerOfVendor("Vendor 1");

        logger.info("customer information entered");
    }

    @When("click on Save button")
    public void click_on_save_button() {
        addCustPg.clickOnSave();
        logger.info("clicked on save button");
    }

    @Then("User can view confirmation message {string}")
    public void user_can_view_confirmation_message(String exptectedConfirmationMsg) {
        String bodyTagText = driver.findElement(By.tagName("Body")).getText();
        if (bodyTagText.contains(exptectedConfirmationMsg)) {
            Assert.assertTrue(true);//pass
            logger.info("User can view confirmation message - passed");
        } else {
            logger.warn("User can view confirmation message - failed");

            Assert.assertTrue(false);//fail
        }
    }

    ////////////Search Customer//////////////////////////
    @When("Enter customer EMail")
    public void enter_customer_e_mail() {
        logger.info("Email address entered   *** Before");
        searchCustPg.enterEmailAdd("victoria_victoria@nopCommerce.com");
        logger.info("Email address entered");
    }

    @When("Click on search button")
    public void click_on_search_button() {
        searchCustPg.clickOnSearchButton();
        logger.info("Clicked on search button.");

        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Then("User should found Email in the Search table")
    public void user_should_found_email_in_the_search_table() {
        String expectedEmail = "victoria_victoria@nopCommerce.com";

        //   Assert.assertTrue(SearchCustPg.searchCustomerByEmail(expectedEmail));

        if (searchCustPg.searchCustomerByEmail(expectedEmail) == true) {
            Assert.assertTrue(true);
            logger.info("User should found Email in the Search table - passed");

        } else {
            logger.info("User should found Email in the Search table - passed");
            Assert.assertTrue(false);
        }
    }

    ///////////////search customer by name////////////////////


    @When("Enter customer FirstName")
    public void enter_customer_first_name() {
        logger.info("Enter First Name before action");
        searchCustPg.enterFirstName("Victoria");
        logger.info("Enter First Name after action");
    }

    @When("Enter customer LastName")
    public void enter_customer_last_name() {
        searchCustPg.enterLastName("Terces");
        logger.info("Enter Last Name");
    }

    @Then("User should found Name in the Search table")
    public void user_should_found_name_in_the_search_table() {
        String expectedName = "Victoria Terces";

        if (searchCustPg.searchCustomerByName(expectedName) == true) {
            Assert.assertTrue(true);
        } else
            Assert.assertTrue(false);

    }
}
