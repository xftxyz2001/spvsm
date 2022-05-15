package com.xftxyz.spvsm.model.domain;

/**
 * 页表项
 */
public class PageEntry {
    public int pageNum; // 页号
    public boolean load; // 该页是否载入

    // load为false时，以下字段无意义
    public int frameNum; // 该页载入的页框号。
    // 该页最近一次被访问的时间：用于实现页面置换策略LRU，当该页被载入内存或被访问时，重置该时间
    public long usedTime;
    public String info = ""; // 其他信息，如设置保护、共享等

    /**
     * 构造函数
     * 
     * 创建一个指定页号、未载入的页
     * 
     * @param pageNum 页号
     */
    public PageEntry(int pageNum) {
        this.pageNum = pageNum;
        setUnload();
    }

    /**
     * 载入内存
     * 
     * 设置该页载入到页框号为frameNum的页框中
     * 
     * @param frameNum 页框号
     */
    public void setLoad(int frameNum) {
        this.load = true;
        this.frameNum = frameNum;
        usedTime = System.currentTimeMillis();
    }

    /**
     * 将该页载出内存
     */
    public void setUnload() {
        this.load = false;
        this.frameNum = -1;
        usedTime = -1;
    }
}
