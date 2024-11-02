package com.hrm.utils;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.UnexpectedTagNameException;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;
import java.util.List;
import java.util.Set;
import static com.hrm.base.BaseClass.*;


public class CommonMethods {
    /**
     * This method clears then inserts input
     *
     * @param element
     * @param text
     */
    public static void sendText(WebElement element, String text) {
        element.clear();
        element.sendKeys(text);
    }

    /**
     * This method will check if radio/checkbox is enabled then selects it. Retrieves element by "value".
     *
     * @param radioOrCheckbox
     * @param expectedValue
     */
    public static void clickRadioOrCheckbox(List<WebElement> radioOrCheckbox, String expectedValue) {
        for (WebElement element : radioOrCheckbox) {
            String actualValue = element.getAttribute("value");
            if (element.isEnabled() && actualValue.equals(expectedValue)) {
                element.click();
                break;
            }
        }
    }

    /**
     * This method will check if radio/checkbox is enabled then selects it. Retrieves element by "value".
     *
     * @param radioOrCheckbox
     * @param expectedValue
     */
    public static void clickRadioOrCheckboxByText(List<WebElement> radioOrCheckbox, String expectedValue) {
        for (WebElement element : radioOrCheckbox) {
            String actualValue = element.getText();
//            if (element.isDisplayed() && !element.isEnabled()) {
//
//            }
            if (actualValue.contains(expectedValue)) {
                element.click();
                break;
            }
        }
    }

    /**
     * Method will check if radio/checkbox is enabled and selects element based on given text using JavascriptExecutor.
     *
     * @param radioOrCheckbox
     * @param expectedValue
     */
    public static void clickRadioOrCheckboxJS(List<WebElement> radioOrCheckbox, String expectedValue) {
        JavascriptExecutor jse = (JavascriptExecutor) driver;
        for (WebElement element : radioOrCheckbox) {
            if (element.isEnabled() && element.getText().equals(expectedValue)) {
                jse.executeScript("arguments[0].click()", element);
                break;
            }
        }
    }


    /**
     * Method looks for dropdown WebElement by visible text and if found selects it.
     *
     * @param element
     * @param visibleText
     */
    public static void selectDdValue(WebElement element, String visibleText) {
        try {
            Select select = new Select(element);
            List<WebElement> options = select.getOptions();
            for (WebElement el : options) {
                if (el.getText().equals(visibleText)) {
                    //el.click(); // <-- this can be used as well
                    select.selectByVisibleText(visibleText);
                    break;
                }
            }
        } catch (UnexpectedTagNameException e) {
            e.printStackTrace();
        }
    }

    /**
     * Method looks for dropdown WebElement by using index and if found selects it.
     *
     * @param element
     * @param index
     */
    public static void selectDdValue(WebElement element, int index) {
        try {
            Select select = new Select(element);
            List<WebElement> options = select.getOptions();
            for (WebElement el : options) {
                if (index > options.size()) {
                    throw new IndexOutOfBoundsException("Entered index is either negative or larger than size of the existing array, enter index within boundaries");
                }
                if (el == options.get(index)) {
                    //el.click(); // <-- this can be used as well
                    select.selectByIndex(index);
                    break;
                }
            }
        } catch (UnexpectedTagNameException e) {
            e.printStackTrace();
        }
    }

    /**
     * Method switches to and accepts an alert. Throws exception if alert is not found.
     */
    public static void acceptAlert() {
        try {
            Alert alert = driver.switchTo().alert();
            alert.accept();
        } catch (NoAlertPresentException e) {
            e.printStackTrace();
        }
    }

    /**
     * Method switches to and dismisses an alert. Throws exception if alert is not found.
     */
    public static void dismissAlert() {
        try {
            Alert alert = driver.switchTo().alert();
            alert.dismiss();
        } catch (NoAlertPresentException e) {
            e.printStackTrace();
        }
    }

