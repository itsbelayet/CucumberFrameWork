package pageObject;

//import org.apache.logging.log4j.core.Logger;
import org.openqa.selenium.WebDriver;
import org.apache.logging.log4j.*;

import java.util.Properties;

public class BaseClass {
    public WebDriver driver;
    public LoginPage loginPage;

    public Logger logger;
    public Properties prop;
}
