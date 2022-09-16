package pageObject;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginPage extends BaseClass{
    public LoginPage(WebDriver rdriver) {
        driver = rdriver;
        PageFactory.initElements(driver, this);
    }

    @FindBy(id = "Email")
    WebElement email;

    @FindBy(id = "Password")
    WebElement password;

    @FindBy(xpath = "//button[@class=\"button-1 login-button\"]")
    WebElement loginButton;

    @FindBy(linkText = "Logout")
    WebElement logOut;

    public void enterEmail(String emailAdd){
        email.clear();
        email.sendKeys(emailAdd);
    }
    public void enterPassword(String pass){
        password.clear();
        password.sendKeys(pass);
    }
    public void clickButton(){
        loginButton.click();
    }

    public void clickLogout(){
        logOut.click();
    }

}
