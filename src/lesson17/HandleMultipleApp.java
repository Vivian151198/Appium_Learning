package lesson17;

import driver.DriverFactory;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.TouchAction;
import io.appium.java_client.touch.WaitOptions;
import io.appium.java_client.touch.offset.PointOption;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;

import java.time.Duration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class HandleMultipleApp {

    public static void main(String[] args) {
        DriverFactory.startAppiumServer();
        try {
            AppiumDriver<MobileElement> androidDriver = DriverFactory.getAndroidDriver();

            MobileElement loginLabel = androidDriver.findElementByAccessibilityId("Login");
            loginLabel.click();

            // Put webdriverio demo app into background
            androidDriver.runAppInBackground(Duration.ofSeconds(-1));

            //Open setting app
            androidDriver.activateApp("com.android.settings");

            //Turn on and turn off wifi

            MobileElement networkAndInternetElem = androidDriver.findElementByXPath("//*[@text='Network & internet']");
            networkAndInternetElem.click();
            MobileElement wifiSwitchBtnElem = androidDriver.findElement(By.id("com.android.settings:id/switchWidget"));
            boolean isWifiOn = wifiSwitchBtnElem.getText().equals("ON");
            if(isWifiOn){
                //Turn off wifi
                wifiSwitchBtnElem.click();
                //Turn on wifi
                wifiSwitchBtnElem.click();
            }

            //Open webdriverio demo app
            androidDriver.activateApp("com.wdiodemoapp");

        }catch (Exception e){
            e.printStackTrace();
        }finally {
            DriverFactory.stopAppiumServer();
        }
    }

}
