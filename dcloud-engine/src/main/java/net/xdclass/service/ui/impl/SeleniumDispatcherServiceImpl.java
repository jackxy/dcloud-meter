package net.xdclass.service.ui.impl;

import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import net.xdclass.dto.UiOperationResultDTO;
import net.xdclass.enums.BizCodeEnum;
import net.xdclass.enums.SeleniumOperationEnum;
import net.xdclass.enums.SeleniumOperationTypeEnum;
import net.xdclass.exception.BizException;
import net.xdclass.model.UiCaseStepDO;
import net.xdclass.service.ui.*;
import net.xdclass.util.SeleniumFetchUtil;
import net.xdclass.util.SeleniumWebdriverContext;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.springframework.stereotype.Service;

import java.time.Duration;

/**
 * 小滴课堂,愿景：让技术不再难学
 *
 * @Description
 * @Author 二当家小D
 * @Remark 有问题直接联系我，源码-笔记-技术交流群
 * @Version 1.0
 **/
@Service
@Slf4j
public class SeleniumDispatcherServiceImpl implements SeleniumDispatcherService {

    @Resource
    private SeleniumBrowserOperationService browserOperationService;

    @Resource
    private SeleniumMouseOperationService mouseOperationService;

    @Resource
    private SeleniumKeyboardOperationService keyboardOperationService;

    @Resource
    private SeleniumWaitOperationService waitOperationService;

    @Resource
    private SeleniumAssertionOperationService assertionOperationService;

    @Override
    public UiOperationResultDTO operationDispatcher(UiCaseStepDO uiCaseStepDO) {

        String type = uiCaseStepDO.getOperationType();

        if (type.startsWith(SeleniumOperationEnum.BROWSER.name())) {
            //浏览器相关操作
            browserOperationDispatcher(uiCaseStepDO);
        } else if (type.startsWith(SeleniumOperationEnum.MOUSE.name())) {
            //鼠标相关操作
            mouseOperationDispatcher(uiCaseStepDO);

        } else if (type.startsWith(SeleniumOperationEnum.KEYBOARD.name())) {
            //键盘相关操作
            keyboardOperationDispatcher(uiCaseStepDO);

        } else if (type.startsWith(SeleniumOperationEnum.WAIT.name())) {
            //等待相关操作
            waitOperationDispatcher(uiCaseStepDO);

        } else if (type.startsWith(SeleniumOperationEnum.ASSERTION.name())) {
            //断言相关操作
            return assertionOperationDispatcher(uiCaseStepDO);

        } else {
            log.error("不支持的UI操作类型：{}", type);
            throw new BizException(BizCodeEnum.UI_OPERATION_UNSUPPORTED);
        }
        return UiOperationResultDTO.builder().operationState(true).build();
    }

    private UiOperationResultDTO assertionOperationDispatcher(UiCaseStepDO uiCaseStepDO) {

        String type = uiCaseStepDO.getOperationType();
        UiOperationResultDTO operationResult = null;
        if (type.contains(SeleniumOperationEnum.ASSERTION_BROWSER.name())) {
            //断言浏览器相关操作
            operationResult = assertionBrowserOperationDispatcher(uiCaseStepDO);

        } else if (type.contains(SeleniumOperationEnum.ASSERTION_ELEMENT_TEXT.name())) {
            //断言元素文本相关操作
            operationResult = assertionElementTextOperationDispatcher(uiCaseStepDO);

        } else if (type.contains(SeleniumOperationEnum.ASSERTION_ELEMENT.name())) {
            //断言元素属性状态相关操作
            operationResult = assertionElementOperationDispatcher(uiCaseStepDO);

        } else {
            throw new BizException(BizCodeEnum.UI_OPERATION_UNSUPPORTED_ASSERTION);
        }

        return operationResult;
    }

    private UiOperationResultDTO assertionElementOperationDispatcher(UiCaseStepDO uiCaseStepDO) {

        SeleniumOperationTypeEnum operationTypeEnum = SeleniumOperationTypeEnum.valueOf(uiCaseStepDO.getOperationType());
        return switch (operationTypeEnum) {
            case ASSERTION_ELEMENT_EXIST -> assertionOperationService.existElement(uiCaseStepDO.getLocationType(), uiCaseStepDO.getLocationExpress());
            case ASSERTION_ELEMENT_NOT_EXIST -> assertionOperationService.absentElement(uiCaseStepDO.getLocationType(), uiCaseStepDO.getLocationExpress());
            case ASSERTION_ELEMENT_ENABLE -> assertionOperationService.enableElement(uiCaseStepDO.getLocationType(), uiCaseStepDO.getLocationExpress());
            case ASSERTION_ELEMENT_DISABLE -> assertionOperationService.disableElement(uiCaseStepDO.getLocationType(), uiCaseStepDO.getLocationExpress());
            case ASSERTION_ELEMENT_VISIBLE -> assertionOperationService.visibleElement(uiCaseStepDO.getLocationType(), uiCaseStepDO.getLocationExpress());
            case ASSERTION_ELEMENT_INVISIBLE -> assertionOperationService.invisibleElement(uiCaseStepDO.getLocationType(), uiCaseStepDO.getLocationExpress());
            case ASSERTION_ELEMENT_SELECT -> assertionOperationService.selectElement(uiCaseStepDO.getLocationType(), uiCaseStepDO.getLocationExpress());
            case ASSERTION_ELEMENT_UNSELECT -> assertionOperationService.unselectElement(uiCaseStepDO.getLocationType(), uiCaseStepDO.getLocationExpress());
            default -> {
                log.error("不支持的UI断言操作类型：{}", uiCaseStepDO.getOperationType());
                throw new BizException(BizCodeEnum.UI_OPERATION_UNSUPPORTED_ASSERTION);
            }
        };

    }

