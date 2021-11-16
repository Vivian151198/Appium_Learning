package lesson15;

import driver.DriverFactory;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileBy;
import io.appium.java_client.MobileElement;
import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.touch.WaitOptions;
import io.appium.java_client.touch.offset.PointOption;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.awt.*;
import java.time.Duration;

public class SwipeVertically {

    public static void main(String[] args) {
        DriverFactory.startAppiumServer();
        try {
            AppiumDriver<MobileElement> androidDriver = DriverFactory.getAndroidDriver();

            MobileElement formLabelElem = androidDriver.findElementByAccessibilityId("Forms");
            formLabelElem.click();

            //Check until forms screen appear.

            WebDriverWait webDriverWait = new WebDriverWait(androidDriver, 30L);
            webDriverWait.until(ExpectedConditions.visibilityOf(androidDriver.findElementByAccessibilityId("switch")));

            //Get mobile window size
            Dimension windowSize = androidDriver.manage().window().getSize();
            int screenHeight = windowSize.getHeight();
            int screenWidth = windowSize.getWidth();

            //Calculate touch point

            int xStartPoint = 50*screenWidth/100;
            int xEndPoint = 50*screenWidth/100;
            int yStartPoint = 90*screenHeight/100;
            int yEndPoint = 10*screenHeight/100;

            //Convert to PointOption - Coordinates

            PointOption startPoint = new PointOption().withCoordinates(xStartPoint, yStartPoint);
            PointOption endPoint = new PointOption().withCoordinates(xEndPoint, yEndPoint);

            //Perform touch action
            TouchAction touchAction = new TouchAction(androidDriver);

            //Swipe up
            touchAction
                    .press(startPoint)
                    .waitAction(new WaitOptions().withDuration(Duration.ofSeconds(2)))
                    .moveTo(endPoint)
                    .release()
                    .perform();

            MobileElement activeBtnElem = androidDriver.findElementByAccessibilityId("button-Active");
            activeBtnElem.click();

        }catch (Exception e){
            e.printStackTrace();
        }finally {
            DriverFactory.stopAppiumServer();
        }
    }
}
