package lesson14;

import driver.DriverFactory;
import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;

import java.util.List;

public class DriverFactoryTest {
    public static void main(String[] args) {

        DriverFactory.startAppiumServer();
        try {

            AndroidDriver<MobileElement> androidDriver = DriverFactory.getAndroidDriver();
            MobileElement loginLabel = androidDriver.findElementByAccessibilityId("Login");
            loginLabel.click();
/**
 * If Xpath wrong => List will empty not show error "No such element".
 */
            List<MobileElement> credFormElems = androidDriver.findElementsByXPath("//android.widget.EditText");
            int INPUT_EMAIL_INDEX = 0;
            int INPUT_PASSWORD_INDEX = 1;
            credFormElems.get(0).sendKeys("van@gmail.com");
            credFormElems.get(1).sendKeys("12345");


//            MobileElement emailInputElem = androidDriver.findElementByXPath("//android.widget.EditText[@content-desc='input-email']");
//            MobileElement passwordInputElem = androidDriver.findElementByXPath("//android.widget.EditText[@content-desc='input-password']");
            MobileElement loginBtnElem = androidDriver.findElementByAccessibilityId("button-LOGIN");
            loginBtnElem.click();

//            emailInputElem.sendKeys("abc@gmail.com");
//            passwordInputElem.sendKeys("12345678");
            //Some Xpath
            /**
             * //*[contains(@text, 'When the device')]
             * new UiSelector().textContains("When the device").className("android.widget.TextView")
             */
            MobileElement loginFeatureDesElem = androidDriver.findElementByXPath("//*[contains(@text, 'When the device')]");
            String loginFeatureDes = loginFeatureDesElem.getText();
            System.out.println(loginFeatureDes);

            MobileElement loginFeatureDesElemUiSelector = androidDriver.findElementByAndroidUIAutomator("new UiSelector().textContains(\"When the device\").className(\"android.widget.TextView\")");
            String loginFeatureDesViaUi = loginFeatureDesElemUiSelector.getText();
            System.out.println(loginFeatureDesViaUi);

            List<MobileElement> matchesCard;
            matchesCard = androidDriver.findElementsByAccessibilityId("WebdriverIO logo");


        } catch (Exception ignored) {
        } finally {

            DriverFactory.stopAppiumServer();
        }

    }
}