    private UiOperationResultDTO assertionElementTextOperationDispatcher(UiCaseStepDO uiCaseStepDO) {
        WebElement element = null;
        try {
            element = SeleniumFetchUtil.findElement(uiCaseStepDO.getLocationType(), uiCaseStepDO.getLocationExpress(), uiCaseStepDO.getElementWait());
        }catch (Exception e){
            //元素不存在
            throw new BizException(BizCodeEnum.UI_ELEMENT_NOT_EXIST);
        }

        String value = element.getText();
        SeleniumOperationTypeEnum operationTypeEnum = SeleniumOperationTypeEnum.valueOf(uiCaseStepDO.getOperationType());
        return switch (operationTypeEnum) {
            case ASSERTION_ELEMENT_TEXT_GREAT_THEN -> assertionOperationService.greaterThan(Long.parseLong(value), Long.parseLong(uiCaseStepDO.getExpectValue()));
            case ASSERTION_ELEMENT_TEXT_LESS_THEN -> assertionOperationService.lessThan(Long.parseLong(value), Long.parseLong(uiCaseStepDO.getExpectValue()));
            case ASSERTION_ELEMENT_TEXT_EQUAL -> assertionOperationService.equalValue(value, uiCaseStepDO.getExpectValue());
            case ASSERTION_ELEMENT_TEXT_NOT_EQUAL -> assertionOperationService.notEqualValue(value, uiCaseStepDO.getExpectValue());
            case ASSERTION_ELEMENT_TEXT_CONTAIN -> assertionOperationService.containValue(value, uiCaseStepDO.getExpectValue());
            case ASSERTION_ELEMENT_TEXT_NOT_CONTAIN -> assertionOperationService.notContainValue(value, uiCaseStepDO.getExpectValue());
            default -> {
                log.error("不支持的断言类型：{}", uiCaseStepDO.getOperationType());
                throw new BizException(BizCodeEnum.UI_OPERATION_UNSUPPORTED_ASSERTION);
            }
        };

    }

    private UiOperationResultDTO assertionBrowserOperationDispatcher(UiCaseStepDO uiCaseStepDO) {

        WebDriver webDriver = SeleniumWebdriverContext.get();
        SeleniumOperationTypeEnum operationTypeEnum = SeleniumOperationTypeEnum.valueOf(uiCaseStepDO.getOperationType());

        return switch (operationTypeEnum) {
            case ASSERTION_BROWSER_TITLE_EQUAL -> assertionOperationService.equalValue(webDriver.getTitle(), uiCaseStepDO.getExpectValue());
            case ASSERTION_BROWSER_TITLE_NOT_EQUAL ->
                assertionOperationService.notEqualValue(webDriver.getTitle(), uiCaseStepDO.getExpectValue());
            case ASSERTION_BROWSER_TITLE_CONTAIN ->
                assertionOperationService.containValue(webDriver.getTitle(), uiCaseStepDO.getExpectValue());
            case ASSERTION_BROWSER_TITLE_NOT_CONTAIN ->
                assertionOperationService.notContainValue(webDriver.getTitle(), uiCaseStepDO.getExpectValue());
            case ASSERTION_BROWSER_URL_EQUAL ->
                assertionOperationService.equalValue(webDriver.getCurrentUrl(), uiCaseStepDO.getExpectValue());
            case ASSERTION_BROWSER_URL_NOT_EQUAL ->
                assertionOperationService.notEqualValue(webDriver.getCurrentUrl(), uiCaseStepDO.getExpectValue());
            case ASSERTION_BROWSER_URL_CONTAIN ->
                assertionOperationService.containValue(webDriver.getCurrentUrl(), uiCaseStepDO.getExpectValue());
            case ASSERTION_BROWSER_URL_NOT_CONTAIN ->
                assertionOperationService.notContainValue(webDriver.getCurrentUrl(), uiCaseStepDO.getExpectValue());
            default -> {
                log.error("不支持的断言类型：{}", uiCaseStepDO.getOperationType());
                throw new BizException(BizCodeEnum.UI_OPERATION_UNSUPPORTED_ASSERTION);
            }
        };

    }

