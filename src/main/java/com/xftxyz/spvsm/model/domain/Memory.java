package com.xftxyz.spvsm.model.domain;

import com.xftxyz.spvsm.controller.service.OS;

public class Memory {

    private Frame[] memory; // 内存
    private int unusedFrameCount = 0; // 未使用的页框数

    /**
     * 创建有frameNum个未使用页框的内存
     * 
     * @param frameNum 内存中页框的数量
     */
    public Memory(int frameNum) {
        memory = new Frame[frameNum]; // 创建内存，含有frameNum个页框
        for (int i = 0; i < frameNum; i++) {
            memory[i] = new Frame(i, i * OS.pageSize); // 创建页框，页框号为i，起始地址为i*pageSize
        }
        unusedFrameCount = frameNum;// 开始时，全部页框都未使用
    }

    /**
     * 申请页框
     * 
     * 放置策略：优先放在低页框号的页框中
     * 申请(设置used为true)前n个未使用的页框，返回包含页框号的数组；若剩余内存不够，返回null
     * 
     * @param id 进程id
     * @param n  需要申请的页框数
     * @return 包含页框号的数组，若剩余内存不够，返回null
     */
    public int[] mallocFrame(String id, int n) {
        // 剩余页框数量不足
        if (unusedFrameCount < n) {
            return null;
        }
        // 结果数组，保存申请到的页框号
        int[] result = new int[n];
        // 遍历内存，找到n个未使用的页框
        for (int i = 0, index = 0; index < n && i < memory.length; i++) {
            if (memory[i].used == false) {
                result[index] = memory[i].frameNum;
                memory[i].setUsed(id);
                index++;
            }
        }
        // 申请成功，更新未使用页框数
        unusedFrameCount -= n;
        // 返回页框号数组
        return result;
    }

    /**
     * 释放页框
     * 
     * 将frames中的页框号对应的页框设置为未使用
     * 
     * @param frames 页框号数组
     */
    public void freeFrame(int[] frames) {
        for (int i = 0; i < frames.length; i++) {
            memory[frames[i]].setUnused();
        }
        // 更新未使用页框数
        unusedFrameCount += frames.length;
    }

    /**
     * 获取未使用页框数
     * 
     * @return 未使用页框数
     */
    public int unusedFrameCount() {
        return unusedFrameCount;
    }

    /**
     * 模拟从外存读入一页
     * 
     * 从外存读入id进程segmentNum段pageNum页frameNum的页框中
     * 
     * @param id         进程id
     * @param segmentNum 段号
     * @param pageNum    页号
     * @param frameNum   页框号
     */
    public void readPage(String id, int segmentNum, int pageNum, int frameNum) {
        System.out.println("IO: 将进程 " + id + " 段(" + segmentNum + ") 页(" + pageNum + ") 读入页框 " + frameNum + " 中");
    }

    /**
     * 模拟将程序的一页写入外存。
     * 
     * 将frameNum的内容写入外存，写入内容为id进程segmentNum段pageNum页
     * 
     * @param id         进程id
     * @param segmentNum 段号
     * @param pageNum    页号
     * @param frameNum   页框号
     */
    public void writePage(String id, int segmentNum, int pageNum, int frameNum) {
        System.out.println("IO: 将页框" + frameNum + "内容写入外存。进程 " + id + " 段(" + segmentNum + ") 页(" + pageNum + ")");
    }

    /**
     * 获取页框
     * 
     * 返回页框号为frame的页框，若请求页框不存在，返回null
     * 
     * @param frameNum 页框号
     * @return 页框，若请求页框不存在，返回null
     */
    public Frame getFrame(int frameNum) {
        if (frameNum >= 0 && frameNum < memory.length) {
            return memory[frameNum];
        }
        return null;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("内存使用情况：");
        for (int i = 0; i < memory.length; i++) {
            if (i % 8 == 0) { // 一行显示8个页框
                sb.append("\n" + i + "-" + (i + 7) + ":\t| ");
            }
            if (memory[i].used) {
                // 截取进程id前5个字符输出
                String id = memory[i].id;
                if (id.length() > 5) {
                    id = id.substring(0, 4);
                }
                sb.append(id + "\t| ");
            } else {
                sb.append("     \t| ");
            }
        }

        return sb.toString();
    }
}
