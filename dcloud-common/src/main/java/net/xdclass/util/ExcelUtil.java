package net.xdclass.util;

import cn.hutool.core.date.DateUtil;
import cn.hutool.http.server.HttpServerResponse;
import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.converters.Converter;
import com.alibaba.excel.converters.ReadConverterContext;
import com.alibaba.excel.converters.WriteConverterContext;
import com.alibaba.excel.enums.CellDataTypeEnum;
import com.alibaba.excel.metadata.GlobalConfiguration;
import com.alibaba.excel.metadata.data.ReadCellData;
import com.alibaba.excel.metadata.data.WriteCellData;
import com.alibaba.excel.metadata.property.ExcelContentProperty;
import com.alibaba.excel.support.ExcelTypeEnum;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;

import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 小滴课堂,愿景：让技术不再难学
 *
 * @Description
 * @Author 二当家小D
 * @Remark 有问题直接联系我，源码-笔记-技术交流群
 * @Version 1.0
 **/
@Slf4j
public class ExcelUtil {

    private static final String CONTENT_TYPE = "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet";

    public static <T> void exportExcel(HttpServletResponse response, List<T> list, String originName) {
        try {
            // 这里注意 有同学反应使用swagger 会导致各种问题，请直接用浏览器或者用postman
            response.setContentType(CONTENT_TYPE);
            response.setCharacterEncoding("utf-8");
            // 这里URLEncoder.encode可以防止中文乱码 当然和easyexcel没有关系
            String fileName = URLEncoder.encode(originName, "UTF-8").replaceAll("\\+", "%20");
            response.setHeader("Content-disposition",
                    "attachment;filename*=utf-8''" + fileName +"_"+ DateUtil.format(new Date(),"yyyyMMddHHmmss")+".xlsx");

            if(list!=null && !list.isEmpty()){
                EasyExcel.write(response.getOutputStream()).excelType(ExcelTypeEnum.XLSX)
                        .head(list.get(0).getClass()).sheet("sheet1").doWrite(list);
            }else {
                EasyExcel.write(response.getOutputStream()).excelType(ExcelTypeEnum.XLSX)
                        .head(Collections.emptyList()).sheet("sheet1").doWrite(list);
            }
        }catch (Exception e){
            log.error("导出excel失败",e);
            throw new RuntimeException("导出excel失败");
        }

    }


    public static class LongTimeConverter implements Converter<Long> {
        @Override
        public Class<?> supportJavaTypeKey() {
            return Long.class;
        }

        @Override
        public CellDataTypeEnum supportExcelTypeKey() {
            return CellDataTypeEnum.STRING;
        }

        /**
         * 将Excel中的数据转换为Java数据类型。
         *
         * @param cellData            包含单元格数据的读取上下文。
         * @param contentProperty     单元格内容属性。
         * @param globalConfiguration 全局配置信息。
         * @return 转换后的Java数据类型实例，这里是Long类型。
         * @throws Exception 转换过程中出现的任何异常。
         */
        @Override
        public Long convertToJavaData(ReadCellData<?> cellData, ExcelContentProperty contentProperty, GlobalConfiguration globalConfiguration) throws Exception {
            return cellData.getNumberValue().longValue();
        }

        /**
         * 将Java数据类型转换为Excel中的数据。
         *
         * @param value               要转换的Java数据类型实例，这里是Long类型的时间戳。
         * @param contentProperty     单元格内容属性。
         * @param globalConfiguration 全局配置信息。
         * @return 转换后的Excel数据类型实例，这里是包含格式化后日期字符串的WriteCellData对象。
         * @throws Exception 转换过程中出现的任何异常。
         */
        @Override
        public WriteCellData<?> convertToExcelData(Long value, ExcelContentProperty contentProperty, GlobalConfiguration globalConfiguration) throws Exception {
            String format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date(value));
            return new WriteCellData<>(format);
        }

    }



}
