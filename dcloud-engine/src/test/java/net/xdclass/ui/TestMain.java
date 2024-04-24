package net.xdclass.ui;

import cn.hutool.core.util.IdUtil;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.safari.SafariDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.File;
import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

/**
 * 小滴课堂,愿景：让技术不再难学
 *
 * @Description
 * @Author 二当家小D
 * @Remark 有问题直接联系我，源码-笔记-技术交流群
 * @Version 1.0
 **/
public class TestMain {
    /**
     * 主方法入口
     * @param args 命令行参数
     */
    public static void main(String[] args) throws Exception {
        // 创建一个Safari驱动实例
        //WebDriver webDriver = new SafariDriver();
        System.setProperty("webdriver.chrome.driver", "/Users/xdclass/Desktop/coding/chromedriver-mac-arm64/chromedriver");
        ChromeOptions options = new ChromeOptions();
        // 允许所有请求
        options.addArguments("--remote-allow-origins=*");
        WebDriver webDriver = new ChromeDriver(options);

        // 最大化浏览器窗口
        webDriver.manage().window().maximize();
        // 打开"https://xdclass.net"网址
        //webDriver.get("https://xdclass.net");
        //idTest(webDriver);
        //nameTest(webDriver);
        //linkTest(webDriver);
        //partialLinkTest(webDriver);
        //classTest(webDriver);
        //xpathTest(webDriver);
        //cssTest(webDriver);
        //webEleTest(webDriver);
        //mouseTest(webDriver);
        //windowTest(webDriver);
        //screenshotTest(webDriver);
        waitTest(webDriver);
    }


