package lesson16;

import driver.DriverFactory;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import org.openqa.selenium.WebElement;

import java.util.List;

public class HandleHypericContext {

    public static void main(String[] args) {

        DriverFactory.startAppiumServer();
        try {
            AppiumDriver<MobileElement> appiumDriver = DriverFactory.getAndroidDriver();

            MobileElement webViewLabelElem = appiumDriver.findElementByAccessibilityId("Webview");
            webViewLabelElem.click();

//            appiumDriver.getContextHandles().forEach(context -> {
//                System.out.println(context);
//            });

        appiumDriver.context("WEBVIEW_com.wdiodemoapp");
        WebElement naviToggleBtnElem = appiumDriver.findElementByCssSelector(".navbar__toggle");
        naviToggleBtnElem.click();

        List<MobileElement> mobileListItemElems = appiumDriver.findElementsByCssSelector(".menu__list-item");
            for (MobileElement mobileListItem : mobileListItemElems) {
                System.out.println("Text:" + mobileListItem);
            }

        appiumDriver.context("NATIVE_APP");
            MobileElement loginLabelElem = appiumDriver.findElementByAccessibilityId("Login");
            loginLabelElem.click();

        }catch (Exception e){
            e.printStackTrace();
        }finally {
            DriverFactory.stopAppiumServer();
        }
    }
}
