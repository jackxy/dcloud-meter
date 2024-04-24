package net.xdclass.service.ui.impl;

import net.xdclass.enums.BizCodeEnum;
import net.xdclass.enums.SeleniumByEnum;
import net.xdclass.exception.BizException;
import net.xdclass.model.UiCaseStepDO;
import net.xdclass.service.ui.SeleniumWaitOperationService;
import net.xdclass.util.SeleniumWebdriverContext;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.springframework.stereotype.Component;

import java.time.Duration;

/**
 * 小滴课堂,愿景：让技术不再难学
 *
 * @Description
 * @Author 二当家小D
 * @Remark 有问题直接联系我，源码-笔记-技术交流群
 * @Version 1.0
 **/
@Component
public class SeleniumWaitOperationServiceImpl implements SeleniumWaitOperationService {
    @Override
    public void waitHide(Long millSeconds) {
        WebDriver webDriver = SeleniumWebdriverContext.get();
        webDriver.manage().timeouts().implicitlyWait(Duration.ofMillis(millSeconds));
    }

    @Override
    public void waitShow(UiCaseStepDO uiCaseStepDO) {
        WebDriverWait wait = new WebDriverWait(SeleniumWebdriverContext.get(), Duration.ofMillis(Long.parseLong(uiCaseStepDO.getValue())));
        SeleniumByEnum seleniumByEnum = SeleniumByEnum.valueOf(uiCaseStepDO.getLocationType());
        String locationExpress = uiCaseStepDO.getLocationExpress();
        switch (seleniumByEnum) {
            case XPATH -> wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(locationExpress)));
            case TAG_NAME -> wait.until(ExpectedConditions.presenceOfElementLocated(By.tagName(locationExpress)));
            case ID -> wait.until(ExpectedConditions.presenceOfElementLocated(By.id(locationExpress)));
            case NAME -> wait.until(ExpectedConditions.presenceOfElementLocated(By.name(locationExpress)));
            case CLASS_NAME -> wait.until(ExpectedConditions.presenceOfElementLocated(By.className(locationExpress)));
            case LINK_TEXT -> wait.until(ExpectedConditions.presenceOfElementLocated(By.linkText(locationExpress)));
            case CSS_SELECTOR ->
                    wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector(locationExpress)));
            case PARTIAL_LINK_TEXT ->
                    wait.until(ExpectedConditions.presenceOfElementLocated(By.partialLinkText(locationExpress)));

            default -> throw new BizException(BizCodeEnum.UI_ELEMENT_NOT_EXIST);
        }
    }

    @Override
    public void waitForce(Long millSeconds) {
        try {
            Thread.sleep(millSeconds);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

    }
}