    public static void waitTest(WebDriver webDriver)throws InterruptedException {
        //启动需要打开的网页
        webDriver.get("https://www.baidu.com");

        // 创建显式等待对象，设置最大等待时间为10秒
        WebDriverWait wait = new WebDriverWait(webDriver, Duration.ofSeconds(5));
        // 使用ExpectedConditions定义等待条件，例如元素可见性
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("kw")));

        //隐式等待,可以用一个不存在的元素位置进行测试
        //webDriver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
        //输入元素 sendKey
        webDriver.findElement(By.id("kw")).sendKeys("小滴课堂官网");

        //提交元素  submit
        webDriver.findElement(By.id("su")).submit();
        String text = webDriver.findElement(By.id("su")).getAttribute("value");
        System.out.println("text======"+text);
    }



    public static void screenshotTest(WebDriver webDriver)throws Exception {
        //打开网页
        // 打开百度首页
        webDriver.get("https://www.baidu.com");
        // 在搜索框中输入“小滴课堂官网”
        webDriver.findElement(By.cssSelector("#kw")).sendKeys("小滴课堂官网");
        // 点击搜索按钮
        webDriver.findElement(By.cssSelector("#su")).click();
        // 等待2秒
        TimeUnit.SECONDS.sleep(2);

        // 截取当前页面的截图
        File file = ((TakesScreenshot) webDriver).getScreenshotAs(OutputType.FILE);
        // 将截图保存为文件，文件名为UUID加上.png后缀
        FileUtils.copyFile(file, new File(IdUtil.fastSimpleUUID()+".png"));

    }

    public static void windowTest(WebDriver webDriver)throws InterruptedException {
        //打开网页
        webDriver.get("https://www.baidu.com");
        String currentHandle = webDriver.getWindowHandle();
        System.out.println("currentHandle===="+currentHandle);
        //点击百度的新闻页面
        webDriver.findElement(By.cssSelector("#s-top-left > a:nth-child(1")).click();
        // getWindowHandles获取全部窗口句柄
        Set<String> handles = webDriver.getWindowHandles();
        String newHandle = "";
        for(String handle : handles){
            System.out.println("handle===="+handle);
            //记录最后一个窗口的具柄
            newHandle = handle;
        }
        TimeUnit.SECONDS.sleep(2);
        //切换到新窗口，没调用默认还是第一个窗口，则定位新闻窗口的输入框元素会失败
        webDriver.switchTo().window(newHandle);
        //定位新闻窗口的输入框
        webDriver.findElement(By.id("ww")).sendKeys("小滴课堂");
        System.out.println("newHandle===="+webDriver.getWindowHandle());

    }


    public static void mouseTest(WebDriver webDriver)throws InterruptedException {
        //打开网页
        webDriver.get("https://www.baidu.com");
        //输入小滴课堂官网
        webDriver.findElement(By.cssSelector("#kw")).sendKeys("小滴课堂官网");
        //点击百度一下的按钮
        webDriver.findElement(By.cssSelector("#su")).click();
        TimeUnit.SECONDS.sleep(2);
        //找到文库的text的tab
        WebElement webElement = webDriver.findElement(By.cssSelector("#s_tab > div > a.s-tab-item.s-tab-wenku"));
        TimeUnit.SECONDS.sleep(2);
        //鼠标右击
        Actions actions = new Actions(webDriver);
        actions.moveToElement(webElement).contextClick().perform();
    }


    public static void browserTest(WebDriver webDriver)throws InterruptedException {
        //打开网页
        webDriver.get("https://www.baidu.com");
        //输入小滴课堂官网
        webDriver.findElement(By.cssSelector("#kw")).sendKeys("小滴课堂官网");
        //点击搜索
        webDriver.findElement(By.cssSelector("#su")).click();
        //浏览器刷新
        TimeUnit.SECONDS.sleep(2);
        webDriver.navigate().refresh();
        //浏览器后退一步
        TimeUnit.SECONDS.sleep(2);
        //加个休眠时间
        webDriver.navigate().back();
        //浏览器前进
        TimeUnit.SECONDS.sleep(2);
        webDriver.navigate().forward();
        TimeUnit.SECONDS.sleep(2);
        //浏览器窗口按照指定大小来显示
        webDriver.manage().window().setSize(new Dimension(300,300));
        TimeUnit.SECONDS.sleep(2);
        //浏览器全屏
        webDriver.manage().window().fullscreen();
    }


    public static void webEleTest(WebDriver webDriver)throws InterruptedException {
        //启动需要打开的网页
        webDriver.get("https://www.baidu.com");
        String title = webDriver.getTitle();
        String currentUrl = webDriver.getCurrentUrl();

        System.out.println("title="+title+",currentUrl="+currentUrl);

        TimeUnit.SECONDS.sleep(2);

        //输入元素 sendKey
        webDriver.findElement(By.id("kw")).sendKeys("小滴课堂官网");
        TimeUnit.SECONDS.sleep(2);

        //清除元素  clear
        webDriver.findElement(By.id("kw")).clear();

        TimeUnit.SECONDS.sleep(2);
        webDriver.findElement(By.id("kw")).sendKeys("苹果手机");

        //点击元素  click
        webDriver.findElement(By.id("su")).click();
        TimeUnit.SECONDS.sleep(2);

        webDriver.findElement(By.id("kw")).clear();
        webDriver.findElement(By.id("kw")).sendKeys("苹果手机最新款");
        //提交元素  submit
        webDriver.findElement(By.id("su")).submit();
        String text = webDriver.findElement(By.id("su")).getAttribute("value");
        System.out.println("text======"+text);
    }





    public static void cssTest(WebDriver webDriver) throws InterruptedException {
        webDriver.get("https://xdclass.net");
        TimeUnit.SECONDS.sleep(2);
        List<WebElement> elements = webDriver.findElements(By.cssSelector("div[class='title']"));
        System.out.println("size======="+elements.size());
        List<String> list = new ArrayList<>(elements.size());
        for (WebElement element : elements) {
            String text = element.getText();
            list.add(text);
        }
        list.forEach(System.out::println);
    }


    public static void xpathTest(WebDriver webDriver) throws InterruptedException {
        webDriver.get("https://xdclass.net");
        TimeUnit.SECONDS.sleep(2);
        List<WebElement> elements = webDriver.findElements(By.xpath("//div[@class=\"title\"]"));
        System.out.println("size======="+elements.size());
        List<String> list = new ArrayList<>(elements.size());
        for (WebElement element : elements) {
            String text = element.getText();
            list.add(text);
        }
        list.forEach(System.out::println);
    }



    public static void classTest(WebDriver webDriver) throws InterruptedException {
        webDriver.get("https://xdclass.net");
        TimeUnit.SECONDS.sleep(2);
        List<WebElement> elements = webDriver.findElements(By.className("title"));
        System.out.println("size======="+elements.size());
        for (WebElement element : elements) {
            String text = element.getText();
            System.out.println(text);
        }
    }


    public static void partialLinkTest(WebDriver webDriver) throws InterruptedException {
        webDriver.get("https://xdclass.net");
        TimeUnit.SECONDS.sleep(2);
        WebElement webElement = webDriver.findElement(By.partialLinkText("课程"));
        String text = webElement.getText();
        System.out.println("text========="+text);
        //点击元素
        webElement.click();
    }

    public static void linkTest(WebDriver webDriver) throws InterruptedException {
        webDriver.get("https://xdclass.net");
        TimeUnit.SECONDS.sleep(2);
        WebElement webElement = webDriver.findElement(By.linkText("课程中心"));
        webElement.click();
    }
    public static void nameTest(WebDriver webDriver) throws InterruptedException{
        webDriver.get("https://baidu.com");
        TimeUnit.SECONDS.sleep(2);
        WebElement webElement = webDriver.findElement(By.name("wd"));
        webElement.sendKeys("小滴课堂官网");
    }

    public static void idTest(WebDriver webDriver) throws InterruptedException {
        webDriver.get("https://xdclass.net");
        TimeUnit.SECONDS.sleep(2);
        WebElement webElement = webDriver.findElement(By.id("rc_select_0"));
        webElement.sendKeys("架构大课");
    }


}
