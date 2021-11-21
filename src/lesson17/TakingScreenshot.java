package lesson17;

import driver.DriverFactory;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.File;
import java.util.List;

public class TakingScreenshot {

    public static void main(String[] args) {
        DriverFactory.startAppiumServer();
        try {
            AppiumDriver<MobileElement> androidDriver = DriverFactory.getAndroidDriver();

            MobileElement loginLabel = androidDriver.findElementByAccessibilityId("Login");
            loginLabel.click();

            //Check until swipe screen appear.

            WebDriverWait webDriverWait = new WebDriverWait(androidDriver, 2L);
            MobileElement loginBtnElem = androidDriver.findElementByAccessibilityId("button-LOGIN");
            webDriverWait.until(ExpectedConditions.visibilityOf(loginBtnElem));

           //Taking whole screen
            File base64ScreenShotData = androidDriver.getScreenshotAs(OutputType.FILE);
            String fileLocation = System.getProperty("user.dir").concat("/screenshot/").concat("login.png");
            FileUtils.copyFile(base64ScreenShotData, new File(fileLocation));

            //Taking elements screenshot
            File base64LoginBtnData = loginBtnElem.getScreenshotAs(OutputType.FILE);
            String loginBtnImgFileLocation = System.getProperty("user.dir").concat("/screenshot/").concat("loginBtnImg.png");
            FileUtils.copyFile(base64LoginBtnData, new File(loginBtnImgFileLocation));

            //Taking area screenshot
            List<MobileElement> viewGroupElems = androidDriver.findElementsByXPath("//android.view.ViewGroup/android.view.ViewGroup[2]");
            if(!viewGroupElems.isEmpty()){

                File baseViewGroupData = viewGroupElems.get(viewGroupElems.size() -1 ).getScreenshotAs(OutputType.FILE);
                String viewGroupFileLocation = System.getProperty("user.dir").concat("/screenshot/").concat("viewGroup.png");
                FileUtils.copyFile(baseViewGroupData, new File(viewGroupFileLocation));
            }

        }catch (Exception e){
            e.printStackTrace();
        }finally {
            DriverFactory.stopAppiumServer();
        }
    }

}