    private void waitOperationDispatcher(UiCaseStepDO uiCaseStepDO) {
        SeleniumOperationTypeEnum operationTypeEnum = SeleniumOperationTypeEnum.valueOf(uiCaseStepDO.getOperationType());
        switch (operationTypeEnum) {
            case WAIT_SHOW:
                waitOperationService.waitShow(uiCaseStepDO);
                break;
            case WAIT_HIDE:
                waitOperationService.waitHide(Long.parseLong(uiCaseStepDO.getValue()));
                break;
            case WAIT_FORCE:
                waitOperationService.waitForce(Long.parseLong(uiCaseStepDO.getValue()));
                break;
            default:
                log.error("不支持的等待操作类型：{}", uiCaseStepDO.getOperationType());
                throw new BizException(BizCodeEnum.UI_OPERATION_UNSUPPORTED_WAIT);
        }

    }

    private void keyboardOperationDispatcher(UiCaseStepDO uiCaseStepDO) {
        WebElement webElement = SeleniumFetchUtil.findElement(uiCaseStepDO.getLocationType(), uiCaseStepDO.getLocationExpress(), uiCaseStepDO.getElementWait());

        SeleniumOperationTypeEnum operationTypeEnum = SeleniumOperationTypeEnum.valueOf(uiCaseStepDO.getOperationType());
        //通过switch
        switch (operationTypeEnum) {
            case KEYBOARD_INPUT:
                keyboardOperationService.input(webElement, uiCaseStepDO.getValue());
                break;
            case KEYBOARD_CLEAR:
                keyboardOperationService.clear(webElement);
                break;
            case KEYBOARD_SUBMIT:
                keyboardOperationService.submit(webElement);
                break;
            default:
                log.error("不支持的键盘操作类型：{}", uiCaseStepDO.getOperationType());
                throw new BizException(BizCodeEnum.UI_OPERATION_UNSUPPORTED);
        }


    }

    private void mouseOperationDispatcher(UiCaseStepDO uiCaseStepDO) {

        WebElement webElement = SeleniumFetchUtil.findElement(uiCaseStepDO.getLocationType(), uiCaseStepDO.getLocationExpress(), uiCaseStepDO.getElementWait());

        SeleniumOperationTypeEnum operationTypeEnum = SeleniumOperationTypeEnum.valueOf(uiCaseStepDO.getOperationType());
        //通过switch
        switch (operationTypeEnum) {
            case MOUSE_LEFT_CLICK:
                mouseOperationService.leftClick(webElement);
                break;
            case MOUSE_DOUBLE_CLICK:
                mouseOperationService.doubleClick(webElement);
                break;
            case MOUSE_MOVE_TO_ELEMENT:
                mouseOperationService.moveToElement(webElement);
                break;
            case MOUSE_RIGHT_CLICK:
                mouseOperationService.rightClick(webElement);
                break;
            case MOUSE_DRAG_ELEMENT_TO_ELEMENT:
                WebElement target = SeleniumFetchUtil.findElement(uiCaseStepDO.getTargetLocationType(),
                        uiCaseStepDO.getTargetLocationExpress(), uiCaseStepDO.getTargetElementWait());

                mouseOperationService.dragElementToElement(webElement, target);
                break;
            default: {
                log.error("不支持的UI操作类型：{}", operationTypeEnum);
                throw new BizException(BizCodeEnum.UI_OPERATION_UNSUPPORTED_BROWSER);
            }
        }

    }

    private void browserOperationDispatcher(UiCaseStepDO uiCaseStepDO) {
        String operationType = uiCaseStepDO.getOperationType();
        SeleniumOperationTypeEnum operationTypeEnum = SeleniumOperationTypeEnum.valueOf(operationType);
        switch (operationTypeEnum) {
            case BROWSER_OPEN:
                browserOperationService.open(uiCaseStepDO.getValue());
                break;
            case BROWSER_CLOSE:
                browserOperationService.close();
                break;
            case BROWSER_SWITCH_BY_HANDLER:
                browserOperationService.switchByHandle(uiCaseStepDO.getValue());
                break;
            case BROWSER_SWITCH_BY_INDEX:
                browserOperationService.switchByIndex(Integer.parseInt(uiCaseStepDO.getValue()));
                break;
            case BROWSER_REFRESH:
                browserOperationService.refresh();
                break;
            case BROWSER_BACK:
                browserOperationService.back();
                break;
            case BROWSER_MAXIMIZE:
                browserOperationService.resizeMaximize();
                break;
            case BROWSER_RESIZE:
                //输入窗口大小格式 1490，1221
                String[] split = uiCaseStepDO.getValue().split(",");
                browserOperationService.resize(Integer.parseInt(split[0]), Integer.parseInt(split[1]));
                break;
            case BROWSER_FORWARD:
                browserOperationService.forward();
                break;
            default: {
                log.error("不支持的浏览器操作类型：{}", operationType);
                throw new BizException(BizCodeEnum.UI_OPERATION_UNSUPPORTED_BROWSER);
            }


        }

    }
}
