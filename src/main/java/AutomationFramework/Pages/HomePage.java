package AutomationFramework.Pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class HomePage {
    public WebDriver driver;

//    elements
    @FindBy(linkText = "Sign In")
    public WebElement lk_SingnIn;

    @FindBy(id = "username")
    public WebElement lbl_ValidLogin;

//    constructor
    public HomePage(WebDriver wdriver){
        driver=wdriver;
        PageFactory.initElements(driver,this);

    }

//    Actions

    public void click_SignIn(){
        lk_SingnIn.click();
    }

    public boolean isSuccessfulMsgDisplayed(){
        return lbl_ValidLogin.isDisplayed();
    }
}
