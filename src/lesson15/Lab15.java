package lesson15;

import driver.DriverFactory;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import utils.screen.SwipeUntil;

public class Lab15 {

    public static void main(String[] args) {
        DriverFactory.startAppiumServer();
        try {
            AppiumDriver<MobileElement> androidDriver = DriverFactory.getAndroidDriver();
            By needFoundElem = By.xpath("//*[@text='You found me!!!']");
            int xStart = 50;
            int xEnd = 50;
            int yStart = 90;
            int yEnd = 10;

            MobileElement swipeLabelElem = androidDriver.findElementByAccessibilityId("Swipe");
            swipeLabelElem.click();

            //Check until swipe screen appear.

            WebDriverWait webDriverWait = new WebDriverWait(androidDriver, 2L);
            webDriverWait.until(ExpectedConditions.visibilityOf(androidDriver.findElementByXPath("//*[@text='Swipe horizontal']")));
            SwipeUntil.swipeUntilISee(androidDriver, needFoundElem, xStart, xEnd, yStart, yEnd);

        }catch (Exception e){
            e.printStackTrace();
        }finally {
            DriverFactory.stopAppiumServer();
        }

    }
}
