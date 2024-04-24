package net.xdclass.service.ui;

public interface SeleniumBrowserOperationService {


    /**
     * 打开网页
     * @param url
     */
    void open(String url);
    /**
     * 关闭当前窗口或页面。
     */
    void close();

    /**
     * 返回到前一个页面。
     */
    void back();

    /**
     * 前进到下一个页面。
     */
    void forward();

    /**
     * 刷新当前页面。
     */
    void refresh();

    /**
     * 根据处理器名称切换窗口或页面。
     * @param handler 处理器名称，用于标识要切换的窗口或页面。
     */
    void switchByHandle(String handler);

    /**
     * 根据索引切换窗口或页面。
     * @param index 窗口或页面的索引，用于标识要切换的目标。
     */
    void switchByIndex(int index);

    /**
     * 最大化窗口尺寸。
     */
    void resizeMaximize();

    /**
     * 重设窗口大小
     * @param x
     * @param y
     */
    void resize(int x, int y);

}
