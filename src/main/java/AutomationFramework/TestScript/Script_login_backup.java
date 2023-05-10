package AutomationFramework.TestScript;

import AutomationFramework.Commons.Base1;
import AutomationFramework.DataProviders.Dp_Login;
import org.openqa.selenium.By;
import org.testng.annotations.Test;

public class Script_login_backup extends Base1 {
    @Test(dataProvider ="validlogin",dataProviderClass = Dp_Login.class)
    public void valid_Login(String TC_ID,String scriptname,String username,String password){
//        click to sign in
        driver.findElement(By.linkText("Sign In")).click();

//        enter login details
        driver.findElement(By.name("logid")).sendKeys(username);
        driver.findElement(By.name("pswd")).sendKeys(password);
        driver.findElement(By.xpath("//input[@value='Login']")).click();

        boolean login_username = driver.findElement(By.id("username")).isDisplayed();
        if (login_username){
            System.out.println(TC_ID+"pass");
        }else {
            System.out.println(TC_ID+"fail");
        }

    }

    @Test(dataProvider = "invalidlogin",dataProviderClass = Dp_Login.class)
    public void invalid_Login(String TC_ID,String scriptname,String username,String password){
        //        click to sign in
        driver.findElement(By.linkText("Sign In")).click();

//        enter login details
        driver.findElement(By.name("logid")).sendKeys(username);
        driver.findElement(By.name("pswd")).sendKeys(password);
        driver.findElement(By.xpath("//input[@value='Login']")).click();

        boolean displayed = driver.findElement(By.xpath("//b[text()='Sorry we were unable to complete")).isDisplayed();
        if (displayed){
            System.out.println(TC_ID+"pass");
        }else {
            System.out.println(TC_ID+"fail");
        }


    }

}


