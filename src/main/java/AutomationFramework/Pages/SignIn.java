package AutomationFramework.Pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class SignIn {

//    element
    public WebDriver driver;

    @FindBy(name = "logid")
    public WebElement txt_Username;

    @FindBy(name = "pswd")
    public WebElement txt_pswd;

    @FindBy(xpath = "//input[@value='Login")
    public WebElement btn_Login;

    @FindBy(xpath = "//b[text()='Sorry we were unable to complete")
    public WebElement lbl_LoginErrorMsg;



//    constructor

    public SignIn(WebDriver wdriver){
        driver=wdriver;
        PageFactory.initElements(driver,this);
    }

//    Actions
    public void enterUserDetails(String username,String password){
        txt_Username.sendKeys(username);
        txt_pswd.sendKeys(password);
        btn_Login.click();
    }

    public Boolean isErrorMsgDisplayed(){
        return lbl_LoginErrorMsg.isDisplayed();


    }



}
