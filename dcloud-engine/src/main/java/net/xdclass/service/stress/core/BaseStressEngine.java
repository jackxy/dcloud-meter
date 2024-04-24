package net.xdclass.service.stress.core;

import cn.hutool.core.util.IdUtil;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import net.xdclass.dto.ReportDTO;
import net.xdclass.enums.ReportStateEnum;
import net.xdclass.feign.ReportFeignService;
import net.xdclass.model.StressCaseDO;
import net.xdclass.req.ReportUpdateReq;
import net.xdclass.service.common.ResultSenderService;
import net.xdclass.util.CustomFileUtil;
import net.xdclass.util.StressTestUtil;
import org.apache.jmeter.config.CSVDataSet;
import org.apache.jmeter.engine.StandardJMeterEngine;
import org.apache.jmeter.reporters.Summariser;
import org.apache.jmeter.save.SaveService;
import org.apache.jmeter.testelement.TestElement;
import org.apache.jmeter.util.JMeterUtils;
import org.apache.jorphan.collections.HashTree;
import org.apache.jorphan.collections.SearchByClass;
import org.springframework.context.ApplicationContext;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Collection;
import java.util.Objects;

/**
 * 小滴课堂,愿景：让技术不再难学
 *
 * @Description
 * @Author 二当家小D
 * @Remark 有问题直接联系我，源码-笔记-技术交流群
 * @Version 1.0
 **/
@Data
@Slf4j
public abstract class BaseStressEngine {

    /**
     * 最终的测试计划
     */
    private HashTree testPlanHashTree;

    /**
     * 测试引擎
     */
    protected StandardJMeterEngine engine;

    /**
     * 测试用例
     */
    protected StressCaseDO stressCaseDO;

    /**
     * 测试报告
     */
    protected ReportDTO reportDTO;


    /**
     * spring的应用上下文
     */
    protected ApplicationContext applicationContext;



    /**
     * 这个是模版方法，具体的由子类进行实现
     */
    public void startStressTest(){
        //初始化测试引擎
        this.initStressEngine();

        //组装测试计划 抽象方法
        this.assembleTestPlan();

        //方便调试使用，可以不用
        this.hashTree2Jmx();

        //运行测试
        this.run();

        //运行完用例后，清理相关的资源
        this.clearData();

        //更新测试报告
        this.updateReport();
    }


    /**
     * 获取结果收集器，这个是JMX和Simple公用的
     * @param resultSenderService
     * @return
     */
    public EngineSampleCollector getEngineSampleCollector(ResultSenderService resultSenderService) {

        // Summariser对象
        Summariser summer = null;
        // Summariser名称
        String summariserName = JMeterUtils.getPropDefault("summariser.name", "summary");
        if (!summariserName.isEmpty()) {
            // 创建Summariser对象
            summer = new Summariser(summariserName);
        }
        //使用自定义结果收集器
        EngineSampleCollector collector = new EngineSampleCollector(stressCaseDO,summer,resultSenderService,reportDTO);
        //如果要调整收集器名称
        collector.setName(stressCaseDO.getName());
        collector.setEnabled(Boolean.TRUE);

        return collector;

    }



    /**
     * 更新测试报告
     */
    public void updateReport() {

        while (!engine.isActive()){
            ReportFeignService reportFeignService = applicationContext.getBean(ReportFeignService.class);
            ReportUpdateReq reportUpdateReq = ReportUpdateReq.builder()
                    .id(reportDTO.getId())
                    .executeState(ReportStateEnum.COUNTING_REPORT.name())
                    .endTime(System.currentTimeMillis()).build();
            reportFeignService.update(reportUpdateReq);
            break;
        }

    }

    /**
     * 清理相关资源文件
     */
    public void clearData() {

        // 寻找JMX里面的CSVDataSet
        SearchByClass<TestElement> testElementVisitor = new SearchByClass<>(TestElement.class);
        testPlanHashTree.traverse(testElementVisitor);
        Collection<TestElement> searchResults = testElementVisitor.getSearchResults();
        // 提取里面的csv data set的类，获取filename路径，然后删除
        for(TestElement testElement: searchResults){
            if(testElement instanceof CSVDataSet csvDataSet){
                String filename = csvDataSet.getProperty("filename").getStringValue();
                Path path = Paths.get(filename);
                try {
                    Files.delete(path);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        }
    }


    /**
     * 运行压测
     */
    public void run() {
        if(Objects.nonNull(testPlanHashTree)){
            engine.configure(testPlanHashTree);
            //运行引擎
            engine.run();
        }
    }

    /**
     * 把测试计划转为本地JMX文件
     */
    public void hashTree2Jmx() {
        try {

            StressTestUtil.initJmeterProperties();
            SaveService.loadProperties();
            String dir = System.getProperty("user.dir") + File.separator+"static"+File.separator;
            CustomFileUtil.mkdir(dir);
            String localJmxPath =  dir + IdUtil.simpleUUID()+".jmx";
            SaveService.saveTree(testPlanHashTree, new FileOutputStream(localJmxPath));
        }catch (Exception e){
            e.printStackTrace();
            log.error("保存本地jmx失败");
        }
    }

    /**
     * 组装测试计划,交给子类进行实现
     */
    public abstract void assembleTestPlan();

    /**
     * 初始化测试引擎
     */
    public void initStressEngine() {
        engine = StressTestUtil.getJmeterEngine();
    }

}
