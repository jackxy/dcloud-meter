package net.xdclass.stress;

import org.apache.jmeter.control.LoopController;
import org.apache.jmeter.engine.DistributedRunner;
import org.apache.jmeter.protocol.http.sampler.HTTPSampler;
import org.apache.jmeter.reporters.Summariser;
import org.apache.jmeter.testelement.TestPlan;
import org.apache.jmeter.threads.SetupThreadGroup;
import org.apache.jmeter.util.JMeterUtils;
import org.apache.jorphan.collections.HashTree;

import java.util.LinkedList;
import java.util.List;

/**
 * 小滴课堂,愿景：让技术不再难学
 *
 * @Description
 * @Author 二当家小D
 * @Remark 有问题直接联系我，源码-笔记-技术交流群
 * @Version 1.0
 **/
public class DistributeTest {

    /**
     * 主函数，执行分布式压力测试。
     *
     * @param argv 命令行参数
     * @throws Exception 抛出异常
     */
    public static void main(String[] argv) throws Exception {

        // 设置JMeter基础路径和属性文件
        String jmeterHome = "/Users/xdclass/Desktop/jmeter";
        JMeterUtils.setJMeterHome(jmeterHome);
        JMeterUtils.loadJMeterProperties(JMeterUtils.getJMeterBinDir() + "/jmeter.properties");
        // TODO 配置Controller节点基础配置信息
        JMeterUtils.setProperty("server.rmi.ssl.disable", "true");
        JMeterUtils.setProperty("mode", "Standard");
        JMeterUtils.initLogging();
        JMeterUtils.initLocale();

        // 创建HTTP请求采样器并配置
        HTTPSampler httpSampler = new HTTPSampler();
        httpSampler.setDomain("dmeter.cn");
        httpSampler.setPort(80);
        httpSampler.setPath("/");
        httpSampler.setMethod("GET");

        // 创建循环控制器并配置
        LoopController loopController = new LoopController();
        loopController.setLoops(2);
        loopController.setFirst(true);
        loopController.initialize();

        // 定义线程组并配置
        SetupThreadGroup threadGroup = new SetupThreadGroup();
        threadGroup.setName("测试线程组");
        threadGroup.setNumThreads(10);
        threadGroup.setRampUp(1);
        threadGroup.setSamplerController(loopController);

        // 定义测试计划并构建测试树
        TestPlan testPlan = new TestPlan("小滴课堂云测平台-分布式压测计划");
        HashTree testPlanTree = new HashTree();
        testPlanTree.add(testPlan);
        HashTree threadGroupHashTree = testPlanTree.add(testPlan, threadGroup);
        threadGroupHashTree.add(httpSampler);

        // 初始化结果汇总器和结果收集器
        Summariser summer = null;
        String summariserName = JMeterUtils.getPropDefault("summariser.name", "summary");
        if (summariserName.length() > 0) {
            summer = new Summariser(summariserName);
        }
        TestResultCollector logger = new TestResultCollector(summer);
        testPlanTree.add(testPlanTree.getArray()[0], logger);

        // TODO 初始化并配置分布式测试运行器
        // 初始化远程主机列表
        List<String> remoteHosts = new LinkedList<>();
        remoteHosts.add("127.0.0.1:9001");
        remoteHosts.add("127.0.0.1:9002");
        // 创建分布式运行器实例
        DistributedRunner distributedRunner = new DistributedRunner();
        // 设置标准输出和错误输出
        distributedRunner.setStdout(System.out);
        distributedRunner.setStdErr(System.err);
        // 初始化分布式运行器，传入远程主机列表和测试计划树
        distributedRunner.init(remoteHosts, testPlanTree);

        // TODO 启动分布式测试
        distributedRunner.start();

        // TODO 添加程序退出钩子，确保测试优雅结束
        Runtime.getRuntime().addShutdownHook(new Thread() {
            @Override
            public void run() {
                System.out.println("=========小滴课堂 addShutdownHook运行=========");
                distributedRunner.shutdown(remoteHosts);

            }
        });
    }


}
