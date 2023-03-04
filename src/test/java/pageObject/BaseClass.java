package pageObject;

//import org.apache.logging.log4j.core.Logger;

import org.apache.commons.lang3.RandomStringUtils;
import org.openqa.selenium.WebDriver;
import org.apache.logging.log4j.*;

import java.util.Properties;

public class BaseClass {
    public static WebDriver driver;
    public LoginPage loginPage;
    public AddCustomersPage addCustPg;
    public static Logger logger;
    public Properties prop;

    //====
    public SearchCustomerPage searchCustPg;
//    public ReadConfig readConfig;
    public String generateEmailId() {
        return (RandomStringUtils.randomAlphabetic(5));
    }
}
