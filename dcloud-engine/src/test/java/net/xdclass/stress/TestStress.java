package net.xdclass.stress;

import cn.hutool.core.util.IdUtil;
import net.xdclass.util.StressTestUtil;
import org.apache.commons.io.FileUtils;
import org.apache.jmeter.JMeter;
import org.apache.jmeter.config.Arguments;
import org.apache.jmeter.config.gui.ArgumentsPanel;
import org.apache.jmeter.control.LoopController;
import org.apache.jmeter.control.gui.LoopControlPanel;
import org.apache.jmeter.control.gui.TestPlanGui;
import org.apache.jmeter.engine.StandardJMeterEngine;
import org.apache.jmeter.protocol.http.control.gui.HttpTestSampleGui;
import org.apache.jmeter.protocol.http.sampler.HTTPSamplerProxy;
import org.apache.jmeter.report.dashboard.ReportGenerator;
import org.apache.jmeter.reporters.ResultCollector;
import org.apache.jmeter.reporters.Summariser;
import org.apache.jmeter.samplers.SampleSaveConfiguration;
import org.apache.jmeter.save.SaveService;
import org.apache.jmeter.services.FileServer;
import org.apache.jmeter.testelement.TestElement;
import org.apache.jmeter.testelement.TestPlan;
import org.apache.jmeter.threads.ThreadGroup;
import org.apache.jmeter.threads.gui.ThreadGroupGui;
import org.apache.jmeter.util.JMeterUtils;
import org.apache.jorphan.collections.HashTree;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * 小滴课堂,愿景：让技术不再难学
 *
 * @Description
 * @Author 二当家小D
 * @Remark 有问题直接联系我，源码-笔记-技术交流群
 * @Version 1.0
 **/
public class TestStress {


    @Test
    public void testJmeterScript() throws IOException {

        // JMeter路径
        String jmeterPath = "/Users/xdclass/Desktop/coding/apache-jmeter-5.5";
        // JMeter根目录
        File jmeterHome = new File(jmeterPath);
        // JMX文件路径
        String jmxFilePath = "/Users/xdclass/Desktop/课程资料-jmx集合/query.jmx";
        // JMX文件
        File jmxFile = new File(jmxFilePath);
        // JMeter配置文件路径
        File jmeterProperties = new File(jmeterHome.getPath() + File.separator + "bin" + File.separator + "jmeter.properties");
        // 设置JMeter根目录
        JMeterUtils.setJMeterHome(jmeterHome.getPath());
        // 加载JMeter配置文件
        JMeterUtils.loadJMeterProperties(jmeterProperties.getPath());

        // JMeter标准引擎
        StandardJMeterEngine jmeter = new StandardJMeterEngine();
        // 测试计划树
        HashTree testPlanTree = new HashTree();

        // 设置文件服务器的基础脚本路径
        FileServer.getFileServer().setBaseForScript(jmxFile);
        // 加载测试计划树 jmx脚本
        testPlanTree = SaveService.loadTree(jmxFile);

        // 转换测试计划树
        JMeter.convertSubTree(testPlanTree, false);

        // Summariser对象
        Summariser summer = null;
        // Summariser名称
        String summariserName = JMeterUtils.getPropDefault("summariser.name", "summary");
        if (!summariserName.isEmpty()) {
            // 创建Summariser对象
            summer = new Summariser(summariserName);
        }
        // 结果日志文件名
        String logFile = "example.csv";
        // 结果收集器
        //ResultCollector logger = new ResultCollector (summer);
        TestResultCollector logger = new TestResultCollector(summer);
        logger.setFilename(logFile);
        // 将结果收集器添加到测试计划树上
        testPlanTree.add(testPlanTree.getArray()[0], logger);
        // 配置JMeter引擎
        jmeter.configure(testPlanTree);
        // 运行JMeter测试
        jmeter.run();
    }




    @Test
    public void testJmeterOnline() throws IOException {

        StandardJMeterEngine jmeter = StressTestUtil.getJmeterEngine();
        // 创建JMeter的TestPlan树结构并设置相关属性
        HashTree testPlanTree = new HashTree();
        // 设置HTTP请求的名称、协议、域名、端口、路径和方法
        HTTPSamplerProxy httpSampler = new HTTPSamplerProxy();
        httpSampler.setName("HTTP Sampler");
        httpSampler.setProtocol("http");
        httpSampler.setDomain("127.0.0.1");
        httpSampler.setPort(8082);
        httpSampler.setPath("/api/v1/test/query");
        httpSampler.setMethod("GET");
        httpSampler.setProperty(TestElement.TEST_CLASS, HTTPSamplerProxy.class.getName());
        httpSampler.setProperty(TestElement.GUI_CLASS, HttpTestSampleGui.class.getName());
        httpSampler.setEnabled(true);
        // 添加请求参数
        httpSampler.addArgument("id", "1");

        // 创建Loop Controller
        LoopController loopController = new LoopController();
        loopController.setLoops(1);
        loopController.setFirst(true);
        loopController.setProperty(TestElement.TEST_CLASS, LoopController.class.getName());
        loopController.setProperty(TestElement.GUI_CLASS, LoopControlPanel.class.getName());
        loopController.initialize();

        //Thread Group 创建Thread Group
        ThreadGroup threadGroup = new ThreadGroup();
        threadGroup.setName("API Thread Group");
        threadGroup.setNumThreads(5); //Users
        threadGroup.setRampUp(2); //Seconds
        threadGroup.setSamplerController(loopController);
        threadGroup.setProperty(TestElement.TEST_CLASS, ThreadGroup.class.getName());
        threadGroup.setProperty(TestElement.GUI_CLASS, ThreadGroupGui.class.getName());
        threadGroup.setIsSameUserOnNextIteration(true);
        threadGroup.setScheduler(false);


        //Test Plan，将Test Plan、Thread Group和HTTP Sampler添加到Test Plan树结构中
        TestPlan testPlan = new TestPlan("JMeter Script From Java Code");
        testPlan.setProperty(TestElement.TEST_CLASS, TestPlan.class.getName());
        testPlan.setProperty(TestElement.GUI_CLASS, TestPlanGui.class.getName());
        testPlan.setUserDefinedVariables((Arguments) new ArgumentsPanel().createTestElement());

        testPlanTree.add(testPlan);
        HashTree threadGroupHashTree = testPlanTree.add(testPlan, threadGroup);
        threadGroupHashTree.add(httpSampler);

        //保存Test Plan到JMX文件,方便调试使用，可以不生成
        String jmxFilePath = IdUtil.simpleUUID() +".jmx";
        File jmxFile = new File(jmxFilePath);
        jmxFile.createNewFile();
        SaveService.saveTree(testPlanTree, new FileOutputStream(jmxFile));

        // Summariser对象
        Summariser summer = null;
        // Summariser名称
        String summariserName = JMeterUtils.getPropDefault("summariser.name", "summary");
        if (!summariserName.isEmpty()) {
            // 创建Summariser对象
            summer = new Summariser(summariserName);
        }
        // 结果日志文件名
        String logFile = "example_simple.csv";
        // 结果收集器
        ResultCollector logger = new ResultCollector(summer);
        logger.setFilename(logFile);
        // 将结果收集器添加到测试计划树上
        testPlanTree.add(testPlanTree.getArray()[0], logger);
        // 配置JMeter引擎
        jmeter.configure(testPlanTree);
        // 运行JMeter测试
        jmeter.run();

    }

}
