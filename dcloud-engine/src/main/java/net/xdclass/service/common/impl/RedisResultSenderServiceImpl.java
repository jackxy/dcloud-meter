package net.xdclass.service.common.impl;

import net.xdclass.dto.common.CaseInfoDTO;
import net.xdclass.enums.TestTypeEnum;
import net.xdclass.service.common.ResultSenderService;
import org.springframework.stereotype.Service;

/**
 * 小滴课堂,愿景：让技术不再难学
 *
 * @Description
 * @Author 二当家小D
 * @Remark 有问题直接联系我，源码-笔记-技术交流群
 * @Version 1.0
 **/
//@Service
public class RedisResultSenderServiceImpl implements ResultSenderService {
    @Override
    public void sendResult(CaseInfoDTO caseInfoDTO, TestTypeEnum testTypeEnum, String result) {

    }
}
