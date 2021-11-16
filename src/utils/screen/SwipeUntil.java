package utils.screen;

import driver.DriverFactory;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.TouchAction;
import io.appium.java_client.touch.WaitOptions;
import io.appium.java_client.touch.offset.PointOption;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

public class SwipeUntil {

    public static void swipeUntilISee(AppiumDriver<MobileElement> androidDriver, By needFoundElement, int xStart, int xEnd, int yStart, int yEnd) {

        //Get mobile window size
        Dimension windowSize = androidDriver.manage().window().getSize();
        int screenHeight = windowSize.getHeight();
        int screenWidth = windowSize.getWidth();

        //Calculate touch point

        int xStartPoint = xStart * screenWidth / 100;
        int xEndPoint = xEnd * screenWidth / 100;
        int yStartPoint = yStart * screenHeight / 100;
        int yEndPoint = yEnd * screenHeight / 100;

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
        while (MAX_SWIPE_TIME > swipeTime) {

            List<MobileElement> matchedCards = androidDriver.findElements(needFoundElement);
            if (!matchedCards.isEmpty()) {
                break;
            } else {
                touchAction
                        .press(startPoint)
                        .waitAction(new WaitOptions().withDuration(Duration.ofSeconds(2)))
                        .moveTo(endPoint)
                        .release()
                        .perform();
                swipeTime++;
            }
        }

    }
}
