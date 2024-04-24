package net.xdclass.service;

import net.xdclass.controller.req.ReportDetailPageReq;

import java.util.Map;

public interface ReportDetailService {

    void handleStressReportDetail(String topicContent);

    void handleApiReportDetail(String topicContent);

    void handleUiReportDetail(String topicContent);

    Map<String, Object> page(ReportDetailPageReq req);
}
