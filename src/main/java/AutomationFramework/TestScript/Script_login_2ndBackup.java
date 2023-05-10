package AutomationFramework.TestScript;

import AutomationFramework.Commons.Base1;
import AutomationFramework.DataProviders.Dp_Login;
import AutomationFramework.Pages.HomePage;
import AutomationFramework.Pages.SignIn;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.Status;
import org.testng.annotations.Test;

import java.io.IOException;

public class Script_login_2ndBackup extends Base1 {

    @Test(dataProvider ="validLogin",dataProviderClass = Dp_Login.class,groups = {"Smoke","Reg"})
    public void valid_Login(String TC_ID,String scriptname,String username,String password) throws IOException {

        HomePage homePage = new HomePage(driver);
        SignIn signIn = new SignIn(driver);

        mytest = extent.createTest(TC_ID);

        mytest.log(Status.PASS,"succefully launched application");


//        click to sign in
        homePage.click_SignIn();

//        enter login details
       signIn.enterUserDetails(username,password);
       mytest.log(Status.PASS,"succefully entered login details");

        boolean login_username = homePage.isSuccessfulMsgDisplayed();
        if (login_username){
            System.out.println(TC_ID+"pass");
            mytest.log(Status.PASS, TC_ID + "succefuly passed" );
        }else {
            System.out.println(TC_ID+"fail");
            mytest.log(Status.PASS,TC_ID +"failed", MediaEntityBuilder.createScreenCaptureFromPath(getScreenshot().replace(System.getProperty("user.dir"),"")).build());
        }

    }

    @Test(dataProvider = "invalidlogin",dataProviderClass = Dp_Login.class,groups = {"Reg","Prod","Dev"})
    public void invalid_Login(String TC_ID,String scriptname,String username,String password) throws IOException {

        HomePage homePage = new HomePage(driver);
        SignIn signIn = new SignIn(driver);

        mytest = extent.createTest(TC_ID);


        //        click to sign in
        homePage.click_SignIn();
        mytest.log(Status.PASS,"succefully launched application");

//        enter login details
        signIn.enterUserDetails(username,password);
        mytest.log(Status.PASS,"succefuly entered logi details");


        boolean displayed = signIn.isErrorMsgDisplayed();
        if (displayed){
            System.out.println(TC_ID+"pass");
            mytest.log(Status.PASS,TC_ID+"succefuly passed");
        }else {
            System.out.println(TC_ID+"fail");
            mytest.log(Status.PASS,TC_ID+"failed",MediaEntityBuilder.createScreenCaptureFromPath(getScreenshot().replace(System.getProperty("user.dir"),"")).build());
        }


    }

}