    /**
     * Method switches to and gets text from an alert. Throws exception if alert is not found.
     */
    public static String getAlertText() {
        try {
            return driver.switchTo().alert().getText();
        } catch (NoAlertPresentException e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * Method will switch to alert and send text as input. If alert is not found, throws exception.
     *
     * @param text
     */
    public static void sendAlertText(String text) {
        try {
            Alert alert = driver.switchTo().alert();
            alert.sendKeys(text);
        } catch (NoAlertPresentException e) {
            e.printStackTrace();
        }
    }

    /**
     * Method switches to frame by name or ID value. If frame is not found, throws exception.
     *
     * @param nameOrId
     */
    public static void switchToFrame(String nameOrId) {
        try {
            driver.switchTo().frame(nameOrId);
        } catch (NoSuchFrameException e) {
            e.printStackTrace();
        }
    }

    /**
     * Method switches to frame by index. If frame is not found, throws exception.
     *
     * @param index
     */
    public static void switchToFrame(int index) {
        try {
            driver.switchTo().frame(index);
        } catch (NoSuchFrameException e) {
            e.printStackTrace();
        }
    }

    /**
     * Method switches to frame by web element. If frame is not found, throws exception.
     *
     * @param element
     */
    public static void switchToFrame(WebElement element) {
        try {
            driver.switchTo().frame(element);
        } catch (NoSuchFrameException e) {
            e.printStackTrace();
        }
    }

    /**
     * Method switches focus to child window or tab
     */
    public static void switchToChildWindowOrTab() {
        String mainWindow = driver.getWindowHandle();
        Set<String> windows = driver.getWindowHandles();
        for (String windowOrTab : windows) {
            if (!windowOrTab.equals(mainWindow)) {
                driver.switchTo().window(windowOrTab);
                break;
            }
        }
    }

    /**
     * This method simply calls WebDriverWait and specifies time from constant calss
     *
     * @return
     */
    public static WebDriverWait explicitWait() {
        return new WebDriverWait(driver, Duration.ofSeconds(Constants.EXPLICIT_WAIT_TIME));
    }

    /**
     * Method waits for element to become present
     * @param locator
     * @return
     */
    public static WebElement waitForPresenceOfElement(By locator) {
        return explicitWait().until(ExpectedConditions.presenceOfElementLocated(locator)); // This line works in original project (SeleniumBatchVI, but not here.??)
    }

    /**
     * Method explicitly waits for web element to be visible (No Action is taken)
     *
     * @param element
     */
    public static void waitForVisibility(WebElement element) {
        explicitWait().until(ExpectedConditions.visibilityOf(element));
    }

    /**
     * Method explicitly waits for web element to be clickable (Does Not click on it)
     *
     * @param element
     */
    public static WebElement waitForClickability(WebElement element) {
        return explicitWait().until(ExpectedConditions.elementToBeClickable(element));
    }

    /**
     * Method explicitly waits for the web element to be clickable then clicks on it.
     *
     * @param element
     */
    public static WebElement click_waitForClickability(WebElement element) {
        waitForClickability(element);
        element.click();
        return element;
    }

    /**
     * Method explicitly waits for the web element to be visible then clicks on it.
     *
     * @param element
     */
    public static WebElement click_waitForVisibility(WebElement element) {
        waitForVisibility(element);
        element.click();
        return element;
    }

    public static JavascriptExecutor getJsObject() {
        return (JavascriptExecutor) driver;
    }

    public static void jsClick(WebElement element) {
        getJsObject().executeScript("arguments[0].click()", element);
    }

    public static void scrollToElement(WebElement element) {
        getJsObject().executeScript("arguments[0].scrollIntoView(true);", element);
    }

    public static void scrollDown(int pixel) {
        getJsObject().executeScript("window.scrollBy(0," + pixel + ")");
    }

    public static void scrollUp(int pixel) {
        getJsObject().executeScript("window.scrollBy(0,-" + pixel + ")");
    }

    /**
     * Method waits for given time in seconds. Based on java Thread.sleep() method.
     *
     * @param seconds
     */
    public static void waitInSeconds(int seconds) {
        try {
            Thread.sleep(seconds * 1000L);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    /**
     * Method will take screenshot of specified element and saves it in provided destination filePath
     * @param element pass element which you want screenshot of.
     * @param destinationFileName taken screenshot will be saved in this destination, give it a name
     *                            (default extension is png can be changed to jpeg or others from CommonMethods where it is located).
     */
    public static String takeScreenshot(WebElement element, String destinationFileName) {
        File sourceFile = element.getScreenshotAs(OutputType.FILE);
        String destinationFile = Constants.SCREENSHOT_FILEPATH + destinationFileName + "_" + timeStamp() + ".png";
        try {
            FileUtils.copyFile(sourceFile, new File(destinationFile));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return destinationFile;
    }

    /**
     * This method overload is for older (v3) Selenium for use in Listener where it requires only one String parameter.
     * @param destinationFileName provide a name to destination file
     * @return will return destination file as String format
     */
    public static String takeScreenshot(String destinationFileName) {
        TakesScreenshot takesScreenshot = (TakesScreenshot)driver;
        File sourceFile = takesScreenshot.getScreenshotAs(OutputType.FILE);
        String destinationFile = Constants.SCREENSHOT_FILEPATH + destinationFileName + "_" + timeStamp() + ".jpeg";
        try {
            FileUtils.copyFile(sourceFile, new File(destinationFile));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return destinationFile;
    }

    static public String timeStamp() {
        return new SimpleDateFormat("yyyy-MM-dd_HH_mm_ss_SSS").format(new Date());
    }

}
