package lesson15;

import driver.DriverFactory;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.TouchAction;
import io.appium.java_client.touch.WaitOptions;
import io.appium.java_client.touch.offset.PointOption;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

public class SwipeHorizontallyUntil {

    public static void main(String[] args) {
        DriverFactory.startAppiumServer();
        try {
            AppiumDriver<MobileElement> androidDriver = DriverFactory.getAndroidDriver();

            MobileElement swipeLabelElem = androidDriver.findElementByAccessibilityId("Swipe");
            swipeLabelElem.click();

            //Check until swipe screen appear.

            WebDriverWait webDriverWait = new WebDriverWait(androidDriver, 2L);
            webDriverWait.until(ExpectedConditions.visibilityOf(androidDriver.findElementByXPath("//*[@text='Swipe horizontal']")));

            //Get mobile window size
            Dimension windowSize = androidDriver.manage().window().getSize();
            int screenHeight = windowSize.getHeight();
            int screenWidth = windowSize.getWidth();

            //Calculate touch point

            int xStartPoint = 90*screenWidth/100;
            int xEndPoint = 10*screenWidth/100;
            int yStartPoint = 50*screenHeight/100;
            int yEndPoint = yStartPoint;

            //Convert to PointOption - Coordinates

            PointOption startPoint = new PointOption().withCoordinates(xStartPoint, yStartPoint);
            PointOption endPoint = new PointOption().withCoordinates(xEndPoint, yEndPoint);

            //Perform touch action
            TouchAction touchAction = new TouchAction(androidDriver);

            touchAction
                    .press(startPoint)
                    .waitAction(new WaitOptions().withDuration(Duration.ofSeconds(2)))
                    .moveTo(endPoint)
                    .release()
                    .perform();
            int MAX_SWIPE_TIME = 5;
            int swipeTime = 0;
            while (MAX_SWIPE_TIME > swipeTime){

                List<MobileElement> matchedCards = androidDriver.findElementsByXPath("//*[@text='EXTENDABLE']");
                if(!matchedCards.isEmpty()){
                    break;
                }
                else{
                    touchAction
                            .press(startPoint)
                            .waitAction(new WaitOptions().withDuration(Duration.ofSeconds(2)))
                            .moveTo(endPoint)
                            .release()
                            .perform();
                    swipeTime++;
                }
            }

        }catch (Exception e){
            e.printStackTrace();
        }finally {
            DriverFactory.stopAppiumServer();
        }
    }
}
