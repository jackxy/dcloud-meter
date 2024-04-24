package net.xdclass.service;

import net.xdclass.controller.req.ReportDelReq;
import net.xdclass.controller.req.ReportExportReq;
import net.xdclass.controller.req.ReportPageReq;
import net.xdclass.dto.ReportDTO;
import net.xdclass.dto.ReportExcelDTO;
import net.xdclass.req.ReportSaveReq;
import net.xdclass.req.ReportUpdateReq;

import java.util.List;
import java.util.Map;

public interface ReportService {
    /**
     * 保存测试报告
     * @param req
     * @return
     */
    ReportDTO save(ReportSaveReq req);

    /**
     * 更新状态
     * @param req
     */
    void updateReportState(ReportUpdateReq req);

    /**
     * 导出报告
     * @param req
     * @return
     */
    List<ReportExcelDTO> exportReport(ReportExportReq req);

    /**
     * 分页查询
     * @param req
     * @return
     */
    Map<String, Object> page(ReportPageReq req);

    /**
     * 删除报告
     * @param req
     * @return
     */
    int delete(ReportDelReq req);
}
