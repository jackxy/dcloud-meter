package net.xdclass.util;

import net.xdclass.enums.BizCodeEnum;
import net.xdclass.enums.SeleniumByEnum;
import net.xdclass.enums.SeleniumWebDriverEnum;
import net.xdclass.exception.BizException;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.safari.SafariDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

/**
 * 小滴课堂,愿景：让技术不再难学
 *
 * @Description
 * @Author 二当家小D
 * @Remark 有问题直接联系我，源码-笔记-技术交流群
 * @Version 1.0
 **/
public class SeleniumFetchUtil {

    public static WebDriver getWebDriver(String driverName) {

        //获取系统类型
        String osName = System.getProperty("os.name");
        SeleniumWebDriverEnum seleniumWebDriverEnum = SeleniumWebDriverEnum.valueOf(driverName);
       return switch (seleniumWebDriverEnum){
            case CHROME -> {
                ChromeOptions options = new ChromeOptions();
                //--no-sandbox参数表示禁用沙箱模式，以提高浏览器的兼容性和稳定性。
                //--disable-dev-shm-usage参数表示禁用/dev/shm的使用，以避免在某些Linux系统中出现的内存不足问题。
                //--disable-extensions参数表示禁用所有扩展，以防止扩展影响浏览器的性能和稳定性。
                options.addArguments("--no-sandbox");
                options.addArguments("--disable-dev-shm-usage");
                options.addArguments("--disable-extensions");

                if(osName.contains("Mac")){
                    System.setProperty("webdriver.chrome.driver", "/Users/xdclass/Desktop/coding/chromedriver-mac-arm64/chromedriver");

                }else {
                    //生产环境Linux系统
                    options.addArguments("--headless");
                    System.setProperty("webdriver.chrome.driver", "/usr/bin/chromedriver");
                }
                System.setProperty("webdriver.chrome.logfile", "./chromedriver.log");
                WebDriver webDriver  = new ChromeDriver(options);
                //webDriver.manage().window().maximize();
                yield webDriver;
            }
            case SAFARI -> {
                WebDriver webDriver = new SafariDriver();
                //webDriver.manage().window().maximize();
                yield webDriver;
            }
            default -> {
                throw new BizException(BizCodeEnum.UI_UNSUPPORTED_BROWSER_DRIVER);
            }
        };
    }


    /**
     * 查找UI元素
     * @param locationType
     * @param locationExpress
     * @param waitTime
     * @return
     */
    public static WebElement findElement(String locationType,String locationExpress,Long waitTime) {
        WebDriver webDriver = SeleniumWebdriverContext.get();
        //配置元素等待时间
        WebDriverWait wait = new WebDriverWait(webDriver, Duration.ofMillis(waitTime));

        SeleniumByEnum seleniumByEnum = SeleniumByEnum.valueOf(locationType);
        return switch (seleniumByEnum){

            case XPATH -> wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(locationExpress)));

            case TAG_NAME ->
                wait.until(ExpectedConditions.presenceOfElementLocated(By.tagName(locationExpress)));
            case ID ->
                wait.until(ExpectedConditions.presenceOfElementLocated(By.id(locationExpress)));
            case NAME ->
                wait.until(ExpectedConditions.presenceOfElementLocated(By.name(locationExpress)));
            case CLASS_NAME ->
                wait.until(ExpectedConditions.presenceOfElementLocated(By.className(locationExpress)));
            case LINK_TEXT ->
                wait.until(ExpectedConditions.presenceOfElementLocated(By.linkText(locationExpress)));
            case CSS_SELECTOR ->
                wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector(locationExpress)));
            case PARTIAL_LINK_TEXT ->
                    wait.until(ExpectedConditions.presenceOfElementLocated(By.partialLinkText(locationExpress)));

            default -> throw new BizException(BizCodeEnum.UI_ELEMENT_NOT_EXIST);
        };

    }


}
