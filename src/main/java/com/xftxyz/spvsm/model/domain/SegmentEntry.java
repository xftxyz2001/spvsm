package com.xftxyz.spvsm.model.domain;

import com.xftxyz.spvsm.controller.service.OS;

/**
 * 段表项
 */
public class SegmentEntry {
    public int segmentNum = -1; // 段号
    public int segmentSize = -1; // 段长
    public PageEntry[] pageTable = null; // 对应的页表

    /**
     * 段表项构造函数
     * 
     * @param segmentNum  段号
     * @param segmentSize 段长
     */
    public SegmentEntry(int segmentNum, int segmentSize) {
        this.segmentNum = segmentNum;
        this.segmentSize = segmentSize;

        // 计算需要的页表项数量
        int count = segmentSize / OS.pageSize; // 页表的大小
        // 如果页表大小不能整除段长，则需要多一个页表项
        if (segmentSize % OS.pageSize != 0) {
            count++;
        }
        // 创建页表
        pageTable = new PageEntry[count];
        for (int i = 0; i < count; i++) {
            pageTable[i] = new PageEntry(i);
        }
    }
}