package com.xftxyz.spvsm.controller.service;

/**
 * 配置
 */
public interface XFSetting {
    String version = "1.0.0";
    String name = "段页式虚拟存储管理";

    String configFilePath = "config.ini";

    String helpMess = "create process 进程id 各个段大小\t创建一个进程\n" +
            "destroy process 进程id\t\t销毁一个进程\n" +
            "show memory\t\t\t显示内存使用情况\n" +
            "show process 进程id\t\t显示该进程驻留集、置换策略、段表、页表\n" +
            "address 进程名 段号 段偏移\t\t将逻辑地址映射为物理地址\n" +
            "help or h\t\t\t获取帮助\n" +
            "quit or q\t\t\t退出\n";
    String description = "内存大小64K，页框大小为1K，一个进程最多有4个段，且每个段最大为16K。一个进程驻留集最多为8页。\n"
            + "驻留集置换策略：局部策略（仅在进程的驻留集中选择一页）\n"
            + "页面淘汰策略：FIFO、LRU\n"
            + "进程初始载入策略：从第0个、第1个段...依次载入页，直到驻留集已全部载入\n"
            + "放置策略：决定一个进程驻留集存放在内存什么地方。优先放在低页框\n";

    // 配置默认值
    int DEFAULT_MEMORY_SIZE = 65536; // 内存大小（默认值）
    int DEFAULT_PAGE_SIZE = 1024; // 页大小，为了简化地址转换，为2的幂（默认值）
    int DEFAULT_MAX_SEGMENT_NUM = 4; // 一个程序最多有多少个段（默认值）
    int DEFAULT_MAX_SEGMENT_SIZE = 16384; // 一个段最大大小（默认值）
    int DEFAULT_MAX_RESIDENT_SET_NUMBER = 8; // 进程驻留集最多多少个页（默认值）
}
